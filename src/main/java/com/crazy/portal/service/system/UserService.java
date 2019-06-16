package com.crazy.portal.service.system;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.archive.Archive;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import com.crazy.portal.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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

    private PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public PageInfo<User> selectUserWithPage(User user,Integer pageNum, Integer pageSize){
        PortalUtil.defaultStartPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.selectUserWithPage(user));
    }

    public User findUser(String userName){
        if(StringUtil.isEmpty(userName)){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_EMPTY_USER_NAME.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_EMPTY_USER_NAME.getZhMsg());
        }
        return userMapper.findByLoginName(userName);
    }

    @Transactional
    public int updateUser(User user){
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Transactional
    public int register(User user) {
        String username = user.getLoginName();
        if (userMapper.findByLoginName(username) != null) {
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_EXISTS.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_EXISTS.getZhMsg());
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
        return userMapper.insertSelective(user);
    }

    @Deprecated
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
}
