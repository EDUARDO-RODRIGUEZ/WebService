package com.eduardo.app.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expire}")
    private int JWT_EXPIRE;

    public Optional<String> validateToken(String token) {
        try {
            String nroAccount = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
            return Optional.of(nroAccount);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String generateToken(String nroAccount) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(calendar.HOUR, JWT_EXPIRE);
        Date expire = calendar.getTime();
        String jwToken = Jwts.builder()
                .setSubject(nroAccount)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return jwToken;
    }
}
