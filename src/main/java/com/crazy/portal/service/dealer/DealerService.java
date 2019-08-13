package com.crazy.portal.service.dealer;

import com.crazy.portal.bean.customer.basic.ContactVO;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.basic.InvoiceVO;
import com.crazy.portal.bean.customer.basic.ShipVO;
import com.crazy.portal.bean.customer.dealer.DealerVO;
import com.crazy.portal.bean.customer.dealer.credit.Zsdscredit;
import com.crazy.portal.dao.customer.CustomerInfoMapper;
import com.crazy.portal.entity.basic.*;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.customer.CustomersService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
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
        BusinessUtil.notNull(dealerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        return mappingVO(dealerInfo);
    }

    public void updateDealerInfo(CustomerInfo dealer, User user){
        customersService.update(dealer, user);
    }

    private DealerVO mappingVO(CustomerInfo dealer){
        DealerVO vo = new DealerVO();
        vo.setDealerCode(dealer.getCustInCode());
        vo.setDealerName(dealer.getCustName());
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
        Zsdscredit zsdscredit = CallApiUtils.callECCCreditApi(dealer.getCustInCode());
        vo.setCredit(null == zsdscredit.getDmbtr()? BigDecimal.ZERO:zsdscredit.getDmbtr());
        vo.setCreditUSE(null == zsdscredit.getZoccupy()?BigDecimal.ZERO:zsdscredit.getZoccupy());
        vo.setCreditUnUSE(null == zsdscredit.getZremain()?BigDecimal.ZERO:zsdscredit.getZoccupy());
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
