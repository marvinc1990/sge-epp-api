/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.security;

import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.util.BeanUtil;
import com.example.sge.epp.util.ExceptionUtil;
import com.example.sge.epp.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author MARVIN
 */
@Component
public class CustomJwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            /* Validate Authorization */
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
                /* Validate Bearer */
                if (authorizationHeader.startsWith("Bearer ")) {
                    /* Get Token */
                    String token = authorizationHeader.replace("Bearer ", "");
                    if (token != null && !token.isEmpty()) {
                        String username = JwtUtil.getUsername(token);
                        if (username != null && !username.isEmpty()) {
                            /* Initialize Bean */
                            customUserDetailsService = (CustomUserDetailsService) BeanUtil.getBean(request, customUserDetailsService, CustomUserDetailsService.class);
                            if (customUserDetailsService != null) {
                                /* Get User */
                                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                                /* Validate Token */
                                if (JwtUtil.validateToken(token, username)) {
                                    /* Set Authentication */
                                    UsernamePasswordAuthenticationToken authentication
                                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    SecurityContextHolder.getContext().setAuthentication(authentication);
                                } else {
                                    throw new RuntimeException("Token invalid");
                                }
                            } else {
                                throw new RuntimeException("CustomUserDetailsService not found");
                            }
                        } else {
                            throw new RuntimeException("Username Token not found");
                        }
                    } else {
                        throw new RuntimeException("Token incorrect");
                    }
                } else {
                    throw new RuntimeException("Authorization format incorrect");
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            ExceptionUtil.writeException(SgeApiResponse.failed("Token expired", e.getMessage(), HttpStatus.UNAUTHORIZED.value()), response);
        } catch (IOException | RuntimeException | ServletException ex) {
            ExceptionUtil.writeException(SgeApiResponse.failed(ex.getMessage()), response);
        }
    }
    
}
