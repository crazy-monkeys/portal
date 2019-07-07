package com.crazy.portal.config.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:14 2019/4/20
 * @Modified by:
 */
public class OptionsRequestFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getMethod().equals("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Methods", "DELETE,GET,POST,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
