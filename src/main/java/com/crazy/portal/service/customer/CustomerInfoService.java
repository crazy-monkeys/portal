package com.crazy.portal.service.customer;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerShipBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.dealer.credit.Zsdscredit;
import com.crazy.portal.bean.customer.visitRecord.CustomerCodeEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.dao.cusotmer.*;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.entity.cusotmer.VisitRecord;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

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
    private CustAssetsInformationMapper assetsInformationMapper;
    @Resource
    private CustBusinessInformationMapper businessInformationMapper;
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
    private VisitRecordMapper visitRecordMapper;

    @Value("${file.path.root}")
    private String filePath;

    private static final String CUST_FILE_PATH = "custfile";

    /**
     * 分页查询客户信息  TODO 权限控制
     * @param customerQueryBean
     * @return
     *  报备状态 0-初始化 1-已报备 2-可报备 3-报备中
     */
    public PageInfo<CustomerInfo> queryList(CustomerQueryBean customerQueryBean){
        PortalUtil.defaultStartPage(customerQueryBean.getPageIndex(), customerQueryBean.getPageSize());
        List<CustomerInfo> customerInfos = customerInfoMapper.selectCustomerInfo(customerQueryBean);
        return new PageInfo<>(customerInfos);
    }

    /**
     * 客户查询页面
     * @param customerInfos
     * @param result
     * @return
     */


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

    /**
     * 获取代理商的客户信息
     * @param userId
     * @return
     */
    public CustomerInfo getDealerByUser(Integer userId){
        return customerInfoMapper.getDealerByUser(userId);
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
            customerInfo = new CustomerInfo();
            customerInfo.setCustName(customerName);
            return customerInfo;
        }
        //已报备客户不允许报备
        //List<CustomerReport> reports = customerInfo.getCustomerReports();
        /*reports.forEach(e->{
            BusinessUtil.assertFlase(e.getReportStatus()==3, ErrorCodes.BusinessEnum.CUSTOMER_IS_REPORT);
        });*/

        //销售报备为被报备的潜在客户时带出客户信息
        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())&&customerInfo.getCustType().equals("0")){
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
            //customerInfo.setCustType(String.valueOf(Enums.YES_NO.YES.getCode()));
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
        /*//CustomerReport customerReport = customerReportMapper.selectUserReport(customerInfo.getId(), reportDealer,reportSales);
        BusinessUtil.assertFlase(null != customerReport && customerReport.getReportStatus()!= Enums.CUSTOMER_APPROVE_STATUS.WAIT_APPROVAL.getCode()
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
   */ }

    /**
     * 审批报备
     * 通过 同时报备的其他驳回
     * 驳回  驳回
     * @param approvalBean
     */
    //TODO 邮件通知
    @Transactional
    public void approval(ApprovalBean approvalBean){
        //通过
        if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            approvalYes(approvalBean);
        }else{
            approvalNo(approvalBean);
        }
    }

    //审批通过
    private void approvalYes(ApprovalBean approvalBean){
       /* CustomerReport customerReport = customerReportMapper.selectByPrimaryKey(approvalBean.getReportId());
        customerReport.setApproveUser(approvalBean.getApproveUser());
        customerReport.setApproveRemark(approvalBean.getRemark());
        if(null != approvalBean.getDealerId()){
            customerReport.setReportDealer(approvalBean.getDealerId());
        }else{
            customerReport.setReportSales(approvalBean.getSalesId());
        }
        customerReport.setReportStatus(Enums.CUSTOMER_APPROVE_STATUS.APPROVAL.getCode());
        customerReportMapper.updateByPrimaryKey(customerReport);
        approvalNo(approvalBean);*/
    }

    //审批驳回
    private void approvalNo(ApprovalBean approvalBean){
        /*if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            List<CustomerReport> customerReports = customerReportMapper.selectReject(approvalBean.getCustId(), approvalBean.getReportId());
            customerReports.forEach(e->{
                apprvalNo(e, approvalBean.getApproveUser(),approvalBean.getRemark());
            });
        }else{
            CustomerReport customerReport = customerReportMapper.selectByPrimaryKey(approvalBean.getReportId());
            apprvalNo(customerReport, approvalBean.getApproveUser(),approvalBean.getRemark());
        }*/
    }

   /* private void apprvalNo(CustomerReport customerReport, Integer approvalId, String remark){
        customerReport.setReportStatus(Enums.CUSTOMER_APPROVE_STATUS.REJECT.getCode());
        customerReport.setApproveUser(approvalId);
        customerReport.setApproveRemark(remark);
        customerReport.setApproveTime(new Date());
        customerReport.setActive(0);
        customerReportMapper.updateByPrimaryKeySelective(customerReport);
    }*/

    @Transactional
    public void updateCustomerInfo(CustomerInfo customerInfo, Integer userId){
        saveCustomerInfo(customerInfo, userId);
        saveCustomerDetail(customerInfo, userId);
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

    private void saveCustomerDetail(CustomerInfo customerInfo, Integer userId){
        saveContact(customerInfo.getCustomerContacts(), customerInfo.getId(), userId);
        saveProduct(customerInfo.getCustomerProducts(), customerInfo.getId(), userId);
        saveFile(customerInfo.getFiles(), customerInfo.getId());
        saveBank(customerInfo.getCustBankInfo(), userId);
        saveRelationship(customerInfo.getRelationships(), customerInfo.getId(), userId);
        saveInvoice(customerInfo.getInvoiceInfos(), customerInfo.getId(), userId);
        saveSales(customerInfo.getSales(), customerInfo.getId(), userId);
        saveAddress(customerInfo.getAddresses(), customerInfo.getId(), userId);
        saveAccountTeam(customerInfo.getAccountTeams(), customerInfo.getId(), userId);
        saveAssetsInformation(customerInfo.getAssetsInformations());
        saveBusinessInformation(customerInfo.getBusinessInformations());
    }

    /*银行账号信息*/
    private void saveBank(CustBankInfo bankInfo, Integer userId){
        if(null == bankInfo){
            return;
        }
        if(null == bankInfo.getBankId()){
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
    private void saveAssetsInformation(List<CustAssetsInformation> assetsInformations){
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
    private void saveBusinessInformation(List<CustBusinessInformation> businessInformations){
        businessInformations.forEach(x->{
            if(null != x.getBusInfoId()){
                businessInformationMapper.updateByPrimaryKeySelective(x);
            }else{
                businessInformationMapper.insertSelective(x);
            }
        });
    }



    /*联系人信息*/
    private void saveContact(List<CustomerContact> customerContacts, Integer custId, Integer userId){
        if(null == customerContacts || customerContacts.isEmpty()){
            return;
        }
        customerContacts.forEach(e->{
            if(null == e.getContactId()){
                e.setCustId(custId);
                e.setInsertUser(userId);
                customerContactMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerContactMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*产品信息*/
    private void saveProduct(List<CustomerProduct> customerProducts, Integer custId, Integer userId){
        if(null == customerProducts || customerProducts.isEmpty()){
            return;
        }
        customerProducts.forEach(e->{
            if(null == e.getProId()){
                e.setCustId(custId);
                e.setInsertUser(userId);
                customerProductMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerProductMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*客户附件*/
    private void saveFile(List<CustomerFile> customerFiles, Integer custId){
        if(null == customerFiles || customerFiles.isEmpty()){
            return;
        }
        customerFiles.forEach(e->{
            if(null == e.getFileId()){
                e.setCustId(custId);
                customerFileMapper.insertSelective(e);
            }else{
                customerFileMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**关系**/
    private void saveRelationship(List<CustCorporateRelationship> relationships, Integer custId, Integer userId){
        if(null == relationships || relationships.isEmpty()){
            return;
        }
        relationships.forEach(e->{
            if(null == e.getShipId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                custCorporateRelationshipMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custCorporateRelationshipMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**开票信息**/
    private void saveInvoice(List<CustInvoiceInfo> invoiceInfos, Integer custId, Integer userId){
        if(null == invoiceInfos || invoiceInfos.isEmpty()){
            return;
        }
        invoiceInfos.forEach(e->{
            if(null == e.getInvoiceId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                custInvoiceInfoMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custInvoiceInfoMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**销售信息**/
    private void saveSales(List<CustSales> basicSales, Integer custId, Integer userId){
        if(null == basicSales || basicSales.isEmpty()){
            return;
        }
        basicSales.forEach(e->{
            if(null == e.getSalesId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                custSalesMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custSalesMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /**地址信息**/
    private void saveAddress(List<CustomerAddress> addresses, Integer custId, Integer userId){
        if(null == addresses || addresses.isEmpty()){
            return;
        }
        addresses.forEach(e->{
            if(null == e.getAddressId()){
                e.setCustId(custId);
                e.setCrateUser(userId);
                customerAddressMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAddressMapper.updateByPrimaryKeySelective(e);
            }
        });
    }
    /*客户团队*/
    private void saveAccountTeam(List<CustomerAccountTeam> accountTeams, Integer custId, Integer userId){
        if(null == accountTeams || accountTeams.isEmpty()){
            return;
        }
        accountTeams.forEach(e->{
            if(null == e.getTeamId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                customerAccountTeamMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAccountTeamMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    /**
     * 下载客户拜访模板
     * @param userId
     * @return
     */
    public Map<String, List<? extends BaseRowModel>> downloadTemplate(Integer userId){
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        List<CustomerInfo> custList = customerInfoMapper.selectNameAndCodeByUserId(userId);
        List<CustomerCodeEO> custCodeList = new ArrayList<>();
        custList.forEach(cust -> {
            CustomerCodeEO eo = new CustomerCodeEO();
            eo.setCustomerName(cust.getCustName());
            eo.setCustomerCode(cust.getInCode());
            custCodeList.add(eo);
        });
        List<VisitRecordEO> visitRecordList = new ArrayList<>();
        visitRecordList.add(new VisitRecordEO());
        resultMap.put("模板", visitRecordList);
        resultMap.put("客户", custCodeList);
        return resultMap;
    }

    /**
     * 查询客户拜访记录
     * @param bean
     * @return
     */
    public PageInfo<VisitRecord> selectVisitRecordPage(VisitRecordQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<VisitRecord> records = visitRecordMapper.selectByPage(bean);
        return new PageInfo<>(records);
    }

    /**
     * 上传客户拜访记录
     * @param files
     * @param userId
     * @throws Exception
     **/

    public void uploadVisitRecord(MultipartFile[] files, Integer userId) throws Exception{
        for (MultipartFile file : files) {
            List<Object> records = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1,VisitRecordEO.class));
            records.forEach(e->{
                try {
                    VisitRecord record = new VisitRecord();
                    BeanUtils.copyNotNullFields(e , record);
                    String excelVisitDate = BeanUtils.getFieldValueByName("visitDate", e).toString();
                    record.setVisitDate(DateUtil.getFlexibleDate(excelVisitDate));
                    record.setActive(Constant.ACTIVE);
                    record.setCreateUserId(userId);
                    record.setCreateTime(DateUtil.getCurrentTS());
                    visitRecordMapper.insertSelective(record);
                } catch (Exception ex) {
                    log.error("保存拜访记录异常", ex);
                }
            });

        }
    }

    /**
     * 文件上传
     * @param files
     * @return
     **/
    public List<FileVO> fileUpload(MultipartFile[] files){
        List<FileVO> fileList = FileUtil.upload(files, getCustFilePath());
        return fileList;
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(CUST_FILE_PATH);
    }

    public List<CustomerInfo> selectAllCustomer(){
        return customerInfoMapper.selectAllCustomer();
    }
}
