package com.br.nupex.controleusuario.controle_usuario_api.infra.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.nupex.controleusuario.controle_usuario_api.domain.user.User;
import com.br.nupex.controleusuario.controle_usuario_api.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);  
        if (token != null) {
            var login = tokenService.validateToken(token);  
            if (login != null) {
                User user = userRepository.findByEmail(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);  
                System.out.println("Usuário autenticado: " + user.getEmail());
            } else {
                System.out.println("Token inválido");
            }
        }
        filterChain.doFilter(request, response); 
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            System.out.println("Authorization header is missing");
            return null;
        }
        System.out.println("Authorization header: " + authHeader);
        return authHeader.replace("Bearer ", "");    //Bearer é o prefixo utilizado com jwt p tokens 
    }

}
