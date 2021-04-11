package com.project.techupdate.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.techupdate.dto.Token;
import com.project.techupdate.entity.Role;
import com.project.techupdate.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.google.gson.Gson;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
//            Set<Role> roles = user.getRole();
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            for(Role role: roles){
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//            }
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                                Authentication auth) throws IOException, ServletException {
            String authorities = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            String token = JWT.create()
                    .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                    .withClaim("roles",authorities)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
            res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

//          String tokenStr = new Gson().toJson(new Token(SecurityConstants.TOKEN_PREFIX + token));
            String tokenStr = new Gson().toJson(new Token(SecurityConstants.TOKEN_PREFIX + token, ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername()));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(tokenStr);


        }
}