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


    /**
     * 分页获取用户信息
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> selectUserWithPage(User user,Integer pageNum, Integer pageSize){
        PortalUtil.defaultStartPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.selectUserWithPage(user));
    }

    /**
     * 根据用户名查找详细信息
     * @param userName
     * @return
     */
    public User findUser(String userName){
        if(StringUtil.isEmpty(userName)){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_EMPTY_USER_NAME.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_EMPTY_USER_NAME.getZhMsg());
        }
        return userMapper.findByLoginName(userName);
    }

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    @Transactional
    public int updateUser(User user){
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
        user.setLoginPwd(passwordEncoder.encode(user.getLoginPwd()));
        user.setUserStatus(Enums.USER_STATUS.freeze.getCode());
        user.setUserType(Enums.USER_TYPE.agent.getCode());
        user.setRegTime(new Date());
        user.setCreateUserId(-1);
        user.setCreateTime(new Date());
        user.setPwdInvalidTime(DateUtil.addDays(new Date(),365));
        user.setActive((short)1);
        return userMapper.insertSelective(user);
    }

    /**
     *
     * @param userName
     * @param status
     * @return
     */
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
