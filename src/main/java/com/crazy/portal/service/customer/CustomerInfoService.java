package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerShipBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.bean.customer.dealer.credit.Zsdscredit;
import com.crazy.portal.dao.customer.*;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CustomerInfoService
 * @Author: God Man Qiu~
 * @Date: 2019/8/19 22:26
 */
@Slf4j
@Service
public class CustomerInfoService {
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private AssetsInformationMapper assetsInformationMapper;
    @Resource
    private BusinessInformationMapper businessInformationMapper;
    @Resource
    private CustomerContactMapper customerContactMapper;
    @Resource
    private CustomerProductMapper customerProductMapper;
    @Resource
    private CustomerFileMapper customerFileMapper;
    @Resource
    private CustBankInfoMapper custBankInfoMapper;
    @Resource
    private CustCorporateRelationshipMapper custCorporateRelationshipMapper;
    @Resource
    private CustInvoiceInfoMapper custInvoiceInfoMapper;
    @Resource
    private CustSalesMapper custSalesMapper;
    @Resource
    private CustomerAddressMapper customerAddressMapper;
    @Resource
    private CustomerAccountTeamMapper customerAccountTeamMapper;
    @Resource
    private CustomerReportMapper customerReportMapper;

    /**
     * 分页查询客户信息
     * @param customerQueryBean
     * @return
     */
    public PageInfo<CustomerInfo> queryList(CustomerQueryBean customerQueryBean){
        PortalUtil.defaultStartPage(customerQueryBean.getPageSize(), customerQueryBean.getPageSize());
        List<CustomerInfo> customerInfos = customerInfoMapper.selectCustomerInfo(customerQueryBean);
        return new PageInfo<>(customerInfos);
    }
    /* 报备状态 0-初始化 1-已报备 2-可报备 3-报备中*/


    /**
     * 查询客户明细
     * @param reportId
     * @param customerId
     * @return
     */
    public CustomerInfo queryInfo(Integer reportId, Integer customerId){
        return customerInfoMapper.queryReportInfo(customerId, reportId);
    }

