package com.ma.apigateway.security;

import com.ma.apigateway.config.JwtConfig;
import com.ma.apigateway.exception.CJwtTokenIncorrectStructureException;
import com.ma.apigateway.exception.CJwtTokenMalformedException;
import com.ma.apigateway.exception.CJwtTokenMissingException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtConfig config;

    public String generateToken(String id){
        Claims claims = Jwts.claims().setSubject(id);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + config.getValidity() * 1000 * 60;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp).signWith(SignatureAlgorithm.HS512, config.getSecret()).compact();
    }

    public void validateToken(final String header) throws CJwtTokenMalformedException, CJwtTokenMissingException {
        try{
            String[] parts = header.split(" ");
            if(parts.length != 2 || "Bearer".equals(parts[0])){
                throw new CJwtTokenIncorrectStructureException("Incorrect Authentication Structure");
            }
            Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(parts[1]);
        } catch (SignatureException ex) {
            throw new CJwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new CJwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new CJwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex){
            throw new CJwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex){
            throw new CJwtTokenMissingException("JWT claims string is empty");
        }
    }

}
