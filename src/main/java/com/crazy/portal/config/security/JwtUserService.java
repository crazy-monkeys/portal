package com.crazy.portal.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crazy.portal.dao.system.RoleDOMapper;
import com.crazy.portal.dao.system.UserDOMapper;
import com.crazy.portal.dao.system.UserRoleDOMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserRole;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:02 2019/4/20
 * @Modified by:
 */
public class JwtUserService implements UserDetailsService {

    private final static String secret = "ioiuffkII#022";

    private UserDOMapper userDOMapper;
    private UserRoleDOMapper userRoleDOMapper;
    private RoleDOMapper roleDOMapper;

    public JwtUserService(UserDOMapper userDOMapper,
                          UserRoleDOMapper userRoleDOMapper,
                          RoleDOMapper roleDOMapper) {

        this.userDOMapper = userDOMapper;
        this.userRoleDOMapper = userRoleDOMapper;
        this.roleDOMapper = roleDOMapper;
    }



    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException,LockedException {
        User user = userDOMapper.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        if(user.getUserStatus().equals(0)){
            throw new LockedException(user.getLoginName()+">>>"+"locked");
        }
        List<Integer> roleIds = userRoleDOMapper.selectUserRoleByUserId(user.getId());

        Collection<? extends GrantedAuthority> roleNames = roleIds.stream().map(x->{
            Role role = roleDOMapper.selectByPrimaryKey(x);
            return  new SimpleGrantedAuthority(role.getRoleName());
        }).collect(Collectors.toList());

        return new JwtUser(user,user.getLoginName(),user.getLoginPwd(),roleNames);
    }

    /**
     * TODO 将salt保存到数据库或者缓存中 BCrypt.gensalt()
     * @param user
     * @return
     */
    public String saveUserLoginInfo(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //设置15分钟过期 TODO 暂时延长1000倍
        Date date = new Date(System.currentTimeMillis()+1000*60*15*1000);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * @todo 清除数据库或者缓存中登录salt
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
