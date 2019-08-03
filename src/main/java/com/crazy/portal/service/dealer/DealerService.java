package com.crazy.portal.service.dealer;

import com.crazy.portal.bean.customer.basic.ContactVO;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.basic.InvoiceVO;
import com.crazy.portal.bean.customer.basic.ShipVO;
import com.crazy.portal.bean.customer.dealer.DealerVO;
import com.crazy.portal.bean.customer.dealer.credit.ZrfcsdcustomercreditResponse;
import com.crazy.portal.bean.customer.dealer.credit.Zsdscredit;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.customer.CustomerInfoMapper;
import com.crazy.portal.entity.basic.*;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.service.customer.CustomersService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DealerService
 * @Author: God Man Qiu~
 * @Date: 2019/7/23 10:59
 */
@Slf4j
@Service
public class DealerService {
    private static final String PRODUCT_URL = "https://e600180-hcioem.hcisbt.cn1.hana.ondemand.com:443/cxf/CUSTOMERCREDIT";

    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private CustomersService customersService;

    /**
     * 获取代理商信息
     * @param dealerId
     * @return
     */
    public DealerVO getDealerInfo(Integer dealerId){
        CustomerInfo dealerInfo = customerInfoMapper.selectDealerInfo(dealerId);
        BusinessUtil.assertIsNull(dealerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        ZrfcsdcustomercreditResponse response = getDealerCredit(dealerInfo.getCustInCode());
        Zsdscredit zsdscredit = new Zsdscredit();
        if(response.getOreturn().equals("0")){
            zsdscredit = response.getOcredit();
        }
        return mappingVO(dealerInfo, zsdscredit);
    }

    /**
     * 获取代理商 授信额度
     * @param dealerCode
     * @return
     */
    public ZrfcsdcustomercreditResponse getDealerCredit(String dealerCode){
        try{
            String params = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">" +
                    "   <soapenv:Header/>" +
                    "   <soapenv:Body>" +
                    "      <urn:Zrfcsdcustomercredit>" +
                    "         <Ikunnr>"+dealerCode+"</Ikunnr>" +
                    "      </urn:Zrfcsdcustomercredit>" +
                    "   </soapenv:Body>" +
                    "</soapenv:Envelope>";
            String jsonStr = HttpClientUtils.postHeader(PRODUCT_URL, params);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(jsonStr, ZrfcsdcustomercreditResponse.class);
        }catch (Exception e){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_CREDIT);
        }
    }

    public void updateDealerInfo(CustomerInfo dealer, Integer dealerId){
        customersService.update(dealer, dealerId);
    }

    private DealerVO mappingVO(CustomerInfo dealer, Zsdscredit zsdscredit){
        DealerVO vo = new DealerVO();
        vo.setDealerCode(dealer.getCustInCode());
        vo.setDealerName(dealer.getCustZhName());
        vo.setDealerSName(dealer.getCustAbbreviation());
        vo.setMobile(dealer.getCustMobile());
        vo.setEmail(dealer.getCustEmail());
        vo.setDealerWeb(dealer.getCustWeb());
        vo.setCorporateAssets(dealer.getCorporateAssets());
        vo.setRegisterTime(dealer.getRegisterTime());
        vo.setCorporateNumber(dealer.getCorporateNumber());
        vo.setCorporateParents(dealer.getCorporateParents());
        vo.setAdvantageValue(dealer.getAdvantageValue());
        vo.setAdvantageIntroduction(dealer.getAdvantageIntroduction());
        vo.setBusinessIntroduction(dealer.getBusinessIntroduction());

        mappingBank(vo,dealer.getBasicBank());
        vo.setShips(mappingShip(dealer.getBasicShip()));
        vo.setContacts(mappingContact(dealer.getBasicContact()));
        vo.setInvoices(mappingInvoice(dealer.getBasicInvoice()));
        vo.setFiles(mappingFile(dealer.getBasicFile()));

         //授信额度初始值
        vo.setCredit(zsdscredit.getDmbtr());
        vo.setCreditUSE(zsdscredit.getZoccupy());
        vo.setCreditUnUSE(zsdscredit.getZremain());
        return vo;
    }

    private List<FileVO> mappingFile(List<BasicFile> fileDOS){
        List<FileVO> fileVOS = new ArrayList<>();
        for(BasicFile fileDO : fileDOS){
            FileVO fileVO = new FileVO();
            fileVO.setFileName(fileDO.getFileName());
            fileVO.setFilePath(fileDO.getFilePath());
            fileVO.setFileType(fileDO.getFileType());
            fileVOS.add(fileVO);
        }
        return fileVOS;
    }

    private List<InvoiceVO> mappingInvoice(List<BasicInvoiceInfo> invoiceInfoDOS){
        List<InvoiceVO> invoiceVOS = new ArrayList<>();
        for(BasicInvoiceInfo invoiceInfoDO : invoiceInfoDOS){
            InvoiceVO invoiceVO = new InvoiceVO();
            invoiceVO.setPurchasingUnit(invoiceInfoDO.getPurchasingUnit());
            invoiceVO.setShippingAddress(invoiceInfoDO.getShippingAddress());
            invoiceVO.setShippingMobile(invoiceInfoDO.getShippingMobile());
            invoiceVO.setTaxpayerRegistrationNumber(invoiceInfoDO.getTaxpayerRegistrationNumber());
            invoiceVO.setCurrency(invoiceInfoDO.getCurrency());
            invoiceVOS.add(invoiceVO);
        }
        return invoiceVOS;
    }

    private List<ContactVO> mappingContact(List<BasicContact> contactDOS){
        List<ContactVO> contactVOS = new ArrayList<>();
        for(BasicContact contactDO : contactDOS){
            ContactVO contactVO = new ContactVO();
            contactVO.setCName(contactDO.getContactName());
            contactVO.setCMobile(contactDO.getContactMobile());
            contactVO.setCEmail(contactDO.getContactEmail());
            contactVO.setCDepartment(contactDO.getContactDepartment());
            contactVO.setCPosition(contactDO.getContactPosition());
            contactVO.setEquityRatio(contactDO.getEquityRatio());
            contactVO.setIsDecisionMaker(contactDO.getIsDecisionMaker());
            contactVOS.add(contactVO);
        }
        return contactVOS;
    }

    private List<ShipVO> mappingShip(List<BasicCorporateRelationship> ships){
        List<ShipVO> shipVos = new ArrayList<>();
        for(BasicCorporateRelationship ship : ships){
            ShipVO shipVO = new ShipVO();
            shipVO.setShipName(ship.getCorporateName());
            shipVO.setShipType(ship.getCorporateType());
            shipVos.add(shipVO);
        }
        return shipVos;
    }

    private void mappingBank(DealerVO vo, BasicBankInfo bank){
        if(null == bank){
            return;
        }
        vo.setBankName(bank.getBankName());
        vo.setBankAccount(bank.getBankAccount());
        vo.setBankAddress(bank.getBankAddress());
        vo.setBankBIC(bank.getBankBic());
    }
}
