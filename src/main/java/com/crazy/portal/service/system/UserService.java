package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 10:41 2019/4/9
 * @Modified by:
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    private PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public List<User> selectAllUser(){
        return userMapper.selectAllUser();
    }

    public Boolean approvalUser(String userName, int status){
        try{
            User user = userMapper.findByLoginName(userName);
            if(user.getUserStatus()==0){
                user.setUserStatus(Enums.USER_STATUS.init.getCode());
            }else if(user.getUserStatus()==3){
                user.setUserStatus(Enums.USER_STATUS.normal.getCode());
            }
            user.setUpdateTime(new Date());
            //user.setUpdateUserId();
            userMapper.insertSelective(user);
        }catch (Exception e){
            log.error("审批异常！",e);
            return false;
        }
        return true;
    }

    @Transactional
    public String register(User user) {
        String username = user.getLoginName();
        if (userMapper.findByLoginName(username) != null) {
            return "用户已存在";
        }
        String rawPassword = user.getLoginPwd();
        user.setLoginName(user.getLoginName());
        user.setEmail(user.getEmail());
        user.setLoginPwd(passwordEncoder.encode(rawPassword));
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setRealName(user.getRealName());
        user.setPhone(user.getPhone());
        user.setCountry(user.getCountry());
        user.setUserStatus(0);
        user.setUserType(Enums.USER_STATUS.freeze.getCode());
        user.setRegTime(new Date());
        user.setLastLoginTime(new Date());
        user.setPwdInvalidTime(DateUtil.addDays(new Date(),365));
        user.setCreateUserId(1);
        user.setCreateTime(new Date());
        user.setActive((short)1);
        userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2);
        userRole.setCreateTime(new Date());
        userRole.setCreateId(1);
        userRoleMapper.insertSelective(userRole);
        return "success";
    }
}
