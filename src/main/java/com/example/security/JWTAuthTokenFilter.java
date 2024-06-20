package com.example.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * JWT認証を行うFilter.
 * 
 * @author char5742
 */
@Component
public class JWTAuthTokenFilter implements Filter {

    @Autowired
    private JsonWebTokenUtil jwtUtil;

    private final Logger logger = LoggerFactory.getLogger(JWTAuthTokenFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilterInternal((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    private void doFilterInternal(
            HttpServletRequest request) {
        if (jwtUtil.authorize(request)) {
            logger.debug("JWT認証成功");
        }
    }

}
