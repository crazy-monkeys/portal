package com.crazy.portal.service.customer;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerShipBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.bean.customer.wsdl.credit.Zsdscredit;
import com.crazy.portal.bean.customer.visitRecord.CustomerCodeEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.dao.cusotmer.*;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.entity.cusotmer.VisitRecord;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
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
    private CustAssetsInformationService custAssetsInformationService;
    @Resource
    private CustBusinessInformationService custBusinessInformationService;
    @Resource
    private CustomerContactService customerContactService;
    @Resource
    private CustomerProductService customerProductService;
    @Resource
    private CustomerFileService customerFileService;
    @Resource
    private CustBankInfoMapper custBankInfoMapper;
    @Resource
    private CustCorporateRelationshipService custCorporateRelationshipService;
    @Resource
    private CustInvoiceInfoService custInvoiceInfoService;
    @Resource
    private CustSalesService custSalesService;
    @Resource
    private CustomerAddressService customerAddressService;
    @Resource
    private CustomerAccountTeamService customerAccountTeamService;
    @Resource
    private CustomerStructureService customerStructureService;
    @Resource
    private VisitRecordMapper visitRecordMapper;
    @Resource
    private InternalUserMapper internalUserMapper;
    @Resource
    private CustQuotasService custQuotasService;
    @Resource
    private CustZrAccountTeamService custZrAccountTeamService;

    @Value("${file.path.root}")
    private String filePath;

    /**
     * 获取所有客户
     * @return
     */
    public List<CustomerInfo> selecAllCustomer(){
        return customerInfoMapper.selectAllCustomer();
    }

    /**
     * 分页查询客户信息  TODO 权限控制
     * @param customerQueryBean
     * @return
     *  报备状态 0-潜在客户 1-已报备 2-可报备 3-报备中
     *  1-客户查询 2-审批查询 3-报备查询
     */
    public PageInfo<CustomerInfo> queryList(CustomerQueryBean customerQueryBean, User user){
        PortalUtil.defaultStartPage(customerQueryBean.getPageIndex(), customerQueryBean.getPageSize());
        if(customerQueryBean.getQueryType()==3){
            if(user.getUserType().equals(Enums.USER_TYPE.internal)){
                customerQueryBean.setReportSales(user.getId());
            }else{
                customerQueryBean.setReportDealer(user.getId());
            }
        }else if(customerQueryBean.getQueryType()==2){
            //审批查询

        }else{
            //客户查询
        }
        List<CustomerInfo> customerInfos = customerInfoMapper.selectCustomer(customerQueryBean);
        return new PageInfo<>(customerInfos);
    }

    /**
     * 查询客户明细
     * @param customerId
     * @return
     */
    public CustomerInfo queryInfo(Integer customerId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        customerInfo.setCustomerProducts(customerProductService.selectByCustId(customerId));
        customerInfo.setAccountTeams(customerAccountTeamService.selectByCustId(customerId));
        customerInfo.setZrAccountTeams(custZrAccountTeamService.selectByCustId(customerId));
        return getCustDetail(customerInfo);
    }

    public void deleteCustomer(Integer custId){
        customerInfoMapper.updateCustomerInfo(custId);
    }

    /**
     * 获取代理商信息
     * @param dealerId
     * @return
     */
    public CustomerInfo getDealerInfo(Integer dealerId){
        CustomerInfo dealerInfo = customerInfoMapper.selectByPrimaryKey(dealerId);
        BusinessUtil.notNull(dealerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        mappingVO(dealerInfo);

        dealerInfo.setAssetsInformations(custAssetsInformationService.selectByCustId(dealerId));
        dealerInfo.setBusinessInformations(custBusinessInformationService.selectByCustId(dealerId));
        return getCustDetail(dealerInfo);
    }

    private CustomerInfo getCustDetail(CustomerInfo customerInfo){
        customerInfo.setFiles(customerFileService.selectByCustId(customerInfo.getId()));
        customerInfo.setCustomerContacts(customerContactService.selectByCustId(customerInfo.getId()));
        customerInfo.setCustBankInfo(custBankInfoMapper.selectByCustId(customerInfo.getId()));
        customerInfo.setRelationships(custCorporateRelationshipService.selectByCustId(customerInfo.getId()));
        customerInfo.setInvoiceInfos(custInvoiceInfoService.selectByCustId(customerInfo.getId()));
        customerInfo.setSales(custSalesService.selectByCustId(customerInfo.getId()));
        customerInfo.setAddresses(customerAddressService.selectByCustId(customerInfo.getId()));
        customerInfo.setCustStructure(customerStructureService.selectByCustId(customerInfo.getId()));
        customerInfo.setQuotas(custQuotasService.selectByCustId(customerInfo.getId()));
        return customerInfo;
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
        CustomerInfo tempCustomer = new CustomerInfo();
        tempCustomer.setCustName(customerName);
        //查询客户的报备状态
        List<CustomerInfo> customerInfos = customerInfoMapper.selectByCustName(customerName.trim());
        if(null == customerInfos || customerInfos.isEmpty()){
            return tempCustomer;
        }

        for(CustomerInfo customerInfo : customerInfos){
            //已报备客户不允许报备
            BusinessUtil.assertFlase(customerInfo.getCustType()==2, ErrorCodes.BusinessEnum.CUSTOMER_IS_APPEROVE);
            //报备中的客户不允许再次提交
            BusinessUtil.assertFlase(customerInfo.getApproveStatus()==Enums.CUSTOMER_APPROVE_STATUS.WAIT_APPROVAL.getCode() && customerInfo.getCreateUser()==user.getId(), ErrorCodes.BusinessEnum.CUSTOMER_IS_REPORT);

            //销售报备为被报备的潜在客户时带出客户信息
            if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())&&customerInfo.getCustType()==Enums.CUSTOMER_TYPE.WAIT_SUBMIT.getCode()){
                return queryInfo(customerInfo.getId());
            }
        }
        return tempCustomer;
    }

    /**
     * 报备客户
     * @param customerInfo
     * @param user
     */
    @Transactional
    public void report(CustomerInfo customerInfo, User user){
        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
            customerInfo.setBusinessType(Enums.CUSTOMER_BUSINESS_TYPE.account_market.getCode());
        }else{
            customerInfo.setBusinessType(Enums.CUSTOMER_BUSINESS_TYPE.mass_market.getCode());
        }
        customerInfo.setCustType(Enums.CUSTOMER_TYPE.WAIT_REPORT.getCode());
        customerInfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.WAIT_APPROVAL.getCode());
        customerInfo.setActive(1);
        customerInfo.setCreateUser(user.getId());
        customerInfoMapper.insertSelective(customerInfo);
        saveCustomerDetail(customerInfo, user.getId());
        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
            InternalUser internalUser = internalUserMapper.selectByUserId(user.getId());
            custZrAccountTeamService.updateTeam(customerInfo.getId(), user.getId(), internalUser);
        }else{
            CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(user.getDealerId());
            custCorporateRelationshipService.UpdateCustShip(customerInfo.getId(), user.getId(), dealer);
        }
        //TODO 同步C4 获得outcode
    }

    private void saveCustomerDetail(CustomerInfo customerInfo, Integer userId){
        saveRelationship(customerInfo.getRelationships(), customerInfo.getId(), userId);
        saveContact(customerInfo.getCustomerContacts(), customerInfo.getId(), userId);
        saveProduct(customerInfo.getCustomerProducts(), customerInfo.getId(), userId);
        saveFile(customerInfo.getFiles(), customerInfo.getId());
        saveBank(customerInfo.getCustBankInfo(), userId);
        saveInvoice(customerInfo.getInvoiceInfos(), customerInfo.getId(), userId);
        saveSales(customerInfo.getSales(), customerInfo.getId(), userId);
        saveAddress(customerInfo.getAddresses(), customerInfo.getId(), userId);
        saveAccountTeam(customerInfo.getAccountTeams(), customerInfo.getId(), userId);
        saveCustomerStructure(customerInfo.getCustStructure(), customerInfo.getId(), userId);
        saveQuotas(customerInfo.getQuotas(), customerInfo.getId(), userId);
        saveZrAccountTeams(customerInfo.getZrAccountTeams(), customerInfo.getId(), userId);
    }

    /**
     * 审批报备
     * 通过 同时报备的其他驳回
     * 驳回  驳回
     * @param approvalBean
     */
    //TODO 邮件通知
    @Transactional
    public void approval(ApprovalBean approvalBean, Integer userId){
        //通过
        if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            approvalYes(approvalBean, userId);
        }else{
            approvalNo(approvalBean, userId);
        }
    }

    /**
     * 审批通过
     * 自动驳回取消  逻辑无法实现
     * @param approvalBean
     */
    private void approvalYes(ApprovalBean approvalBean, Integer userId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(approvalBean.getCustId());
        if(null != customerInfo && customerInfo.getApproveStatus()!=Enums.CUSTOMER_APPROVE_STATUS.APPROVAL.getCode() && customerInfo.getCustType()!=Enums.CUSTOMER_TYPE.WAIT_APPROVAL.getCode()){
            customerInfo.setCustType(Enums.CUSTOMER_TYPE.WAIT_APPROVAL.getCode());
            customerInfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.APPROVAL.getCode());
            if(null != approvalBean.getSalesId()){
                InternalUser internalUser = internalUserMapper.selectByPrimaryKey(approvalBean.getSalesId());
                custZrAccountTeamService.updateTeam(customerInfo.getId(), userId, internalUser);
            }else if (null != approvalBean.getDealerId()){
                CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(approvalBean.getCustId());
                custCorporateRelationshipService.UpdateCustShip(customerInfo.getId(), userId, dealer);
            }
            customerInfo.setApproveUser(userId);
            customerInfo.setApproveRemark(approvalBean.getApprovalRemark());
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    //审批驳回
    private void approvalNo(ApprovalBean approvalBean, Integer userId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(approvalBean.getCustId());
        if(null != customerInfo){
            customerInfo.setApproveUser(userId);
            customerInfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.REJECT.getCode());
            customerInfo.setApproveRemark(approvalBean.getApprovalRemark());
            customerInfo.setActive(0);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    @Transactional
    public void updateDealerInfo(CustomerInfo customerInfo, Integer userId){
        saveCustomerInfo(customerInfo, userId);
        saveDealerDetail(customerInfo, userId);
    }

    /**
     * 查询客户的内外部客户
     * @param dealerId
     * @return
     */
    public CustomerShipBean selectDealerShip(Integer dealerId){
        List<CustCorporateRelationship> relationships = custCorporateRelationshipService.selectByCustId(dealerId);
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
            customerInfo.setActive(1);
            customerInfoMapper.insertSelective(customerInfo);
        }else{
            customerInfo.setUpdateUser(userId);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    private void saveDealerDetail(CustomerInfo customerInfo, Integer userId){
        saveContact(customerInfo.getCustomerContacts(), customerInfo.getId(), userId);
        saveFile(customerInfo.getFiles(), customerInfo.getId());
        saveBank(customerInfo.getCustBankInfo(), userId);
        saveRelationship(customerInfo.getRelationships(), customerInfo.getId(), userId);
        saveInvoice(customerInfo.getInvoiceInfos(), customerInfo.getId(), userId);
        saveSales(customerInfo.getSales(), customerInfo.getId(), userId);
        saveAddress(customerInfo.getAddresses(), customerInfo.getId(), userId);
        saveAssetsInformation(customerInfo.getAssetsInformations(), customerInfo.getId());
        saveBusinessInformation(customerInfo.getBusinessInformations(), customerInfo.getId());
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
    private void saveAssetsInformation(List<CustAssetsInformation> assetsInformations, Integer custId){
        List<CustAssetsInformation> results = custAssetsInformationService.selectByCustId(custId);
        custAssetsInformationService.deleteByCustId(assetsInformations, results, custId);
        custAssetsInformationService.saveOrUpdate(assetsInformations, custId);
    }

    /**
     * 季度更新代理商 业务介绍
     * @param businessInformations
     */
    private void saveBusinessInformation(List<CustBusinessInformation> businessInformations, Integer custId){
        List<CustBusinessInformation> results = custBusinessInformationService.selectByCustId(custId);
        custBusinessInformationService.deleteByCustId(businessInformations, results, custId);
        custBusinessInformationService.saveOrUpdate(businessInformations, custId);
    }

    /*联系人信息*/
    private void saveContact(List<CustomerContact> customerContacts, Integer custId, Integer userId){
        List<CustomerContact> results = customerContactService.selectByCustId(custId);
        customerContactService.deleteByCustId(customerContacts, results, custId);
        customerContactService.saveOrUpdate(customerContacts, custId, userId);
    }

    /*产品信息*/
    private void saveProduct(List<CustomerProduct> customerProducts, Integer custId, Integer userId){
        List<CustomerProduct> results = customerProductService.selectByCustId(custId);
        customerProductService.deleteByCustId(customerProducts, results, custId);
        customerProductService.saveOrUpdate(customerProducts, custId, userId);
    }
    /*客户附件*/
    private void saveFile(List<CustomerFile> customerFiles, Integer custId){
        List<CustomerFile> results = customerFileService.selectByCustId(custId);
        customerFileService.deleteByCustId(customerFiles, results, custId);
        customerFileService.saveOrUpdate(customerFiles, custId);
    }
    /**关系**/
    private void saveRelationship(List<CustCorporateRelationship> relationships, Integer custId, Integer userId){
        List<CustCorporateRelationship> results = custCorporateRelationshipService.selectByCustId(custId);
        custCorporateRelationshipService.deleteByCustId(relationships, results, custId);
        custCorporateRelationshipService.saveOrUpdate(relationships, custId, userId);
    }
    /**开票信息**/
    private void saveInvoice(List<CustInvoiceInfo> invoiceInfos, Integer custId, Integer userId){
        List<CustInvoiceInfo> results = custInvoiceInfoService.selectByCustId(custId);
        custInvoiceInfoService.deleteByCustId(invoiceInfos, results, custId);
        custInvoiceInfoService.saveOrUpdate(invoiceInfos, custId, userId);
    }
    /**销售信息**/
    private void saveSales(List<CustSales> basicSales, Integer custId, Integer userId){
        List<CustSales> results = custSalesService.selectByCustId(custId);
        custSalesService.deleteByCustId(basicSales, results, custId);
        custSalesService.saveOrUpdate(basicSales, custId, userId);
    }
    /**地址信息**/
    private void saveAddress(List<CustomerAddress> addresses, Integer custId, Integer userId){
        List<CustomerAddress> results = customerAddressService.selectByCustId(custId);
        customerAddressService.deleteByCustId(addresses, results, custId);
        customerAddressService.saveOrUpdate(addresses, custId, userId);
    }
    /*客户团队*/
    private void saveAccountTeam(List<CustomerAccountTeam> accountTeams, Integer custId, Integer userId){
        List<CustomerAccountTeam> results = customerAccountTeamService.selectByCustId(custId);
        customerAccountTeamService.deleteByCustId(accountTeams, results, custId);
        customerAccountTeamService.saveOrUpdate(accountTeams, custId, userId);
    }
    /**客户股权结构**/
    private void saveCustomerStructure(List<CustomerStructure> customerStructures, Integer custId, Integer userId){
        List<CustomerStructure> results = customerStructureService.selectByCustId(custId);
        customerStructureService.deleteByCustId(customerStructures,results, custId);
        customerStructureService.saveOrUpdate(customerStructures,custId,userId);
    }

    private void saveQuotas(List<CustSalesQuota> custSalesQuotas, Integer custId, Integer userId){
        List<CustSalesQuota> results = custQuotasService.selectByCustId(custId);
        custQuotasService.deleteByCustId(custSalesQuotas,results, custId);
        custQuotasService.saveOrUpdate(custSalesQuotas,custId,userId);
    }

    private void saveZrAccountTeams(List<CustZrAccountTeam> custZrAccountTeams, Integer custId, Integer userId){
        List<CustZrAccountTeam> results = custZrAccountTeamService.selectByCustId(custId);
        custZrAccountTeamService.deleteByCustId(custZrAccountTeams,results, custId);
        custZrAccountTeamService.saveOrUpdate(custZrAccountTeams,custId,userId);
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
    public CustomerFile fileUpload(MultipartFile files, Integer customerId, Integer index){
        CustomerFile file = customerFileService.saveOrUpdate(files, customerId);
        file.setIndex(index);
        return file;
    }
}
