package com.crazy.portal.config.security.config;

import com.crazy.portal.config.security.JwtAuthenticationProvider;
import com.crazy.portal.config.security.JwtUserService;
import com.crazy.portal.config.security.filter.OptionsRequestFilter;
import com.crazy.portal.config.security.handler.JwtRefreshSuccessHandler;
import com.crazy.portal.config.security.handler.LoginSuccessHandler;
import com.crazy.portal.config.security.handler.TokenClearLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] permissiveUrl = new String[]{"/logout"};

    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private JwtUserService jwtUserService;
    @Resource
    private JwtRefreshSuccessHandler jwtRefreshSuccessHandler;
    @Resource
    private TokenClearLogoutHandler tokenClearLogoutHandler;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtUserService);
    }

    @Bean
    protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService());
        return daoProvider;
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/login","/").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().disable()
                .csrf().disable()
                .cors()
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                        new Header("Access-control-Allow-Origin","*"),
                        new Header("Access-Control-Expose-Headers","Authorization"))))
                .frameOptions().disable()
                .and()
                //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                //添加登录filter
                .apply(new LoginConfigurer<>()).loginSuccessHandler(loginSuccessHandler)
                .and()
                //添加token的filter
                .apply(new JwtLoginConfigurer<>())
                        .tokenValidSuccessHandler(jwtRefreshSuccessHandler)
                        .permissiveRequestUrls(permissiveUrl)
                .and()
                //使用默认的logoutFilter
                .logout()
                //logout时清除token
                .addLogoutHandler(tokenClearLogoutHandler)
                //logout成功后返回200
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider())
                .authenticationProvider(jwtAuthenticationProvider());
    }


    @Override
    protected UserDetailsService userDetailsService() {
        return jwtUserService;
    }
}
