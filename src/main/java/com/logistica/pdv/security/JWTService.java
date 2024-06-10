package com.logistica.pdv.security;

import com.logistica.pdv.DTO.TokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Service
@NoArgsConstructor
@Data
public class JWTService {
    @Value("${jwt.token.expiration_time}")
    private int expirationTime;

    @Value("${jwt.token.key}")
    private String key;

    public TokenDTO generateToken(String username){

        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.MINUTE, expirationTime);
        Date expirationDate = currentDate.getTime();

        Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();

        return TokenDTO.builder()
                .Token(token)
                .expirationTime(expirationTime)
                .build();
    }


    public String getUsername(String token){
        Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpirationTime(String token){
        Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
