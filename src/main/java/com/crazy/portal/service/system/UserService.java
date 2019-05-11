package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.UserDOMapper;
import com.crazy.portal.dao.system.UserRoleDOMapper;
import com.crazy.portal.entity.system.UserDO;
import com.crazy.portal.entity.system.UserRoleDO;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private UserRoleDOMapper userRoleDOMapper;

    private PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public List<UserDO> selectAllUser(){
        return userDOMapper.selectAllUser();
    }

    public Boolean approvalUser(String userName, int status){
        try{
            UserDO userDO = userDOMapper.findByLoginName(userName);
            if(userDO.getUserStatus()==0){
                userDO.setUserStatus(Enums.USER_STATUS.init.getCode());
            }else if(userDO.getUserStatus()==3){
                userDO.setUserStatus(Enums.USER_STATUS.normal.getCode());
            }
            userDO.setUpdateTime(new Date());
            //user.setUpdateUserId();
            userDOMapper.insertSelective(userDO);
        }catch (Exception e){
            log.error("审批异常！",e);
            return false;
        }
        return true;
    }

    @Transactional
    public String register(UserDO user) {
        String username = user.getLoginName();
        if (userDOMapper.findByLoginName(username) != null) {
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
        userDOMapper.insertSelective(user);
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(user.getId());
        userRoleDO.setRoleId(2);
        userRoleDO.setCreateTime(new Date());
        userRoleDO.setCreateId(1);
        userRoleDOMapper.insertSelective(userRoleDO);
        return "success";
    }
}
