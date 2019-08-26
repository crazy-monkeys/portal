package com.crazy.portal.service.system;

import com.crazy.portal.bean.customer.CustomerShipBean;
import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.bean.system.SubAgentVO;
import com.crazy.portal.bean.system.UserPositionBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private EmailHelper emailHelper;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private InternalUserMapper internalUserMapper;


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
     * @param basicInfo
     * @return
     */
    @Transactional
    public int updateSubAgent(SubAgentVO basicInfo,Integer userId){
        User user = this.findUser(basicInfo.getLoginName());
        if(!user.getUserType().equals(Enums.USER_TYPE.subAgent.toString())){
            log.error("当前用户{} 修改的用户类型并不是子账号类型",userId);
            throw new BusinessException(ErrorCodes.CommonEnum.SYSTEM_EXCEPTION.getCode(),
                    ErrorCodes.CommonEnum.SYSTEM_EXCEPTION.getZhMsg());
        }
        user.setEmail(basicInfo.getMail());
        user.setCustomerName(basicInfo.getCustomerName());
        if(StringUtil.isEmpty(basicInfo.getRoleCode())){
            this.subAgentEmpowerment(basicInfo,userId);
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 子账号注册
     * @param subAgentUser
     * @parm currentUser
     * @return
     */
    @Transactional
    public void regSubAgent(SubAgentVO subAgentUser,Integer userId) {
        String username = subAgentUser.getLoginName();
        if (userMapper.findByLoginName(username) != null) {
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_EXISTS.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_EXISTS.getZhMsg());
        }
        User user = new User();
        user.setLoginName(subAgentUser.getLoginName());
        user.setEmail(subAgentUser.getMail());
        user.setCustomerName(subAgentUser.getCustomerName());
        String password = PortalUtil.generateRandomPassword();
        user.setLoginPwd(passwordEncoder.encode(password));
        user.setUserStatus(Enums.USER_STATUS.normal.getCode());
        //只允许子账号注册
        user.setUserType(Enums.USER_TYPE.subAgent.toString());
        user.setRegTime(new Date());
        user.setCreateUserId(userId);
        user.setCreateTime(new Date());
        user.setPwdInvalidTime(DateUtil.addDays(new Date(),365 * 10));
        user.setActive((short)1);
        user.setUserStatus(1);
        int result = userMapper.insertSelective(user);
        BusinessUtil.assertTrue(result==1,ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
        BusinessUtil.notNull(user.getId(), ErrorCodes.SystemManagerEnum.USER_SAVE_FAILED);

        //子角色赋权
        this.subAgentEmpowerment(subAgentUser, user.getId());

        //发送邮件通知用户
        MailBean mailBean = new MailBean();
        mailBean.setTos(user.getEmail());
        mailBean.setSubject("账号开通邮件");
        Map<String,String> map = new HashMap<>();
        map.put("loginName",user.getLoginName());
        map.put("password",password);
        mailBean.setParams(map);
        mailBean.setTemplateName(Enums.MAIL_TEMPLATE.USER_CREATE.getTemplateName());
        emailHelper.sendHtmlMail(mailBean);
    }

    /**
     * 子角色赋权
     * @param subAgentUser
     * @param userId
     */
    private void subAgentEmpowerment(SubAgentVO subAgentUser, Integer userId) {
        //分配指定的角色
        Role role = roleMapper.findRoleByCode(subAgentUser.getRoleCode());
        BusinessUtil.notNull(role, ErrorCodes.SystemManagerEnum.ROLE_NOT_EXIST);

        BusinessUtil.assertTrue(Enums.ROLE_TYPE.SUB_USER.getRoleType() == role.getRoleType().intValue(),
                ErrorCodes.SystemManagerEnum.ROLE_SAVE_FAILED);

        userRoleMapper.insertSelective(new UserRole(userId,role.getId()));
    }

    /**
     * 获取子账号信息
     * @param userQuery
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> getSubAgents(User userQuery,Integer pageNum,Integer pageSize){
        PortalUtil.defaultStartPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.selectUserWithPage(userQuery));
    }

    /**
     * 重置密码
     * @param loginName
     * @param currentUser
     */
    @Transactional
    public void resetUserPwd(String loginName,User currentUser){
        User user = this.findUser(loginName);
        if(user == null){
            throw new BusinessException(ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getCode(),
                    ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS.getZhMsg());
        }
        user.setUpdateTime(new Date());
        user.setUpdateUserId(currentUser.getId());
        String newPasswod = PortalUtil.generateRandomPassword();
        user.setLoginPwd(passwordEncoder.encode(newPasswod));
        userMapper.updateByPrimaryKeySelective(user);

        //发送邮件
        MailBean mailBean = new MailBean();
        mailBean.setTos(user.getEmail());
        mailBean.setSubject("密码重置邮件");

        Map<String,String> map = new HashMap<>();
        map.put("loginName",user.getLoginName());
        map.put("password",newPasswod);
        mailBean.setParams(map);
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
     * 获取 customer的内外部客户
     * @param dealerId
     */
    public CustomerShipBean getUserShips(Integer dealerId){
        return customerInfoService.selectDealerShip(dealerId);
    }

    /**
     * 获取登陆用户的负责代理商
     * @param userId
     * @return
     */
    public List<Integer> getUserDealers(Integer userId){
        return Arrays.asList(1);
    }

    public InternalUser getUserPosition(Integer userId){
        return internalUserMapper.selectByUserId(userId);
    }
}
