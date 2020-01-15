package com.crazy.portal.service.forecast;

import com.crazy.portal.bean.forecast.ForecastMKBean;
import com.crazy.portal.dao.forecast.ForecastMkMapper;
import com.crazy.portal.entity.forecast.ForecastMk;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ForecastMKService
 * @Author: God Man Qiu~
 * @Date: 2020/1/15 17:45
 */
@Service
@Slf4j
public class ForecastMKService {
    @Resource
    private ForecastMkMapper forecastMkMapper;

    public PageInfo<ForecastMk> list(ForecastMKBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(),bean.getPageSize());
        List<ForecastMk> result = forecastMkMapper.selectByPage(bean);
        return new PageInfo<>(result);
    }

    public ForecastMk selectByDealerId(Integer dealerId){
        return forecastMkMapper.selectByDealerId(dealerId);
    }

    /**
     * 新增需要控制预测的代理商
     * @param dealerId
     * @param dealerName
     */
    public void save(Integer dealerId, String dealerName){
        ForecastMk mk = new ForecastMk();
        mk.setDealerId(dealerId);
        mk.setDealerName(dealerName);
        mk.setInsertS(Enums.YES_NO.YES.getCode());
        mk.setUpdateS(Enums.YES_NO.YES.getCode());
        mk.setActive(Enums.YES_NO.YES.getCode());
        forecastMkMapper.insertSelective(mk);
    }

    /**
     * 修改代理商的预测权限
     * @param mk
     */
    public void update(ForecastMk mk){
        ForecastMk forecastMk = forecastMkMapper.selectByPrimaryKey(mk.getMkId());
        BusinessUtil.assertFlase(null == forecastMk, ErrorCodes.BusinessEnum.MK_IS_NULL);

        if(null != mk.getInsertS()){
            forecastMk.setInsertS(mk.getInsertS());
            forecastMk.setInsertM(mk.getInsertM());
        }else if (null != mk.getUpdateS()){
            forecastMk.setUpdateS(mk.getUpdateS());
            forecastMk.setUpdateM(mk.getUpdateM());
        }
        forecastMkMapper.updateByPrimaryKeySelective(forecastMk);
    }
}
