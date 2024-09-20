package com.br.nupex.controleusuario.controle_usuario_api.infra.security;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.br.nupex.controleusuario.controle_usuario_api.domain.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; // Valor armazenado em application.properties
    
    
   
    
    
    
    
    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .setIssuer("controle-usuario-api")
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))  
            .signWith(SignatureAlgorithm.HS256, secret)  
            .compact();
    }

    
    
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(secret) // a chave usada para assinar o token
                .parseClaimsJws(token)
                .getBody();
            return claims.getSubject(); // ou o campo que você está usando para identificar o usuário
        } catch (JwtException e) {
            // Log da exceção para entendimento
            System.out.println("Token inválido: " + e.getMessage());
            return null;
        }
    }


    private Instant generateExpirationDate() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2).toInstant(ZoneOffset.UTC);
    }
}


/* public String generateToken(User user) {   
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);   // Algoritmo de criptografia HMAC256

            return JWT.create()							
                    .withIssuer("controle-usuario-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException except) {
            throw new RuntimeException("Erro ao criar o token JWT", except);
        }
 */