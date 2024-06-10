package com.logistica.pdv.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthFilter extends OncePerRequestFilter {

    private JWTService _JwtService;
    private CustomUserDetailService _CustomUserDetailService;

    public JWTAuthFilter(JWTService jwtService, CustomUserDetailService customUserDetailService){
        this._JwtService = jwtService;
        this._CustomUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){

                String token = authorizationHeader.split(" ")[1];
                String username = _JwtService.getUsername(token);

                UserDetails user = _CustomUserDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken userContext = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                userContext.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userContext);
            }


            filterChain.doFilter(request, response);

        }catch (RuntimeException err){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(err.getMessage());
            response.getWriter().flush();
        }


    }

}
