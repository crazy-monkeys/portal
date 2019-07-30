package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.SysParameterMapper;
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by weiying on 2019/7/28.
 */
@Slf4j
@Service
public class SysParamService {
    @Resource
    private SysParameterMapper sysParameterMapper;

    /**
     * 查询所有的系统参数
     */
    public PageInfo<SysParameter> selectAll(Integer model, Integer function, Integer active, int pageNum,int pageSize){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<SysParameter> results = sysParameterMapper.selectAll(model, function, active);
        return new PageInfo<>(results);
    }

    /**
     * 查找对应的模块，对应功能的系统参数
     * @param model
     * @param function
     * @return
     */
    public List<SysParameter> selectParam(String model, String function){
        return sysParameterMapper.selectByMAndF(model, function);
    }

    /**
     * 新增或保存系统参数
     * @param sysParameter
     */
    public void saveOrUpdate(SysParameter sysParameter, Integer userId){
        Integer checkResult = sysParameterMapper.checkValue(sysParameter.getPModel(), sysParameter.getPFunction(), sysParameter.getPValue(), sysParameter.getId());
        BusinessUtil.assertFlase(checkResult==1, ErrorCodes.SystemManagerEnum.SYS_PARAM_VALUE);
        if(null!=sysParameter.getId()){
            sysParameter.setUpdateTime(new Date());
            sysParameter.setUpdateUser(userId);
            sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
        }else{
            sysParameter.setInsertTime(new Date());
            sysParameter.setInsertUser(userId);
            sysParameterMapper.insertSelective(sysParameter);
        }
    }
}
