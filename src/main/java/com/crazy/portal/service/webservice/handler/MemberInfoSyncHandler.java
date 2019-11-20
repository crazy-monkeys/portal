package com.crazy.portal.service.webservice.handler;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.request.MemberInfoSyncRequest;
import com.crazy.portal.bean.webservice.response.MemberInfoSyncResponse;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustBankInfoMapper;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.service.customer.*;
import com.crazy.portal.service.system.UserService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MemberInfoSyncHandler
 * @Author: God Man Qiu~
 * @Date: 2019/8/27 13:24
 */
@Slf4j
@Component(value = "portal.member.sync")
public class MemberInfoSyncHandler extends AbstractHandler implements IHandler<MemberInfoSyncRequest,MemberInfoSyncResponse>{
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private CustomerAgentsService customerAgentsService;
    @Resource
    private CustAssetsInformationService custAssetsInformationService;
    @Resource
    private CustBusinessInformationService custBusinessInformationService;
    @Resource
    private CustomerContactService customerContactService;
    @Resource
    private CustomerProductService customerProductService;
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
    private CustQuotasService custQuotasService;
    @Resource
    private CustZrAccountTeamService custZrAccountTeamService;
    @Resource
    private UserService userService;

    @Override
    @Transactional
    public MemberInfoSyncResponse process(MemberInfoSyncRequest request) {
        MemberInfoSyncResponse response = new MemberInfoSyncResponse();
        try{
             saveOrUpdateCustomer(request);
        }catch (BusinessException be){
            throw new BusinessException(be.getErrorCode(),be.getMessage());
        }catch (Exception e){
            log.error("接受客户信息异常",e);
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_SYNC_ERROR);
        }
        response.success();
        return response;
    }

    private void saveOrUpdateCustomer(MemberInfoSyncRequest request){
        boolean dealerCreate = false;
        log.info("request outcode"+request.getOutCode());
        CustomerInfo customerinfo = customerInfoMapper.selectByInCode(request.getInCode());
        if(null == customerinfo){
            customerinfo = new CustomerInfo();
            customerinfo.setCustType(Enums.CUSTOMER_TYPE.WAIT_SUBMIT.getCode());
            customerinfo.setApproveStatus(Enums.CUSTOMER_APPROVE_STATUS.WAIT_SUBMIT.getCode());
            customerinfo.setActive(1);
            mappingCustomerInfo(request, customerinfo);
            customerInfoMapper.insertSelective(customerinfo);
            if(request.getCustType().equals(Enums.CUSTOMER_BUSINESS_TYPE.dealer.getCode())){
                 dealerCreate = true;
            }
        }else{
            mappingCustomerInfo(request, customerinfo);
            customerInfoMapper.updateByPrimaryKeySelective(customerinfo);
        }
        saveCustomerAgents(request.getCustomerAgents(),customerinfo.getId());
        saveAssetSInformations(request.getAssetsInformations(), customerinfo.getId());
        saveBusinessInformations(request.getBusinessInformations(), customerinfo.getId());
        saveCustContacts(request.getCustomerContacts(), customerinfo.getId());
        saveProducts(request.getCustomerProducts(), customerinfo.getId());
        saveBank(request.getCustBankInfo(), customerinfo.getId());
        saveRelationShips(request.getRelationships(), customerinfo.getId());
        saveInvoiceInfos(request.getInvoiceInfos(), customerinfo.getId());
        saveSales(request.getSales(), customerinfo.getId());
        saveAddress(request.getAddresses(), customerinfo.getId());
        //saveAccountTeams(request.getAccountTeams(), customerinfo.getId());
        saveCustStructure(request.getCustStructure(), customerinfo.getId());
        saveQuotas(request.getQuotas(), customerinfo.getId());
        saveZRAccountTeam(request.getZRaccountTeams() ,customerinfo.getId());

        if(dealerCreate){
            //代理商用户 开通账号
            userService.createUser(customerinfo.getCustName(), customerinfo.getCustName(), customerinfo.getCustEmail(), customerinfo.getId());
        }
    }

    private void mappingCustomerInfo(MemberInfoSyncRequest request, CustomerInfo customerInfo){
        customerInfo.setInCode(request.getInCode());
        customerInfo.setOutCode(request.getOutCode());
        customerInfo.setCustName(request.getCustName());
        customerInfo.setCustEnName(request.getCustEName());
        customerInfo.setCustAbbreviation(request.getAbbreviation());
        if(StringUtil.isNotEmpty(request.getCustType())){
            customerInfo.setBusinessType(request.getCustType());
        }
        if(StringUtil.isNotEmpty(request.getIsLicense())){
            customerInfo.setIsLicense(Integer.valueOf(request.getIsLicense()));
        }
        if(StringUtil.isNotEmpty(request.getCustRole())){
            customerInfo.setCustRole(request.getCustRole());
        }
        customerInfo.setCustMobile(request.getMobile());
        customerInfo.setCustEmail(request.getEmail());
        customerInfo.setCustWeb(request.getCustWeb());
        if(StringUtil.isNotEmpty(request.getIsWhite())){
            customerInfo.setIsWhite(Integer.valueOf(request.getIsWhite()));
        }else{
            customerInfo.setIsWhite(Enums.YES_NO.YES.getCode());
        }
        if(StringUtil.isNotEmpty(request.getFrozenSales()) && request.getFrozenSales().equals("Y")){
            customerInfo.setFrozenSales(Enums.YES_NO.YES.getCode());
        }else{
            customerInfo.setFrozenSales(Enums.YES_NO.NO.getCode());
        }
        if(StringUtil.isNotEmpty(request.getFrozenDelivery())){
            customerInfo.setFrozenDelivery(Enums.YES_NO.YES.getCode());
        }else {
            customerInfo.setFrozenDelivery(Enums.YES_NO.NO.getCode());
        }
        if(StringUtil.isNotEmpty(request.getFrozenOrder())){
            customerInfo.setFrozenOrder(Enums.YES_NO.YES.getCode());
        }else{
            customerInfo.setFrozenOrder(Enums.YES_NO.NO.getCode());
        }
        if(StringUtil.isNotEmpty(request.getFrozenInvoice())){
            customerInfo.setFrozenInvoice(Enums.YES_NO.YES.getCode());
        }else{
            customerInfo.setFrozenInvoice(Enums.YES_NO.NO.getCode());
        }
        if(StringUtil.isNotEmpty(request.getRegistTime())){
            customerInfo.setRegistTime(request.getRegistTime());
        }else{
            customerInfo.setRegistTime(null);
        }
        if(StringUtil.isNotEmpty(request.getCorportaeAssets())){
            customerInfo.setCorportaeAssets(new BigDecimal(request.getCorportaeAssets()));
        }
        if(StringUtil.isNotEmpty(request.getStaffNumber())){
            customerInfo.setStaffNumber(Integer.valueOf(request.getStaffNumber()));
        }
        if(StringUtil.isNotEmpty(request.getDevelopersNumber())){
            customerInfo.setDevelopersNumber(Integer.valueOf(request.getDevelopersNumber()));
        }
        customerInfo.setBusinessIntroduction(request.getBusinessIntroduction());
        customerInfo.setAdvantagesIntroduction(request.getAdvantagesIntroduction());
    }

    private void saveCustomerAgents(String agents, Integer custId){
        List<CustomerAgent> results = StringUtil.isEmpty(agents)?new ArrayList<>():JSON.parseArray(agents,CustomerAgent.class);
        customerAgentsService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerAgentsService.save(e);
        });
    }

    private void saveAssetSInformations(String asset, Integer custId){
        List<CustAssetsInformation> results = StringUtil.isEmpty(asset)?new ArrayList<>():JSON.parseArray(asset, CustAssetsInformation.class);
        custAssetsInformationService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custAssetsInformationService.save(e);
        });
    }

    private void saveBusinessInformations(String business, Integer custId){
        List<CustBusinessInformation> results = StringUtil.isEmpty(business)?new ArrayList<>():JSON.parseArray(business, CustBusinessInformation.class);
        custBusinessInformationService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custBusinessInformationService.save(e);
        });
    }

    private void saveCustContacts(String contact, Integer custId){
        List<CustomerContact> results = StringUtil.isEmpty(contact)?new ArrayList<>():JSON.parseArray(contact, CustomerContact.class);
        customerContactService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerContactService.save(e);
        });
    }

    private void saveProducts(String products, Integer custId){
        List<CustomerProduct> results = StringUtil.isEmpty(products)?new ArrayList<>():JSON.parseArray(products, CustomerProduct.class);
        customerProductService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerProductService.save(e);
        });
    }

    private void saveBank(String bank, Integer custId){
        CustBankInfo custBankInfo = JSON.parseObject(bank,CustBankInfo.class);
        CustBankInfo bankInfo = custBankInfoMapper.selectByCustId(custId);
        if(null == bankInfo){
            custBankInfo.setBankDetailInfo(custBankInfo.getBankAddress());
            custBankInfo.setCustId(custId);
            custBankInfo.setActive(Enums.YES_NO.YES.getCode());
            custBankInfoMapper.insertSelective(custBankInfo);
        }else{
            if(StringUtil.isNotEmpty(custBankInfo.getBankName())){
                bankInfo.setBankName(custBankInfo.getBankName());
            }
            if(StringUtil.isNotEmpty(custBankInfo.getBankAccount())){
                bankInfo.setBankAccount(custBankInfo.getBankAccount());
            }
            if(StringUtil.isNotEmpty(custBankInfo.getBankBic())){
                bankInfo.setBankBic(custBankInfo.getBankBic());
            }
            if(StringUtil.isNotEmpty(custBankInfo.getBankAddress())){
                bankInfo.setBankDetailInfo(custBankInfo.getBankAddress());
            }
            custBankInfoMapper.updateByPrimaryKeySelective(bankInfo);
        }
    }

    private void saveRelationShips(String relationShips, Integer custId){
        List<CustCorporateRelationship> results = StringUtil.isEmpty(relationShips)?new ArrayList<>():JSON.parseArray(relationShips, CustCorporateRelationship.class);
        custCorporateRelationshipService.deleteByCustId(custId);
        results.forEach(e->{
            CustomerInfo customerInfo = customerInfoMapper.selectByInCode(e.getCorporateName());
            BusinessUtil.assertFlase(null == customerInfo,ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
            e.setCustId(custId);
            e.setCorporateType(null == e.getCorporateType()?null:e.getCorporateType().substring(0,4));
            e.setCorporateId(customerInfo.getInCode());
            e.setCorporateName(customerInfo.getCustName());
            e.setActive(Enums.YES_NO.YES.getCode());
            custCorporateRelationshipService.save(e);
        });
    }

    private void saveInvoiceInfos(String invoice, Integer custId){
        List<CustInvoiceInfo> results = StringUtil.isEmpty(invoice)?new ArrayList<>():JSON.parseArray(invoice, CustInvoiceInfo.class);
        custInvoiceInfoService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custInvoiceInfoService.save(e);
        });

    }

    private void saveSales(String sales, Integer custId){
        List<CustSales> results = StringUtil.isEmpty(sales)?new ArrayList<>():JSON.parseArray(sales, CustSales.class);
        custSalesService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custSalesService.save(e);
        });
    }

    public void saveAddress(String address, Integer custId){
        List<CustomerAddress> results = StringUtil.isEmpty(address)?new ArrayList<>():JSON.parseArray(address, CustomerAddress.class);
        customerAddressService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCountry(String.format("%s,%s",e.getCountry(),e.getProvince()));
            e.setAddressDetail(e.getDistrict());
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerAddressService.save(e);
        });
    }

    /*代理team*/
    public void saveAccountTeams(String accountTeams, Integer custId){
        List<CustomerAccountTeam> result = StringUtil.isEmpty(accountTeams)?new ArrayList<>():JSON.parseArray(accountTeams, CustomerAccountTeam.class);
        customerAccountTeamService.deleteByCustId(custId);
        result.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerAccountTeamService.save(e);
        });
    }

    public void saveCustStructure(String custStructure, Integer custId){
        List<CustomerStructure> result = StringUtil.isEmpty(custStructure)?new ArrayList<>():JSON.parseArray(custStructure, CustomerStructure.class);
        customerStructureService.deleteByCustId(custId);
        result.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            customerStructureService.save(e);
        });
    }

    private void saveQuotas(String quotas, Integer custId){
        List<CustSalesQuota> results = StringUtil.isEmpty(quotas)?new ArrayList<>():JSON.parseArray(quotas, CustSalesQuota.class);
        custQuotasService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custQuotasService.save(e);
        });
    }

    private void saveZRAccountTeam(String zrAccountTeam, Integer custId){
        List<CustZrAccountTeam> results = StringUtil.isEmpty(zrAccountTeam)?new ArrayList<>():JSON.parseArray(zrAccountTeam, CustZrAccountTeam.class);
        custZrAccountTeamService.deleteByCustId(custId);
        results.forEach(e->{
            e.setCustId(custId);
            e.setActive(Enums.YES_NO.YES.getCode());
            custZrAccountTeamService.save(e);
        });
    }
}
