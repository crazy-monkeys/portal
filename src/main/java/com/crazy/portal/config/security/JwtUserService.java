package com.crazy.portal.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crazy.portal.dao.system.RoleDOMapper;
import com.crazy.portal.dao.system.UserDOMapper;
import com.crazy.portal.dao.system.UserRoleDOMapper;
import com.crazy.portal.entity.system.RoleDO;
import com.crazy.portal.entity.system.UserDO;
import com.crazy.portal.entity.system.UserRoleDO;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collections;
import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:02 2019/4/20
 * @Modified by:
 */
public class JwtUserService implements UserDetailsService {

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
        UserDO user = userDOMapper.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        if(user.getUserStatus().equals(0)){
            throw new LockedException("locked");
        }
        UserRoleDO userRoleDO = userRoleDOMapper.selectByUserId(user.getId());
        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(userRoleDO.getRoleId());
        return new JwtUser(user,user.getLoginName(),user.getLoginPwd(),
                Collections.singleton(new SimpleGrantedAuthority(roleDO.getRoleName())));
    }

    /**
     * TODO 将salt保存到数据库或者缓存中 BCrypt.gensalt()
     * @param user
     * @return
     */
    public String saveUserLoginInfo(UserDetails user) {
        String salt = "123456ef";
        Algorithm algorithm = Algorithm.HMAC256(salt);
        //设置15分钟过期
        Date date = new Date(System.currentTimeMillis()+1000*60*15);
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
        String salt = "123456ef";
        JwtUser user = loadUserByUsername(username);
        //暂时仅支持一用户一角色
        return JwtUser.builder()
                .userDO(user.getUserDO())
                .username(user.getUsername())
                .password(salt)
                .authorities(user.getAuthorities())
                .build();
    }
}
