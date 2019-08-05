package com.crazy.portal.service.rate;

import com.crazy.portal.bean.rate.DealerRateBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.rate.DealerRateBatchMapper;
import com.crazy.portal.dao.rate.DealerRateDOMapper;
import com.crazy.portal.entity.rate.DealerRateBatch;
import com.crazy.portal.entity.rate.DealerRateDO;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DealerRateService
 * @Author: God Man Qiu~
 * @Date: 2019/8/3 14:01
 */
@Slf4j
@Service
public class DealerRateService {
    @Resource
    private DealerRateDOMapper dealerRateDOMapper;
    @Resource
    private DealerRateBatchMapper dealerRateBatchMapper;

    /**
     * 上传费率excel
     */
    @Transactional
    public void uploadRate(String batchSeq, MultipartFile file, Integer userId){
        DealerRateBatch rateBatch = dealerRateBatchMapper.selectByBatchSeq(batchSeq);
        BusinessUtil.assertIsNotNull(rateBatch, ErrorCodes.BusinessEnum.RATE_BATCH_IS_NOT_NULL);

        dealerRateBatchMapper.inActive();

        rateBatch = mappingBatch(batchSeq, userId);
        dealerRateBatchMapper.insertSelective(rateBatch);

        List<DealerRateBean> rateBeans = ExcelUtils.readExcel(file, DealerRateBean.class,1);
        for(DealerRateBean rateBean : rateBeans){
            dealerRateDOMapper.insertSelective(mappingDealerReate(rateBean, userId, rateBatch.getId()));
        }
    }

    public DealerRateDO mappingDealerReate(DealerRateBean rateBean, Integer userId, Integer batchId){
        try{
            DealerRateDO dealerRateDO = new DealerRateDO();
            BeanUtils.copyNotNullFields(rateBean, dealerRateDO);
            dealerRateDO.setBatchId(batchId);
            dealerRateDO.setAcitve(1);
            dealerRateDO.setCreateTime(new Date());
            dealerRateDO.setCreateUser(userId);
            return dealerRateDO;
        }catch (Exception e){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.SYS_BEAN_ERROR);
        }
    }
    /**
     * 费率一次同步生效
     * @param batchSeq
     * @param userId
     */
    private DealerRateBatch mappingBatch(String batchSeq, Integer userId){
        DealerRateBatch rateBatch = new DealerRateBatch();
        rateBatch.setBatchSeq(batchSeq);
        rateBatch.setBatchStatus(Enums.RATE_TYPE.ACTIVE.getCode());
        rateBatch.setActive(1);
        rateBatch.setCreateUser(userId);
        rateBatch.setCreateTime(new Date());
        return rateBatch;
    }

    /**
     * 代理费批次查询
     */
   /* public PageInfo<DealerRateBatch> selectRateBatch(){

    }*/
    /**
     * 查询代理费
     * @param dealerRateDO
     * @return
     */
    public PageInfo<DealerRateDO> selectRate(DealerRateDO dealerRateDO){
        PortalUtil.defaultStartPage(dealerRateDO.getPageIndex(), dealerRateDO.getPageSize());
        List<DealerRateDO> results = dealerRateDOMapper.selectDealerRate(dealerRateDO);
        return new PageInfo<>(results);
    }
}
