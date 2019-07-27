package com.crazy.portal.service.dealer;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.basic.ContactVO;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.basic.InvoiceVO;
import com.crazy.portal.bean.customer.basic.ShipVO;
import com.crazy.portal.bean.customer.dealer.DealerVO;
import com.crazy.portal.dao.customer.TCustomerInfoDOMapper;
import com.crazy.portal.entity.basic.*;
import com.crazy.portal.entity.customer.TCustomerInfoDO;
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
    @Resource
    private TCustomerInfoDOMapper tCustomerInfoDOMapper;

    //代理商个人信息
    public DealerVO getDealerInfo(Integer dealerId){
        try{
            TCustomerInfoDO dealerInfo = tCustomerInfoDOMapper.selectDealerInfo(dealerId);
            log.info(JSON.toJSONString(dealerInfo));
            return mappingVO(dealerInfo);
        }catch (Exception e){
            log.error("获取代理商个人信息异常！",e);
            return null;
        }
    }

    private DealerVO mappingVO(TCustomerInfoDO dealer){
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
        return vo;
       /* //授信额度初始值
        private BigDecimal credit;
        //授信额度占用值
        private BigDecimal creditUSE;
        //授信额度剩余值
        private BigDecimal creditUnUSE;*/

    }

    private List<FileVO> mappingFile(List<TBasicFileDO> fileDOS){
        List<FileVO> fileVOS = new ArrayList<>();
        for(TBasicFileDO fileDO : fileDOS){
            FileVO fileVO = new FileVO();
            fileVO.setFileName(fileDO.getFileName());
            fileVO.setFilePath(fileDO.getFilePath());
            fileVO.setFileType(fileDO.getFileType());
            fileVOS.add(fileVO);
        }
        return fileVOS;
    }

    private List<InvoiceVO> mappingInvoice(List<TBasicInvoiceInfoDO> invoiceInfoDOS){
        List<InvoiceVO> invoiceVOS = new ArrayList<>();
        for(TBasicInvoiceInfoDO invoiceInfoDO : invoiceInfoDOS){
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

    private List<ContactVO> mappingContact(List<TBasicContactDO> contactDOS){
        List<ContactVO> contactVOS = new ArrayList<>();
        for(TBasicContactDO contactDO : contactDOS){
            ContactVO contactVO = new ContactVO();
            contactVO.setCName(contactDO.getContactName());
            contactVO.setCMobile(contactDO.getContactMobile());
            contactVO.setCEmail(contactDO.getContactEmail());
            contactVO.setCDepartment(contactDO.getContactDepartment());
            contactVO.setCPosition(contactDO.getContactPosition());
            contactVO.setEquityRatio(contactDO.getEquityRatio());
            contactVO.setIsDecisionMaker(contactDO.getIsDecisionMaker());
        }
        return contactVOS;
    }

    private List<ShipVO> mappingShip(List<TBasicCorporateRelationshipDO> ships){
        List<ShipVO> shipVos = new ArrayList<>();
        for(TBasicCorporateRelationshipDO ship : ships){
            ShipVO shipVO = new ShipVO();
            shipVO.setShipName(ship.getCorporateName());
            shipVO.setShipType(ship.getCorporateType());
            shipVos.add(shipVO);
        }
        return shipVos;
    }

    private void mappingBank(DealerVO vo,TBasicBankInfoDO bank){
        if(null == bank){
            return;
        }
        vo.setBankName(bank.getBankName());
        vo.setBankAccount(bank.getBankAccount());
        vo.setBankAddress(bank.getBankAddress());
        vo.setBankBIC(bank.getBankBic());
    }

    //修改个人信息
    public void updateDealerInfo(DealerVO vo){

    }

    //创建子账号



}
