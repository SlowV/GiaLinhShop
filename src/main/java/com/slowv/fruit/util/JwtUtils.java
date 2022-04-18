package com.slowv.fruit.util;

import com.slowv.fruit.web.errors.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private JwtUtils(){}

    public static String generateToken(String jwtSecret, String uuidSubject, int jwtExpiration) {
        return Jwts.builder()
                .setSubject(uuidSubject)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String getUserUuidFromJwtToken(String token, String jwtSecret) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public static void validateJwtToken(String token, String jwtSecret) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthenticationException("Invalid token");
        }
    }
}
