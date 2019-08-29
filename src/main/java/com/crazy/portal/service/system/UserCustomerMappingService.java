package com.crazy.portal.service.system;

import com.crazy.portal.bean.system.UserCustomerMappingBean;
import com.crazy.portal.dao.system.UserCustomerMappingMapper;
import com.crazy.portal.entity.system.UserCustomerMapping;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserDealerMappingService
 * @Author: God Man Qiu~
 * @Date: 2019/8/26 23:10
 */
@Slf4j
@Service
public class UserCustomerMappingService {
    @Resource
    private UserCustomerMappingMapper userCustomerMappingMapper;

    public PageInfo<UserCustomerMapping> selectByPage(UserCustomerMappingBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<UserCustomerMapping> results = userCustomerMappingMapper.selectByPage(bean);
        return new PageInfo<>(results);

    }

    public void saveOrUpdateMapping(UserCustomerMapping userCustomerMapping, Integer userId){
        if(null != userCustomerMapping.getMappingId()){
            userCustomerMapping.setActive(1);
            userCustomerMapping.setUpdateUserId(userId);
            userCustomerMappingMapper.updateByPrimaryKeySelective(userCustomerMapping);
        }else{
            userCustomerMapping.setInsertUserId(userId);
            userCustomerMappingMapper.insertSelective(userCustomerMapping);
        }
    }
}
