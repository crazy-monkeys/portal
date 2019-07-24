package com.crazy.portal.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:02 2019/4/20
 * @Modified by:
 */
@Component
@Slf4j
public class JwtUserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Value("${app.ad-domain}")
    private String adDomain;
    private PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final static String secret = "ioiuffkII#022";

    public JwtUserService(UserMapper userMapper,
                          UserRoleMapper userRoleMapper,
                          RoleMapper roleMapper) {

        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,LockedException {
        boolean isInternalEmployee = username.contains(adDomain);
        User user;
        if(isInternalEmployee){
            String loginName = username.substring(0,username.indexOf(adDomain)-1);
            user = userMapper.findByLoginName(loginName);
        }else{
            user = userMapper.findByLoginName(username);
        }
        if (user == null) {
            if(!isInternalEmployee){
                String errorMsg = String.format("No user found with username '%s'.", username);
                log.error(errorMsg);
                throw new UsernameNotFoundException(errorMsg);
            }
            String currentLoginName = username.substring(0,username.indexOf(adDomain)-1);
            //初始化用户
            String loginName = this.getLoginName(currentLoginName);
            user = new User();
            user.setLoginName(loginName);
            user.setCustomerName(username);
            user.setLoginPwd(passwordEncoder.encode("123456"));
            user.setActive((short)1);
            user.setUserStatus(1);
            user.setPwdInvalidTime(DateUtil.addDays(new Date(),365));
            user.setRegTime(new Date());
            user.setUserType(Enums.USER_TYPE.internal.toString());
            user.setCreateUserId(1);
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);

            Role basicRole = roleMapper.queryRoleList("BASIC_ROLE").get(0);
            UserRole userRole = new UserRole();
            userRole.setCreateId(1);
            userRole.setCreateTime(new Date());
            userRole.setRoleId(basicRole.getId());
            userRole.setUserId(user.getId());
            userRoleMapper.insertSelective(userRole);

            return new JwtUser(user,user.getLoginName(),user.getLoginPwd(),
                    Collections.singleton(new SimpleGrantedAuthority("BASIC_ROLE")));
        }
        if(user.getUserStatus().equals(0)){
            throw new LockedException("locked");
        }
        if(user.getPwdInvalidTime().before(new Date())){
            throw new LockedException("password expiration");
        }
        UserRole userRole = userRoleMapper.selectByUserId(user.getId());
        Role role = roleMapper.findById(userRole.getRoleId());
        return new JwtUser(user,user.getLoginName(),user.getLoginPwd(),
                Collections.singleton(new SimpleGrantedAuthority(role.getRoleCode())));
    }

    /**
     * TODO 将salt保存到数据库或者缓存中 BCrypt.gensalt()
     * @param user
     * @return
     */
    public String saveUserLoginInfo(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //设置15分钟过期 TODO 暂时设置10天过期
        Date date = new Date(System.currentTimeMillis()+1000*60*15*1000);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 清除数据库或者缓存中登录secret
     */
    public void deleteUserLoginInfo(String username) {
        //TODO
        log.info("用户{}完成退出",username);
    }

    public UserDetails getUserLoginInfo(String username) {
        JwtUser user = (JwtUser) loadUserByUsername(username);
        return JwtUser.builder()
                .userDO(user.getUser())
                .username(user.getUsername())
                .password(secret)
                .authorities(user.getAuthorities())
                .build();
    }

    private String getLoginName(String loginName) {
        if(userMapper.findByLoginName(loginName) == null){
            return loginName;
        }else{
            loginName = loginName + StringUtil.creatRandom(6);
            return getLoginName(loginName);
        }
    }
}
