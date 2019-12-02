package com.crazy.portal.service.system;

import com.crazy.portal.bean.system.UserCustomerMappingBean;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.UserCustomerMappingMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserCustomerMapping;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Resource
    private InternalUserMapper internalUserMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    public PageInfo<UserCustomerMapping> selectByPage(UserCustomerMappingBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<UserCustomerMapping> results = userCustomerMappingMapper.selectByPage(bean);
        return new PageInfo<>(results);

    }

    /**
     * 根据Userid 获取 负责的 代理商的customer id
     * @param userId
     * @param mappingModel
     * @return
     */
    public List<Integer> selectUserMapping(Integer userId, String mappingModel){
        InternalUser internalUser = internalUserMapper.selectByUserId(userId);
        BusinessUtil.assertFlase(null == internalUser, ErrorCodes.SystemManagerEnum.SYS_IN_USER_ERROR);

        UserCustomerMapping mapping= userCustomerMappingMapper.selectUserMapping(internalUser.getInUserId(), mappingModel);
        if(null == mapping){
            return new ArrayList<>();
        }
        List<Integer> results = new ArrayList<>();

        //预测 收货出货 需要 代理商登陆账号id
        if(mappingModel.equals(Enums.CustomerMappingModel.Forecast.getValue())){
            List<String> strs = Arrays.asList(mapping.getCustId().split(","));
            strs.forEach(e->{
                results.add(Integer.valueOf(e));
            });
        }else{
            List<String> strs = Arrays.asList(mapping.getCustId().split(","));
            strs.forEach(e->{
                User user = userMapper.selectById(Integer.valueOf(e));
                if(null!=user && null != user.getDealerId()){
                    CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(user.getDealerId());
                    if(null != customerInfo){
                        results.add(Integer.valueOf(e));
                    }
                }
            });
        }
        return results;
    }

    public void saveOrUpdateMapping(UserCustomerMapping userCustomerMapping, Integer userId){
        userCustomerMapping.setActive(1);
        if(null != userCustomerMapping.getMappingId()){
            userCustomerMapping.setUpdateUserId(userId);
            userCustomerMappingMapper.updateByPrimaryKeySelective(userCustomerMapping);
        }else{
            userCustomerMapping.setInsertUserId(userId);
            userCustomerMappingMapper.insertSelective(userCustomerMapping);
        }
    }
}
