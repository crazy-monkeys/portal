package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.SysParameterMapper;
import com.crazy.portal.entity.system.SysParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
    public List<SysParameter> selectAll(){
        return sysParameterMapper.selectAll();
    }
    /**
     * 查询所有的可配置的模块
     * @return
     */
    public List<SysParameter> selectAllModel(){
        return sysParameterMapper.selectAll();
    }

    /**
     * 查询所有的可配置模块的可配置功能
     * @return
     */
    public List<SysParameter> selectAllFunction(String model){
        return sysParameterMapper.selectAll();
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
    public void saveOrUpdate(SysParameter sysParameter){
        if(null!=sysParameter.getId()){
            sysParameterMapper.updateByPrimaryKeySelective(sysParameter);
        }else{
            sysParameterMapper.insertSelective(sysParameter);
        }
    }
}
