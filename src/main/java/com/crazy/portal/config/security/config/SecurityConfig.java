package com.crazy.portal.config.security.config;

import com.crazy.portal.config.security.JwtUserService;
import com.crazy.portal.config.security.filter.JwtAuthenticationProvider;
import com.crazy.portal.config.security.filter.RequestFilter;
import com.crazy.portal.config.security.handler.AuthenticationFailHandler;
import com.crazy.portal.config.security.handler.JwtRefreshSuccessHandler;
import com.crazy.portal.config.security.handler.LoginSuccessHandler;
import com.crazy.portal.config.security.handler.TokenClearLogoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.kerberos.authentication.KerberosAuthenticationProvider;
import org.springframework.security.kerberos.authentication.KerberosServiceAuthenticationProvider;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosClient;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosTicketValidator;
import org.springframework.security.kerberos.web.authentication.SpnegoAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] permissiveUrl = new String[]{"/**"};

    @Value("${app.service-principal}")
    private String servicePrincipal;

    @Value("${app.keytab-location}")
    private String keytabLocation;

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
    protected AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService());
        return daoProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(permissiveUrl).permitAll()
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
//                .addFilterBefore(spnegoAuthenticationProcessingFilter(authenticationManagerBean()),BasicAuthenticationFilter.class)
//                //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new RequestFilter(), CorsFilter.class)

                //添加登录filter
//                .apply(new LoginConfigurer<>()).loginSuccessHandler(loginSuccessHandler)
//                .and()
                //添加token的filter
//                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler)
//                .permissiveRequestUrls(permissiveUrl)
//                .and()
                //使用默认的logoutFilter
                .logout()
                //logout时清除token
                .addLogoutHandler(tokenClearLogoutHandler)
                //logout成功后返回200
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
            .authenticationProvider(daoAuthenticationProvider())
            .authenticationProvider(kerberosAuthenticationProvider())
            .authenticationProvider(kerberosServiceAuthenticationProvider())
            .authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return jwtUserService;
    }

    @Bean
    public KerberosAuthenticationProvider kerberosAuthenticationProvider() {
        KerberosAuthenticationProvider provider = new KerberosAuthenticationProvider();
        SunJaasKerberosClient client = new SunJaasKerberosClient();
        client.setDebug(true);
        provider.setKerberosClient(client);
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public KerberosServiceAuthenticationProvider kerberosServiceAuthenticationProvider() {
        KerberosServiceAuthenticationProvider provider = new KerberosServiceAuthenticationProvider();
        provider.setTicketValidator(sunJaasKerberosTicketValidator());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {
        SunJaasKerberosTicketValidator ticketValidator = new SunJaasKerberosTicketValidator();
        ticketValidator.setServicePrincipal(servicePrincipal);
        ticketValidator.setKeyTabLocation(new FileSystemResource(keytabLocation));
        ticketValidator.setDebug(true);
        return ticketValidator;
    }

    @Bean
    public SpnegoAuthenticationProcessingFilter spnegoAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager) {
        SpnegoAuthenticationProcessingFilter filter = new SpnegoAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setSuccessHandler(loginSuccessHandler);
        filter.setFailureHandler(new AuthenticationFailHandler());
        return filter;
    }
}
