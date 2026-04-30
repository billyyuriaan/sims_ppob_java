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
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Repositorys.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
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
      FilterChain filterChain) throws ServletException, IOException {

    String path = request.getRequestURI();

    // Skip public endpoints
    if (path.equals("/registration") ||
        path.equals("/login") ||
        path.equals("/banner")) {
      filterChain.doFilter(request, response);
      return;
    }

    String header = request.getHeader("Authorization");

    try {
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);

        // This line will throw ExpiredJwtException if expired
        String email = JwtUtil.getUserEmail(token);

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
              user,
              null,
              List.of());

          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      }

      filterChain.doFilter(request, response);

    } catch (ExpiredJwtException e) {
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(
          "{ \"status\": 401, \"message\": \"Token expired\", \"data\": null }");

    } catch (JwtException | IllegalArgumentException e) {
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(
          "{ \"status\": 401, \"message\": \"Invalid token\", \"data\": null }");
    }
  }
}
