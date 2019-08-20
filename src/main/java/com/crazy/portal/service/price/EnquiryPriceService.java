package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.dao.price.EnquiryPriceMapper;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
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

    /**
     * 提交申请
     * @param vo
     * @param currentUser
     * @return
     */
    public void apply(EnquiryPriceVO vo, String currentUser){
        Date now = new Date();
        EnquiryPrice enquiryPrice = new EnquiryPrice();
        enquiryPrice.setCustomerName(vo.getCustomerName());
        enquiryPrice.setProductModel(vo.getProductModel());
        enquiryPrice.setApplyRemark(vo.getRemark());
        enquiryPrice.setApplyTime(now);
        enquiryPrice.setProposer(currentUser);
        enquiryPrice.setApprovalStatus(Enums.APPROVAL_STATUS.pending.toString());
        int result = enquiryPriceMapper.insertSelective(enquiryPrice);
        BusinessUtil.assertTrue(result > 0, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
    }

    /**
     * 列表查询(申请列表跟审批列表冗余)
     * @param enquiryApplyBean
     * @return
     */
    public Page<EnquiryPrice> query(EnquiryPriceVO enquiryApplyBean){
        return enquiryPriceMapper.selectByParamsWithPage(enquiryApplyBean);
    }

    /**
     * 审批
     * @param enquiryId
     * @param approvalStatus
     * @param approver
     */
    public void approve(int enquiryId,String approvalStatus,String approver){
        EnquiryPrice enquiryPrice = enquiryPriceMapper.selectByPrimaryKey(enquiryId);
    }

    /**
     * 删除询价记录
     * @param enquiryId
     */
    public void delete(int enquiryId){
        int result = enquiryPriceMapper.deleteByPrimaryKey(enquiryId);
        BusinessUtil.assertTrue(result > 0, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
    }


}