    /**
     * 获取代理商信息
     * @param dealerId
     * @return
     */
    public CustomerInfo getDealerInfo(Integer dealerId){
        CustomerInfo dealerInfo = customerInfoMapper.selectDealerInfo(dealerId);
        BusinessUtil.notNull(dealerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        return mappingVO(dealerInfo);
    }

    private CustomerInfo mappingVO(CustomerInfo dealer){
        //授信额度初始值
        Zsdscredit zsdscredit = CallApiUtils.callECCCreditApi(dealer.getOutCode());
        dealer.setCredit(null == zsdscredit.getDmbtr()? BigDecimal.ZERO:zsdscredit.getDmbtr());
        dealer.setCreditUSE(null == zsdscredit.getZoccupy()? BigDecimal.ZERO:zsdscredit.getZoccupy());
        dealer.setCreditUnUSE(null == zsdscredit.getZremain()?BigDecimal.ZERO:zsdscredit.getZoccupy());
        return dealer;
    }

     /* 新建报备时 校验客户是否可报备
     * 1：代理商
     *      只能报备 新建客户和open客户
     * 2：销售
     *      报备 新建客户 和 open客户 以及 潜在客户
     * @param customerName
     * @param user
     * @return 客户信息
     */
    public CustomerInfo checkCustomerReport(String customerName, User user){
        //查询客户的报备状态
        CustomerInfo customerInfo = customerInfoMapper.selectByCustName(customerName.trim());
        if(null == customerInfo){
            return null;
        }
        //已报备客户不允许报备
        List<CustomerReport> reports = customerInfo.getCustomerReports();
        reports.forEach(e->{
            BusinessUtil.assertFlase(e.getReportStatus()==1, ErrorCodes.BusinessEnum.CUSTOMER_IS_REPORT);
        });

        //销售报备为被报备的潜在客户时带出客户信息
        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())&&customerInfo.getCustType()==2){
            return customerInfoMapper.queryReportInfo(customerInfo.getId(), user.getId());
        }else{
            //代理商报备 如果客户名称存在  带出客户id
            CustomerInfo reportCustomer = new CustomerInfo();
            reportCustomer.setId(customerInfo.getId());
            reportCustomer.setCustName(customerInfo.getCustName());
            return reportCustomer;
        }
    }

    /**
     * 报备客户
     * @param customerInfo
     * @param user
     */
    @Transactional
    public void reportCustomer(CustomerInfo customerInfo, User user){
        Integer reportDealer = user.getId();
        Integer reportSales = null;
        String businessType = Enums.CUSTOMER_BUSINESS_TYPE.mass_market.getCode();

        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
            businessType = Enums.CUSTOMER_BUSINESS_TYPE.account_market.getCode();
            reportSales = user.getId();
            reportDealer = null;
        }

        if(null == customerInfo.getId()){
            customerInfo.setBusinessType(businessType);
            customerInfo.setCustType(Enums.YES_NO.YES.getCode());
        }else{
            customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfo.getId());
            if(!user.getUserType().equals(Enums.USER_TYPE.internal)){
                BusinessUtil.assertFlase(customerInfo.getBusinessType().equals(Enums.CUSTOMER_BUSINESS_TYPE.account_market.getCode()), ErrorCodes.BusinessEnum.CUSTOMER_REPORT_ERROR);
            }
        }
        customerReport(customerInfo, reportDealer, reportSales);
        //TODO 同步C4
    }

    public void customerReport(CustomerInfo customerInfo, Integer reportDealer, Integer reportSales){
        CustomerReport customerReport = customerReportMapper.selectUserReport(customerInfo.getId(), reportDealer,reportSales);
        BusinessUtil.assertFlase(null != customerReport && customerReport.getReportStatus()!= Enums.CUSTOMER_APPROVE_STATUS.REJECT.getCode()
                , ErrorCodes.BusinessEnum.CUSTOMER_REPORT_ERROR1);

        saveCustomerInfo(customerInfo, reportDealer==null?reportSales:reportDealer);

        if(null == customerReport){
            customerReport = new CustomerReport();
        }
        customerReport.setCustomerId(customerInfo.getId());
        customerReport.setReportDealer(reportDealer);
        customerReport.setReportSales(reportSales);
        customerReport.setReportStatus(Enums.CUSTOMER_APPROVE_STATUS.WAIT_APPROVAL.getCode());
        customerReport.setReportTime(new Date());
        customerReport.setActive(1);
        customerReportMapper.insertSelective(customerReport);
        saveCustomerDetail(customerInfo, customerReport.getRepId(), reportDealer==null?reportSales:reportDealer);
    }

    /**
     * 审批报备
     * 通过 同时报备的其他驳回
     * 驳回  驳回
     * @param approvalBean
     */
    //TODO 邮件通知
    public void approval(ApprovalBean approvalBean){
        //通过
        if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            approvalYes(approvalBean);
        }else{
            approvalNo(approvalBean);
        }
    }

    //审批通过
    @Transactional
    private void approvalYes(ApprovalBean approvalBean){
        CustomerReport customerReport = customerReportMapper.selectByPrimaryKey(approvalBean.getReportId());
        customerReport.setApproveUser(approvalBean.getApproveUser());
        customerReport.setApproveRemark(approvalBean.getRemark());
        if(null != approvalBean.getDealerId()){
            customerReport.setReportDealer(approvalBean.getDealerId());
        }else{
            customerReport.setReportSales(approvalBean.getSalesId());
        }
        customerReport.setReportStatus(Enums.CUSTOMER_APPROVE_STATUS.REPORT.getCode());
        customerReportMapper.updateByPrimaryKey(customerReport);
        approvalNo(approvalBean);
    }

    //审批驳回
    @Transactional
    private void approvalNo(ApprovalBean approvalBean){
        if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            List<CustomerReport> customerReports = customerReportMapper.selectReject(approvalBean.getCustId(), approvalBean.getReportId());
            customerReports.forEach(e->{
                apprvalNo(e, approvalBean.getApproveUser(),approvalBean.getRemark());
            });
        }else{
            CustomerReport customerReport = customerReportMapper.selectByPrimaryKey(approvalBean.getReportId());
            apprvalNo(customerReport, approvalBean.getApproveUser(),approvalBean.getRemark());
        }
    }

    private void apprvalNo(CustomerReport customerReport, Integer approvalId, String remark){
        customerReport.setReportStatus(Enums.CUSTOMER_APPROVE_STATUS.REJECT.getCode());
        customerReport.setApproveUser(approvalId);
        customerReport.setApproveRemark(remark);
        customerReport.setApproveTime(new Date());
        customerReport.setActive(0);
        customerReportMapper.updateByPrimaryKeySelective(customerReport);
    }

    @Transactional
    public void updateCustomerInfo(CustomerInfo customerInfo, Integer userId){
        saveCustomerInfo(customerInfo, userId);
        saveCustomerDetail(customerInfo, null, userId);
    }

    //查询客户的内外部客户
    public CustomerShipBean selectDealerShip(Integer dealerId){
        List<CustCorporateRelationship> relationships = custCorporateRelationshipMapper.selectDealerShip(dealerId);
        CustomerShipBean shipBean = new CustomerShipBean();
        List<CustCorporateRelationship> resultShips = new ArrayList<>();
        relationships.forEach(e->{
            if(e.getCorporateType().equals(Enums.CUSTOMER_SHIP_TYPE.in_customer.getCode())){
                shipBean.setInShip(e);
            }else{
                resultShips.add(e);
            }
        });
        shipBean.setOutShips(resultShips);
        return shipBean;
    }

    private void saveCustomerInfo(CustomerInfo customerInfo, Integer userId){
        if(null == customerInfo.getId()){
            customerInfo.setCreateUser(userId);
            customerInfoMapper.insertSelective(customerInfo);
        }else{
            customerInfo.setUpdateUser(userId);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    private void saveCustomerDetail(CustomerInfo customerInfo, Integer reportId, Integer userId){
        saveContact(customerInfo.getCustomerContacts(), reportId, userId);
        saveProduct(customerInfo.getCustomerProducts(), reportId, userId);
        saveFile(customerInfo.getFiles(), reportId);
        saveBank(customerInfo.getCustBankInfo(), reportId, userId);
        saveRelationship(customerInfo.getRelationships(), reportId, userId);
        saveInvoice(customerInfo.getInvoiceInfos(), reportId, userId);
        saveSales(customerInfo.getSales(), reportId, userId);
        saveAddress(customerInfo.getAddresses(), reportId, userId);
        saveAccountTeam(customerInfo.getAccountTeams(), reportId, userId);
        saveAssetsInformation(customerInfo.getAssetsInformations());
        saveBusinessInformation(customerInfo.getBusinessInformations());
    }

    /*银行账号信息*/
    private void saveBank(CustBankInfo bankInfo, Integer reportId, Integer userId){
        if(null == bankInfo){
            return;
        }
        if(null == bankInfo.getBankId()){
            bankInfo.setReportId(reportId);
            bankInfo.setCreateUser(userId);
            custBankInfoMapper.insertSelective(bankInfo);
        }else{
            bankInfo.setUpdateUser(userId);
            custBankInfoMapper.updateByPrimaryKeySelective(bankInfo);
        }
    }

    /**
     * 季度更新 资产信息
     * @param assetsInformations
     */
    private void saveAssetsInformation(List<AssetsInformation> assetsInformations){
        assetsInformations.forEach(x->{
            if(null != x.getAsseteInfoId()){
                assetsInformationMapper.updateByPrimaryKeySelective(x);
            }else{
                assetsInformationMapper.insertSelective(x);
            }
        });
    }

    /**
     * 季度更新代理商 业务介绍
     * @param businessInformations
     */
    private void saveBusinessInformation(List<BusinessInformation> businessInformations){
        businessInformations.forEach(x->{
            if(null != x.getBusInfoId()){
                businessInformationMapper.updateByPrimaryKeySelective(x);
            }else{
                businessInformationMapper.insertSelective(x);
            }
        });
    }

    /*联系人信息*/
    private void saveContact(List<CustomerContact> customerContacts, Integer reportId, Integer userId){
        if(null == customerContacts || customerContacts.isEmpty()){
            return;
        }
        customerContacts.forEach(e->{
            if(null == e.getContactId()){
                e.setReportId(reportId);
                e.setInsertUser(userId);
                customerContactMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerContactMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*产品信息*/
    private void saveProduct(List<CustomerProduct> customerProducts, Integer reportId, Integer userId){
        if(null == customerProducts || customerProducts.isEmpty()){
            return;
        }
        customerProducts.forEach(e->{
            if(null == e.getProId()){
                e.setReportId(reportId);
                e.setInsertUser(userId);
                customerProductMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerProductMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*客户附件*/
    private void saveFile(List<CustomerFile> customerFiles, Integer reportId){
        if(null == customerFiles || customerFiles.isEmpty()){
            return;
        }
        customerFiles.forEach(e->{
            if(null == e.getFileId()){
                e.setReportId(reportId);
                customerFileMapper.insertSelective(e);
            }else{
                customerFileMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**关系**/
    private void saveRelationship(List<CustCorporateRelationship> relationships, Integer reportId, Integer userId){
        if(null == relationships || relationships.isEmpty()){
            return;
        }
        relationships.forEach(e->{
            if(null == e.getShipId()){
                e.setReportId(reportId);
                e.setCreateUser(userId);
                custCorporateRelationshipMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custCorporateRelationshipMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**开票信息**/
    private void saveInvoice(List<CustInvoiceInfo> invoiceInfos, Integer reportId, Integer userId){
        if(null == invoiceInfos || invoiceInfos.isEmpty()){
            return;
        }
        invoiceInfos.forEach(e->{
            if(null == e.getInvoiceId()){
                e.setReportId(reportId);
                e.setCreateUser(userId);
                custInvoiceInfoMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custInvoiceInfoMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**销售信息**/
    private void saveSales(List<CustSales> basicSales, Integer reportId, Integer userId){
        if(null == basicSales || basicSales.isEmpty()){
            return;
        }
        basicSales.forEach(e->{
            if(null == e.getSalesId()){
                e.setReportId(reportId);
                e.setCreateUser(userId);
                custSalesMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custSalesMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**地址信息**/
    private void saveAddress(List<CustomerAddress> addresses, Integer reportId, Integer userId){
        if(null == addresses || addresses.isEmpty()){
            return;
        }
        addresses.forEach(e->{
            if(null == e.getAddressId()){
                e.setReportId(reportId);
                e.setCrateUser(userId);
                customerAddressMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAddressMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*客户团队*/
    private void saveAccountTeam(List<CustomerAccountTeam> accountTeams, Integer reportId, Integer userId){
        if(null == accountTeams || accountTeams.isEmpty()){
            return;
        }
        accountTeams.forEach(e->{
            if(null == e.getTeamId()){
                e.setReportId(reportId);
                e.setCreateUser(userId);
                customerAccountTeamMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAccountTeamMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
}
