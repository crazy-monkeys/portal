package com.crazy.portal.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:02 2019/4/20
 * @Modified by:
 */
@Component
public class JwtUserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    private final static String secret = "ioiuffkII#022";

    public JwtUserService(UserMapper userMapper,
                          UserRoleMapper userRoleMapper,
                          RoleMapper roleMapper) {

        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException,LockedException {
        User user = userMapper.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        if(user.getUserStatus().equals(0)){
            throw new LockedException("locked");
        }
        if(user.getPwdInvalidTime().before(new Date())){
            throw new LockedException("password expiration");
        }
        UserRole userRole = userRoleMapper.selectByUserId(user.getId());
        Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
        return new JwtUser(user,user.getLoginName(),user.getLoginPwd(),
                Collections.singleton(new SimpleGrantedAuthority(role.getRoleName())));
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

    }

    public UserDetails getUserLoginInfo(String username) {
        JwtUser user = loadUserByUsername(username);
        //暂时仅支持一用户一角色
        return JwtUser.builder()
                .userDO(user.getUser())
                .username(user.getUsername())
                .password(secret)
                .authorities(user.getAuthorities())
                .build();
    }
}
