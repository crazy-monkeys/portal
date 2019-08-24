package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.EnquiryApprovalBean;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.dao.price.CatalogPriceMapper;
import com.crazy.portal.dao.price.EnquiryPriceMapper;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 21:53 2019-08-20
 * @Modified by:
 */
@Service
public class EnquiryPriceService {

    @Resource
    private EnquiryPriceMapper enquiryPriceMapper;
    @Resource
    private CatalogPriceMapper catalogPriceMapper;

    /**
     * 提交申请
     * @param vo
     * @param currentUser
     * @return
     */
    public void apply(EnquiryPriceVO vo, String currentUser){

        String productModel = vo.getProductModel();
        String inCustomer = vo.getInCustomer();
        BusinessUtil.assertEmpty(productModel,ErrorCodes.PriceEnum.PRICE_CATALOG_NOT_EXISTS);

        CatalogPrice catalogPrice = catalogPriceMapper.selectByProductModelAndCustomerName(productModel,inCustomer);
        BusinessUtil.notNull(catalogPrice,
                StringUtil.isEmpty(inCustomer)
                        ?ErrorCodes.PriceEnum.PRICE_CATALOG_NOT_EXISTS
                        :ErrorCodes.PriceEnum.PRICE_CATALOG_CUSTOMER_NOT_EXISTS);

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
     * @param enquiryPriceVO
     * @return
     */
    public Page<EnquiryPrice> query(EnquiryPriceVO enquiryPriceVO){
        return enquiryPriceMapper.selectByParamsWithPage(enquiryPriceVO);
    }

    /**
     * 审批
     */
    public void approve(EnquiryApprovalBean enquiryApprovalBean){
        EnquiryPrice enquiryPrice = enquiryPriceMapper.selectByPrimaryKey(enquiryApprovalBean.getApplyId());
        BusinessUtil.notNull(enquiryPrice, ErrorCodes.PriceEnum.PRICE_ENQUIEY_NOT_EXISTS);

        String approvalStatus = enquiryApprovalBean.getApprovalStatus();
        BusinessUtil.assertTrue(Enums.APPROVAL_STATUS.isExists(approvalStatus),
                ErrorCodes.PriceEnum.PRICE_ENQUIEY_APPROVAL_STATUS_NOT_EXISTS);


        enquiryPrice.setApprovalStatus(Enums.APPROVAL_STATUS.pass.toString());
        enquiryPrice.setApprovalRemark(enquiryApprovalBean.getApprovalRemark());
        enquiryPrice.setApprover(enquiryApprovalBean.getApprover());

        if(approvalStatus.equals(Enums.APPROVAL_STATUS.pass)){
            //如果是通过,查询出目录商品信息到询价单
            String productModel = enquiryPrice.getProductModel();
            String inCustomer = enquiryPrice.getInCustomer();
            CatalogPrice catalogPrice = catalogPriceMapper.selectByProductModelAndCustomerName(productModel,inCustomer);
            BusinessUtil.notNull(enquiryPrice, ErrorCodes.PriceEnum.PRICE_CATALOG_NOT_EXISTS);

            enquiryPrice.setBu(catalogPrice.getBu());
            enquiryPrice.setPdt(catalogPrice.getPdt());
            enquiryPrice.setProductType(catalogPrice.getProductType());
            enquiryPrice.setPlatform(catalogPrice.getPlatform());
            enquiryPrice.setCatalogPrice(catalogPrice.getCatalogPrice());
            enquiryPrice.setEffectTime(catalogPrice.getEffectiveTime());
            enquiryPrice.setDeadTime(catalogPrice.getDeadTime());
            enquiryPrice.setModifyTime(catalogPrice.getModifyTime());

            enquiryPriceMapper.insertSelective(enquiryPrice);
        }else{
            //更新审批信息
            enquiryPriceMapper.updateByPrimaryKeySelective(enquiryPrice);
        }
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
