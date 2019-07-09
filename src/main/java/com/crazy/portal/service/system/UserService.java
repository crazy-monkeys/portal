package com.crazy.portal.service.system;

import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
    private EmailHelper emailHelper;

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

    @Transactional
    public void resetUserPwd(String loginName,User currentUser){
        User user = this.findUser(loginName);
        if(user == null){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getZhMsg());
        }
        user.setUpdateTime(new Date());
        user.setUpdateUserId(currentUser.getId());
        String newPasswod = this.generateRandomPassword();
        user.setLoginPwd(passwordEncoder.encode(newPasswod));
        userMapper.updateByPrimaryKeySelective(user);

        //发送邮件
        MailBean mailBean = new MailBean();
        mailBean.setTos(user.getEmail());
        mailBean.setSubject("密码重置邮件");
        mailBean.setTemplateName(EmailHelper.MAIL_TEMPLATE.RESET_PWD.getTemplateName());
        emailHelper.sendHtmlMail(mailBean);
    }

    @Transactional
    public void modifyPwd(String loginName,String oldPwd,String newPwd,Integer userId){
        User user = this.findUser(loginName);
        if(user == null){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getZhMsg());
        }
        if(!passwordEncoder.matches(oldPwd,user.getLoginPwd())){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_INVALID_PASSWORD.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_INVALID_PASSWORD.getZhMsg());
        }
        user.setLoginPwd(passwordEncoder.encode(newPwd));
        user.setUpdateTime(new Date());
        user.setUpdateUserId(userId);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 重置密码随机生成10位密码数
     * @return
     */
    private static String generateRandomPassword() {
        int length = 10;
        char[] ss = new char[10];
        int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                ss[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                ss[i] = (char) ('a' + Math.random() * 26);
            else
                ss[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(ss) + "$";
    }
}
