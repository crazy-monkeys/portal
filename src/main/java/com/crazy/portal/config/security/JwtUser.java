package com.crazy.portal.config.security;

import com.crazy.portal.entity.system.UserDO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:58 2019/4/9
 * @Modified by:
 */
public class JwtUser implements UserDetails {

    private UserDO userDO;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(UserDO userDO,String username,String password,Collection<? extends GrantedAuthority> authorities) {
        this.userDO = userDO;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public static JwtUser.UserBuilder builder() {
        return new JwtUser.UserBuilder();
    }

    public static class UserBuilder {
        private UserDO userDO;
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;

        private UserBuilder() {}

        public JwtUser.UserBuilder userDO(UserDO userDO) {
            Assert.notNull(userDO, "username cannot be null");
            this.userDO = userDO;
            return this;
        }

        public JwtUser.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }
        public JwtUser.UserBuilder password(String password) {
            Assert.notNull(password, "username cannot be null");
            this.password = password;
            return this;
        }
        public JwtUser.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }
        public UserDetails build() {
            return new JwtUser(userDO,username,password,authorities);
        }
    }
}
