package org.enriqueboronat.pruebahexagonal.config.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String CODE_STRING = "c894bf3ee9430e7f77a047f0039bd5e27eee89d06a747443f059ce57d982b484";

        public String generateToken(UserDetails user){
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +  1000  * 60 * 24))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSiginKey(){
        byte[] key = Decoders.BASE64.decode(CODE_STRING);
        return Keys.hmacShaKeyFor(key);
    }

    public String getEmailFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsFunction){
        Claims claims = extractAllClaims(token);
        
        return claimsFunction.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getSiginKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        try {
            return getClaim(token,Claims::getExpiration).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

}