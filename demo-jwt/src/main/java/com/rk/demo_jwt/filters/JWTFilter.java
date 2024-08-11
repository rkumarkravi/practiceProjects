package com.rk.demo_jwt.filters;

import com.rk.demo_jwt.utils.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = Optional.ofNullable(jwtTokenProvider.resolveToken(request));
        try {
            token.ifPresent(s -> {
                SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(s));
            });

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
