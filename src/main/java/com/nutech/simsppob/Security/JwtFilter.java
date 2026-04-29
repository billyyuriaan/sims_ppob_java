/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Repositorys.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author billyyuriaan
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,                                
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException 
    {
        String header = request.getHeader("Authorization");
        
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            String email = JwtUtil.getUserEmail(token);

            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                List.of()
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
            
}
