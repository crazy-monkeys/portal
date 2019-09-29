package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.EnquiryApprovalBean;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.price.CatalogPriceMapper;
import com.crazy.portal.dao.price.EnquiryPriceMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 21:53 2019-08-20
 * @Modified by:
 */
@Service
@Slf4j
public class EnquiryPriceService {

    @Resource
    private EnquiryPriceMapper enquiryPriceMapper;
    @Resource
    private CatalogPriceMapper catalogPriceMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 提交申请
     * @param vo
     * @param currentUser
     * @return
     */
    public void apply(EnquiryPriceVO vo, String currentUser){

        String productModel = vo.getProductModel();
        BusinessUtil.assertEmpty(productModel,ErrorCodes.PriceEnum.PRICE_CATALOG_NOT_EXISTS);

        String bu = vo.getBu();
        BusinessUtil.assertEmpty(bu,ErrorCodes.PriceEnum.PRICE_EMPTY_BU);

        String inCustomer = vo.getInCustomer();
        CatalogPrice catalogPrice = catalogPriceMapper.selectByProductModelAndCustomerName(productModel,bu,inCustomer);
        BusinessUtil.notNull(catalogPrice,ErrorCodes.PriceEnum.PRICE_CATALOG_PRICE_NOT_EXISTS);

        Date now = new Date();
        EnquiryPrice enquiryPrice = new EnquiryPrice();
        enquiryPrice.setInCustomer(vo.getInCustomer());
        enquiryPrice.setProductModel(vo.getProductModel());
        enquiryPrice.setApplyRemark(vo.getApplyRemark());
        enquiryPrice.setApplyTime(now);
        enquiryPrice.setProposer(currentUser);
        enquiryPrice.setApprovalStatus(Enums.APPROVAL_STATUS.pending.toString());
        int result = enquiryPriceMapper.insertSelective(enquiryPrice);
        BusinessUtil.assertTrue(result > 0, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
    }

    /**
     * 列表查询(申请列表跟审批列表冗余在一起)
     * @return
     */
    public PageInfo<EnquiryPrice> query(EnquiryPriceVO enquiryPriceVO){
        PortalUtil.defaultStartPage(enquiryPriceVO.getPageIndex(), enquiryPriceVO.getPageSize());
        Page<EnquiryPrice> enquiryPrices = enquiryPriceMapper.selectByParamsWithPage(enquiryPriceVO);
        return new PageInfo<>(enquiryPrices);
    }

    /**
     * 审批
     */
    public void approve(EnquiryApprovalBean enquiryApprovalBean){
        List<Integer> applyIds = enquiryApprovalBean.getApplyIds();
        BusinessUtil.assertFlase(applyIds.isEmpty(), ErrorCodes.PriceEnum.PRICE_EMPTY_APPLYIDS);

        String approvalStatus = enquiryApprovalBean.getApprovalStatus();
        String approvalRemark = enquiryApprovalBean.getApprovalRemark();
        String approver = enquiryApprovalBean.getApprover();

        applyIds.forEach(applyId->{
            log.info("用户[{}] 进行 [{}] 审批操作,审批状态为[{}]",approver,applyId,approvalStatus);
            EnquiryPrice enquiryPrice = enquiryPriceMapper.selectByPrimaryKey(applyId);
            BusinessUtil.notNull(enquiryPrice, ErrorCodes.PriceEnum.PRICE_ENQUIEY_NOT_EXISTS);

            BusinessUtil.assertTrue(Enums.APPROVAL_STATUS.isExists(approvalStatus),
                    ErrorCodes.PriceEnum.PRICE_ENQUIEY_APPROVAL_STATUS_NOT_EXISTS);

            //如果不是待审批的询价单,抛出异常
            boolean checkApprovalStatus = enquiryPrice.getApprovalStatus().equals(Enums.APPROVAL_STATUS.pending.toString());
            BusinessUtil.assertTrue(checkApprovalStatus,ErrorCodes.PriceEnum.PRICE_NON_PENGDING_STATUS);

            enquiryPrice.setApprovalStatus(approvalStatus);
            enquiryPrice.setApprovalRemark(approvalRemark);
            enquiryPrice.setApprover(approver);

            //如果是通过状态，查询出目录价格
            if(approvalStatus.equals(Enums.APPROVAL_STATUS.pass.toString())) {
                //如果是通过,查询出目录商品信息到询价单
                String productModel = enquiryPrice.getProductModel();
                String inCustomer = enquiryPrice.getInCustomer();
                String bu = enquiryPrice.getBu();
                CatalogPrice catalogPrice = catalogPriceMapper.selectByProductModelAndCustomerName(productModel,bu,inCustomer);
                BusinessUtil.notNull(catalogPrice, ErrorCodes.PriceEnum.PRICE_CATALOG_NOT_EXISTS);

                enquiryPrice.setBu(catalogPrice.getBu());
                enquiryPrice.setPdt(catalogPrice.getPdt());
                enquiryPrice.setStatus(catalogPrice.getStatus());

                String inCustomerCode = catalogPrice.getInCustomer();
                CustomerInfo customerInfo = customerInfoMapper.selectByOutCode(inCustomerCode);
                enquiryPrice.setInCustomer(customerInfo == null?inCustomerCode:customerInfo.getCustAbbreviation());
                enquiryPrice.setRemark(catalogPrice.getRemark());
                enquiryPrice.setProductType(catalogPrice.getProductType());
                enquiryPrice.setPriceType(catalogPrice.getPriceType());
                enquiryPrice.setBoms(catalogPrice.getBoms());
                enquiryPrice.setPlatform(catalogPrice.getPlatform());
                enquiryPrice.setCatalogPrice(catalogPrice.getCatalogPrice());
                enquiryPrice.setEffectTime(catalogPrice.getEffectTime());
                enquiryPrice.setDeadTime(catalogPrice.getDeadTime());
                enquiryPrice.setModifyTime(catalogPrice.getModifyTime());
            }
            enquiryPriceMapper.updateByPrimaryKeySelective(enquiryPrice);
        });
    }

    /**
     * 删除询价记录
     * @param enquiryId
     */
    public void delete(Integer enquiryId){
        int result = enquiryPriceMapper.deleteByPrimaryKey(enquiryId);
        BusinessUtil.assertTrue(result > 0, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
    }


}
