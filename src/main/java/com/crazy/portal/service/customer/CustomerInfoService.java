package com.crazy.portal.service.customer;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerShipBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.bean.customer.basic.DealerCreditVO;
import com.crazy.portal.bean.customer.visitRecord.CustomerCodeEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.bean.customer.wsdl.credit.Zsdscredit;
import com.crazy.portal.bean.customer.wsdl.customer.detail.*;
import com.crazy.portal.bean.customer.wsdl.customer.info.*;
import com.crazy.portal.bean.customer.wsdl.visits.*;
import com.crazy.portal.bean.customer.wsdl.visits1.AppointmentActivityMaintainConfirmationBundleMessageSyncV1;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustBankInfoMapper;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.cusotmer.VisitRecordMapper;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.InternalUserService;
import com.crazy.portal.service.system.SysParamService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    @Resource
    private SysParamService sysParamService;
    @Resource
    private InternalUserService internalUserService;

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
            if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
                InternalUser internalUser = internalUserMapper.selectUserByName(user.getLoginName());
                BusinessUtil.assertFlase(null == user,ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS);
                customerQueryBean.setReportSales(internalUser.getUserNo());
            }else{
                CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(user.getDealerId());
                customerQueryBean.setReportDealer(dealer.getInCode());
            }
        }else if(customerQueryBean.getQueryType()==2){
            //审批查询
            if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
                OrganizationalStructure org = internalUserService.getUserOrg(user.getLoginName());
                if(null != org&&org.getSeq()==1001012){
                    customerQueryBean.setBusinessType("A03");
                }else if(null != org&&org.getSeq()==1001011){
                    customerQueryBean.setBusinessType("A02");
                }else{
                    throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_ORG_ERROR);
                }
            }
        }else{
            if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
                //客户查询 销售查询 客户销售为自己或自己战队人员
                InternalUser internalUser = internalUserMapper.selectUserByName(user.getLoginName());
                if(null != internalUser){
                    List<String> sales = internalUserService.getSalesTeam(internalUser);
                    customerQueryBean.setSalesTeam(sales);
                }
            }else{
                CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(user.getDealerId());
                customerQueryBean.setReportDealer(dealer.getInCode());
            }

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
     * 获取所有代理商
     */
    public List<CustomerInfo> getDealerList(){
        return customerInfoMapper.selectDealerList();
    }

    /**获取代理商的关联公司**/
    public List<CustomerInfo> getDealerShip(Integer custId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(custId);
        if(null == customerInfo || !customerInfo.getBusinessType().equals(Enums.CUSTOMER_BUSINESS_TYPE.dealer.getCode())){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_NO_DEALER);
        }
        LinkedList<CustomerInfo> dealerShips = customerInfoMapper.selectDealerShip(custId);
        dealerShips.addFirst(customerInfo);
        return dealerShips;
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

    public DealerCreditVO getDealerCredit(Integer custId){
        CustomerInfo customerInfo = customerInfoMapper.getDealerByUser(custId);
        if(null == customerInfo || !customerInfo.getBusinessType().equals(Enums.CUSTOMER_BUSINESS_TYPE.dealer.getCode())){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_NO_DEALER);
        }
        Zsdscredit zsdscredit = CallApiUtils.callECCCreditApi(customerInfo.getOutCode());
        DealerCreditVO vo = new DealerCreditVO();
        vo.setCredit(null == zsdscredit.getDmbtr()? BigDecimal.ZERO:zsdscredit.getDmbtr());
        vo.setCreditUSE(null == zsdscredit.getZoccupy()? BigDecimal.ZERO:zsdscredit.getZoccupy());
        vo.setCreditUnUSE(null == zsdscredit.getZremain()?BigDecimal.ZERO:zsdscredit.getZoccupy());
        return vo;
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
    @OperationLog
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
            InternalUser internalUser = internalUserMapper.selectUserByName(user.getLoginName());
            custZrAccountTeamService.updateTeam(customerInfo.getId(), internalUser.getUserNo());
        }else{
            CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(user.getDealerId());
            custCorporateRelationshipService.UpdateCustShip(customerInfo.getId(), user.getId(), dealer);
        }
    }

    @OperationLog
    @Transactional
    public void updateCustomerInfo(CustomerInfo cust, User user){
        CustomerInfo customerinfo = customerInfoMapper.selectByPrimaryKey(cust.getId());
        if(null == customerinfo){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        }else{
            cust.setId(customerinfo.getId());
            mappingCustomerInfo(cust, customerinfo);
            customerinfo.setUpdateUser(user.getId());
            customerInfoMapper.updateByPrimaryKeySelective(customerinfo);
        }
        saveCustomerDetail(cust, user.getId());
        customerInfoSync(cust, "02");
    }

    private void mappingCustomerInfo(CustomerInfo cust, CustomerInfo customerInfo){
        customerInfo.setCustName(cust.getCustName());
        if(StringUtil.isNotEmpty(cust.getCustEnName())){
            customerInfo.setCustEnName(cust.getCustEnName());
        }
        if(StringUtil.isNotEmpty(cust.getCustAbbreviation())){
            customerInfo.setCustAbbreviation(cust.getCustAbbreviation());
        }
        if(StringUtil.isNotEmpty(cust.getBusinessType())){
            customerInfo.setBusinessType(cust.getBusinessType());
        }
        if(null != cust.getIsLicense()){
            customerInfo.setIsLicense(Integer.valueOf(cust.getIsLicense()));
        }
        if(StringUtil.isNotEmpty(cust.getCustRole())){
            customerInfo.setCustRole(cust.getCustRole());
        }
        if(StringUtil.isNotEmpty(cust.getCustMobile())){
            customerInfo.setCustMobile(cust.getCustMobile());
        }
        if(StringUtil.isNotEmpty(cust.getCustEmail())){
            customerInfo.setCustEmail(cust.getCustEmail());
        }
        if(StringUtil.isNotEmpty(cust.getCustWeb())){
            customerInfo.setCustWeb(cust.getCustWeb());
        }
        if(null != cust.getIsWhite()){
            customerInfo.setIsWhite(Integer.valueOf(cust.getIsWhite()));
        }
        if(StringUtil.isNotEmpty(cust.getRegistTime())){
            customerInfo.setRegistTime(cust.getRegistTime());
        }
        if(null != cust.getCorportaeAssets()){
            customerInfo.setCorportaeAssets(cust.getCorportaeAssets());
        }
        if(null != cust.getStaffNumber()){
            customerInfo.setStaffNumber(cust.getStaffNumber());
        }
        if(null != cust.getDevelopersNumber()){
            customerInfo.setDevelopersNumber(cust.getDevelopersNumber());
        }
        if(StringUtil.isNotEmpty(cust.getBusinessIntroduction())){
            customerInfo.setBusinessIntroduction(cust.getBusinessIntroduction());
        }
        if(StringUtil.isNotEmpty(cust.getAdvantagesIntroduction())){
            customerInfo.setAdvantagesIntroduction(cust.getAdvantagesIntroduction());
        }
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
        //saveAccountTeam(customerInfo.getAccountTeams(), customerInfo.getId(), userId);
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
    @OperationLog
    @Transactional
    public void approval(ApprovalBean approvalBean, Integer userId){
        //通过
        if(Enums.YES_NO.YES.getCode() == approvalBean.getApprovalType()){
            CustomerInfo customerInfo = queryInfo(approvalBean.getCustId());
            approvalYes(approvalBean, userId, customerInfo);
            customerInfo = queryInfo(approvalBean.getCustId());
            customerInfoSync(customerInfo, "01");
        }else{
            approvalNo(approvalBean, userId);
        }
    }

    private void customerInfoSync(CustomerInfo customerInfo, String type){
        CustomerInfoCreate create = syncCustomerInfo(customerInfo, type);
        String c4cId = CallApiUtils.callC4cCustomerInfo(create);
        CustomerDetailCreate detail = syncCustomerDetail(customerInfo, c4cId);
        CallApiUtils.callC4cCustomerDetail(detail);
        //查询eccid
        customerInfoMapper.updateC4CId(customerInfo.getId(), c4cId);
    }

    /**
     * 审批通过
     * 自动驳回取消  逻辑无法实现
     * @param approvalBean
     */
    private void approvalYes(ApprovalBean approvalBean, Integer userId, CustomerInfo customerInfo){
        if(null != customerInfo && customerInfo.getApproveStatus()!=Enums.CUSTOMER_APPROVE_STATUS.APPROVAL.getCode() && customerInfo.getCustType()!=Enums.CUSTOMER_TYPE.WAIT_APPROVAL.getCode()){
            customerInfo.setCustType(Enums.CUSTOMER_TYPE.WAIT_APPROVAL.getCode());
            customerInfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.APPROVAL.getCode());
            if(null != approvalBean.getSalesId()){
                custZrAccountTeamService.updateTeam(customerInfo.getId(), approvalBean.getSalesId());
            }else if (null != approvalBean.getDealerId()){
                CustomerInfo dealer = customerInfoMapper.selectByPrimaryKey(approvalBean.getCustId());
                custCorporateRelationshipService.UpdateCustShip(customerInfo.getId(), userId, dealer);
            }
            customerInfo.setApproveUser(userId);
            customerInfo.setApproveRemark(approvalBean.getApprovalRemark());
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    private CustomerDetailCreate syncCustomerDetail(CustomerInfo customerinfo, String c4cId){
        CustomerDetail detail = new CustomerDetail();
        detail.setCustomerID(c4cId);

        productMapping(detail, customerinfo.getCustomerProducts()==null?new ArrayList<>():customerinfo.getCustomerProducts());
        businessMapping(detail, customerinfo.getAssetsInformations()==null?new ArrayList<>():customerinfo.getAssetsInformations());
        businessInfoMationsMapping(detail, customerinfo.getBusinessInformations()==null?new ArrayList<>():customerinfo.getBusinessInformations());
        shareholdingMapping(detail, customerinfo.getCustStructure()==null?new ArrayList<>():customerinfo.getCustStructure());
        custBusinessMapping(detail, customerinfo.getQuotas()==null?new ArrayList<>():customerinfo.getQuotas());

        VisitCreateHeader header = new VisitCreateHeader();
        CustomerDetailContent content = new CustomerDetailContent(header, detail);
        CustomerDetailBody body = new CustomerDetailBody(content);
        return new CustomerDetailCreate(body);
    }

    private CustomerInfoCreate syncCustomerInfo(CustomerInfo customerInfo, String type){
        Customer customer = new Customer();
        if(type.equals("02")){
            customer.setInternalID(customerInfo.getInCode());
        }
        customer.setActionCode(type);

        Organisation organisation = new Organisation();
        organisation.setFirstLineName(customerInfo.getCustName());
        customer.setOrganisation(organisation);

        customer.setAbbreviation(customerInfo.getCustAbbreviation());
        customer.setType(customerInfo.getBusinessType());
        String license = customerInfo.getIsLicense()==null?"Y":customerInfo.getIsLicense()==Enums.YES_NO.YES.getCode()?"Y":"N";
        customer.setIsLicenseAccount(license);
        customer.setAccountRole(customerInfo.getCustRole());

        BlockingReasons blockingReasons = new BlockingReasons();
        //创建时默认都是否
        blockingReasons.setOrderBlockingReasonCode("01");
        blockingReasons.setDeliveryBlockingReasonCode("01");
        blockingReasons.setBillingBlockingReasonCode("02");
        blockingReasons.setSalesSupportBlockingIndicator("true");
        customer.setBlockingReasons(blockingReasons);

        customer.setRegistrationDate(customerInfo.getRegistTime());
        customer.setAdvantagesIntroduction(customerInfo.getAdvantagesIntroduction());
        if(null != customerInfo.getCorportaeAssets()){
            customer.setCorporateAssets(customerInfo.getCorportaeAssets().toString());
        }
        if(null != customerInfo.getStaffNumber()){
            customer.setStaffNumber(customerInfo.getStaffNumber().toString());
        }
        if(null != customerInfo.getDevelopersNumber()){
            customer.setDevelopersNumber(customerInfo.getDevelopersNumber().toString());
        }
        customer.setBusinessintroduction(customerInfo.getBusinessIntroduction());

        //联系人
        contanctMapping(customer, customerInfo.getCustomerContacts()==null?new ArrayList<>():customerInfo.getCustomerContacts());
        //银行
        bankMapping(customer, customerInfo.getCustBankInfo());
        //地址
        addressMapping(customer, customerInfo.getAddresses()==null?new ArrayList<>():customerInfo.getAddresses());
        //关系
        shipMapping(customer, customerInfo.getRelationships()==null?new ArrayList<>():customerInfo.getRelationships());
        //开票信息
        invoiceMapping(customer, customerInfo.getInvoiceInfos()==null?new ArrayList<>():customerInfo.getInvoiceInfos());
        //zr团队
        zrAccountTeamMapping(customer, customerInfo.getZrAccountTeams()==null?new ArrayList<>():customerInfo.getZrAccountTeams());

        VisitCreateHeader header = new VisitCreateHeader();
        CustomerInfoContent customerContent = new CustomerInfoContent(header, customer);

        CustomerInfoBody customerInfoBody = new CustomerInfoBody(customerContent);
        CustomerInfoCreate create = new CustomerInfoCreate(customerInfoBody);
        return create;
    }

    private void shareholdingMapping(CustomerDetail detail, List<CustomerStructure> structures){
        List<ShareholdingInformation> shareholdingInformations = new ArrayList<>();
        structures.forEach(e->{
            ShareholdingInformation shareholdingInformation = new ShareholdingInformation();
            shareholdingInformation.setZSuperiorShareholder(e.getStrOne());
            shareholdingInformation.setZShareholder(e.getStrName());//gudong
            shareholdingInformation.setZEquityRatio(String.valueOf(e.getStrValue()));//bili
            shareholdingInformation.setZNatureOfShareholder(e.getStrTwo());//xinzhi
            shareholdingInformation.setZNatureOfCompany(e.getStrThree()); //gongsixinzhi
            shareholdingInformation.setZIsManager(e.getStrFour()); // shifouguanli
            shareholdingInformation.setZDepartment(e.getStrFive()); //bumen
            shareholdingInformation.setZTitle(e.getStrSix()); //zhiwu

            shareholdingInformations.add(shareholdingInformation);
        });
        detail.setShareholdingInformation(shareholdingInformations);
    }

    private void businessInfoMationsMapping(CustomerDetail detail, List<CustBusinessInformation> businessInformations){
        List<BusinessIntroduction> businessIntroductions = new ArrayList<>();
        businessInformations.forEach(e->{
            BusinessIntroduction businessIntroduction = new BusinessIntroduction();
            businessIntroduction.setYear(e.getBusinessYear());
            businessIntroduction.setProductLine1(e.getProductLine());
            businessIntroduction.setRevenuePL1(String.valueOf(e.getRevenuePlOne()));
            businessIntroduction.setRevenuePL2(String.valueOf(e.getRevenuePlTwo()));
            businessIntroduction.setRevenuePL3(String.valueOf(e.getRevenuePlThree()));

            businessIntroductions.add(businessIntroduction);
        });
        detail.setBusinessIntroductions(businessIntroductions);
    }

    private void productMapping(CustomerDetail detail, List<CustomerProduct> products){
        List<ProductInfo> productInfos = new ArrayList<>();
        products.forEach(e->{
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductCode(e.getProduct());
            //productInfo.setCurrentYear();
            productInfo.setCurrentMonth(e.getPMonth());
            productInfo.setExpectedShipments1(e.getPNumberOne()==null?"0":String.valueOf(e.getPNumberOne()));
            productInfo.setExpectedShipments2(e.getPNumberTwo()==null?"0":String.valueOf(e.getPNumberTwo()));
            productInfo.setExpectedShipments3(e.getPNumberThree()==null?"0":String.valueOf(e.getPNumberThree()));
            productInfo.setExpectedShipments4(e.getPNumberFour()==null?"0":String.valueOf(e.getPNumberFour()));
            productInfo.setExpectedShipments5(e.getPNumberFive()==null?"0":String.valueOf(e.getPNumberFive()));
            productInfo.setExpectedShipments6(e.getPNumberSix()==null?"0":String.valueOf(e.getPNumberSix()));
            productInfos.add(productInfo);
        });
        detail.setProductInfo(productInfos);
    }

    public void businessMapping(CustomerDetail detail, List<CustAssetsInformation> assetsInformations){
        List<AssetInfo> assetInfos = new ArrayList<>();
        assetsInformations.forEach(e->{
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setYear(e.getAssetsYear());
            assetInfo.setSeason(e.getAssetsSeason());
            assetInfo.setTotalAssets(String.valueOf(e.getAssetsTotal()));
            assetInfo.setNetAssets(String.valueOf(e.getAssetsNet()));
            assetInfo.setRevenue(String.valueOf(e.getRevenue()));
            assetInfo.setTotalStaff(String.valueOf(e.getTotalStaff()));

            assetInfos.add(assetInfo);
        });

        detail.setAssetInfo(assetInfos);
    }

    public void custBusinessMapping(CustomerDetail detail, List<CustSalesQuota> custSalesQuotas){
        List<AssetInfo> assetInfos = new ArrayList<>();
        custSalesQuotas.forEach(e->{
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setYear(e.getSalesYear());
            assetInfo.setRevenue(String.valueOf(e.getSalesNumber()));

            assetInfos.add(assetInfo);
        });

        detail.setAssetInfo(assetInfos);
    }

    private void zrAccountTeamMapping(Customer customer, List<CustZrAccountTeam> zrAccountTeams){
        List<DirectResponsibility> durects = new ArrayList<>();
        zrAccountTeams.forEach(e->{
            DirectResponsibility directResponsibility = new DirectResponsibility();
            directResponsibility.setEmployeeID(e.getEmployeeId());
            directResponsibility.setPartyRoleCode(e.getRoleType());

            durects.add(directResponsibility);
        });
        customer.setDirectResponsibility(durects);
    }

    private void invoiceMapping(Customer customer, List<CustInvoiceInfo> invoiceInfos){
        invoiceInfos.forEach(e->{
            customer.setPurchasingUnit(e.getPurchasingUnit());
            customer.setShippingAddress(e.getShippingAddress());
            customer.setPhone(e.getShippingMobile());
            customer.setTaxpayerRegistrationNumber(e.getTaxpayerRegistrationNumber());
            customer.setCurrency(e.getCurrency());
        });
    }

    private void shipMapping(Customer customer, List<CustCorporateRelationship> relationships){
        List<Relationship> relationshipList = new ArrayList<>();
        relationships.forEach(e->{
            Relationship relationship = new Relationship();
            relationship.setRelationshipBusinessPartnerInternalID(e.getCorporateId().toString());
            relationship.setRoleCode(e.getCorporateType());

            relationshipList.add(relationship);
        });
        customer.setRelationship(relationshipList);
    }

    private void addressMapping(Customer customer, List<CustomerAddress> customerAddress){
        List<AddressInformation> addressInformation = new ArrayList<>();
        customerAddress.forEach(e->{
            AddressInformation information  = new AddressInformation();
            information.setAddressType(e.getAddressType());

            Address address = new Address();
            PostalAddress postalAddress = new PostalAddress();
            postalAddress.setCountryCode(e.getCountry().substring(0,e.getCountry().indexOf(",")));
            postalAddress.setCityName(e.getCountry().substring(e.getCountry().indexOf(",")+1));
            postalAddress.setStreetName(e.getDistrict());
            address.setPostalAddress(postalAddress);

            Telephone telephone = new Telephone();
            telephone.setFormattedNumberDescription("+86 "+e.getMobile());
            address.setTelephone(telephone);

            Email email = new Email();
            email.setURI(e.getEamil());
            address.setEmail(email);
            information.setAddress(address);
            addressInformation.add(information);
        });
        customer.setAddressInformation(addressInformation);
    }

    private void bankMapping(Customer customer, CustBankInfo bankInfo){
        customer.setBankName(bankInfo.getBankName());
        customer.setBankAddress(bankInfo.getBankAddress());
        customer.setAccount(bankInfo.getBankAccount());
        customer.setBIC(bankInfo.getBankBic());
    }

    private void contanctMapping(Customer customer, List<CustomerContact> contacts){
        List<ContactPerson> contactPersonList = new ArrayList<>();
        contacts.forEach(e->{
            ContactPerson person = new ContactPerson();
            person.setObjectNodeSenderTechnicalID(e.getContactId().toString());
            person.setBusinessPartnerFunctionTypeCode(e.getType());
            person.setFamilyName(e.getContactName());

           if(StringUtil.isNotEmpty(e.getMobile())){
               WorkplaceTelephone workplaceTelephone = new WorkplaceTelephone();
               workplaceTelephone.setFormattedNumberDescription("+86 "+e.getMobile());
               person.setWorkplaceTelephone(workplaceTelephone);
           }

            WorkplaceEmail email1 = new WorkplaceEmail();
            email1.setURI(e.getEmail());
            person.setWorkplaceEmail(email1);

            person.setWorkplaceDepartmentName(e.getDepartment());
            person.setSecondaryDept(e.getSubDepartment());
            person.setBusinessPartnerFunctionTypeCode(e.getPosition());
            contactPersonList.add(person);
        });
        customer.setContactPerson(contactPersonList);
    }

    //审批驳回
    private void approvalNo(ApprovalBean approvalBean, Integer userId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(approvalBean.getCustId());
        if(null != customerInfo){
            customerInfo.setApproveUser(userId);
            customerInfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.REJECT.getCode());
            customerInfo.setApproveRemark(approvalBean.getApprovalRemark());
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        }
    }

    @OperationLog
    @Transactional
    public void updateDealerInfo(CustomerInfo customerInfo, Integer userId){
        saveCustomerInfo(customerInfo, userId);
        saveDealerDetail(customerInfo, userId);
        //TODO 修改代理商信息
        customerInfoSync(customerInfo, "02");
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
        List<CustomerAddress> addresses = new ArrayList<>();
        for(CustomerAddress address : customerInfo.getAddresses()){
            if(StringUtil.isNotEmpty(address.getCountry())){
                List<String> st = JSON.parseArray(address.getCountry(),String.class);
                String contact = "";
                for(String s : st){
                    contact += s+",";
                }
                address.setCountry(contact.substring(0,contact.lastIndexOf(",")));
                addresses.add(address);
            }
        }
        saveAddress(addresses, customerInfo.getId(), userId);
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
    @OperationLog
    public List<VisitRecord> uploadVisitRecord(MultipartFile[] files, Integer userId) throws Exception{
        List<VisitRecord> results = new ArrayList<>();
        for (MultipartFile file : files) {
            List<Object> records = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1,VisitRecordEO.class));
            for(Object e : records){
                try {
                    VisitRecord record = new VisitRecord();
                    BeanUtils.copyNotNullFields(e , record);
                    String excelVisitDate = BeanUtils.getFieldValueByName("visitDate", e).toString();
                    record.setVisitDate(DateUtil.getFlexibleDate(excelVisitDate));
                    record.setActive(Constant.DELETE);
                    record.setCreateUserId(userId);
                    record.setCreateTime(DateUtil.getCurrentTS());
                    visitRecordMapper.insertSelective(record);
                    results.add(record);
                }catch (BusinessException be){
                    throw be;
                }catch (Exception ex) {
                    log.error("保存拜访记录异常", ex);
                }
            }
        }
        return results;
    }

    public void approve(List<Integer> ids, Integer dealerId){
        CustomerInfo dealerInfo = customerInfoMapper.selectByPrimaryKey(dealerId);
        ids.forEach(e->{
            VisitRecord visitRecord = visitRecordMapper.selectByPrimaryKey(e);
            VisitCreate create = getVisitsRequest(visitRecord, dealerInfo.getInCode());
            AppointmentActivityMaintainConfirmationBundleMessageSyncV1 response = CallApiUtils.callC4cVisits(create);
            if(null != response && !response.getAppointmentActivity().isEmpty() && null != response.getAppointmentActivity().get(0).getID()){
                visitRecordMapper.approve(e, response.getAppointmentActivity().get(0).getID().getValue());
            }
        });
    }

    private VisitCreate getVisitsRequest(VisitRecord visitRecord, String c4cId){
        VisitCreateBean visitBean = new VisitCreateBean();
        visitBean.setObjectNodeSenderTechnicalID("001");
        visitBean.setName(visitRecord.getProjectName());
        visitBean.setLifeCycleStatusCode("1");
        visitBean.setVisitTypeCode("Z01");
        visitBean.setAddress(visitRecord.getCustomerLocation());

        //客户 c4c id
        CustomerInfo customerInfo = customerInfoMapper.selectByOutCode(visitRecord.getCustomerCode());
        MainActivityPartyBean mainActivityPartyBean = new MainActivityPartyBean();
        mainActivityPartyBean.setBusinessPartnerInternalID(customerInfo.getOutCode());
        visitBean.setMainActivityPartyBean(mainActivityPartyBean);

        //代理商 c4c id
        OrganizerPartyBean organizerPartyBean = new OrganizerPartyBean();
        organizerPartyBean.setBusinessPartnerInternalID(c4cId);
        visitBean.setOrganizerPartyBean(organizerPartyBean);

        try{
            visitBean.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").format(DateUtil.parseDate(visitRecord.getVisitDate(),DateUtil.WEB_FORMAT)));
            visitBean.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").format(DateUtil.parseDate(visitRecord.getVisitDate(),DateUtil.WEB_FORMAT)));
        }catch (Exception e){
            log.error("时间格式化异常",e);
        }
        visitBean.setZMeetingContent(visitRecord.getTalkContent());
        visitBean.setZFollowUpPlans(visitRecord.getFollowPlan());
        visitBean.setZRequirementDescription(visitRecord.getClaimDescription());

        visitBean.setAttendees(visitRecord.getParticipantsZr());
        visitBean.setDAttendees(visitRecord.getParticipantsDl());
        visitBean.setKAttendees(visitRecord.getParticipantsCt());

        VisitCreateHeader header = new VisitCreateHeader();
        VisitCreateContent content = new VisitCreateContent(visitBean,header);

        VisitCreateBody body = new VisitCreateBody(content);
        VisitCreate create = new VisitCreate(body);
        return create;
    }

    /**
     * 文件上传
     * @param files
     * @return
     **/
    @OperationLog
    public CustomerFile fileUpload(MultipartFile files, Integer customerId, Integer index){
        CustomerFile file = customerFileService.saveOrUpdate(files, customerId);
        file.setIndex(index);
        return file;
    }

    /**
     * 通过客户简称获取客户销售的组织信息
     * TODO 校验客户是否可以支持销售预测
     */
    public CustomerOrgBean selectByAbbreviation(String custAbbreviation){
        CustomerInfo customerinfo = customerInfoMapper.selectByCustAbbreviation(custAbbreviation);
        CustomerOrgBean customerOrgBean = internalUserService.getSalesInfo(customerinfo.getId());

        SysParameter sysParameter = sysParamService.selectParam("2","1",customerinfo.getBusinessType());
        customerOrgBean.setCustType(sysParameter.getZhName());

        return customerOrgBean;
    }

    public List<CustomerInfo> selectCustShip(Integer custId){
        return customerInfoMapper.selectCustShip(custId);
    }
}
