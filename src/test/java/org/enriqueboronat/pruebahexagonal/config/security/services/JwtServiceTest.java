package org.enriqueboronat.pruebahexagonal.config.security.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.models.UserSecurity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    UserSecurity userEntity = UserSecurity.builder()
            .email("prueba300@gmail.com")
            .username("prueba300@gmail.com")
            .role(RolEnum.USER)
            .password(new BCryptPasswordEncoder().encode("123"))
            .build();

    @Test
    public void generateTokenAndGetUserNameFromTokenTest() { 

        String token = jwtService.generateToken(userEntity);
        String username = jwtService.getEmailFromToken(token);

        assertEquals("prueba300@gmail.com", username);
    }

    @Test
    public void isTokenValidTest() {

        String token = jwtService.generateToken(userEntity);

        assertTrue(jwtService.isTokenValid(token, userEntity));

    }

    @Test
    public void isTokenValidNotValidTest() {

        UserSecurity otherUserEntity = UserSecurity.builder()
            .email("patata@gmail.com")
            .username("patata@gmail.com")
            .role(RolEnum.USER)
            .password(new BCryptPasswordEncoder().encode("123"))
            .build();

        String token = jwtService.generateToken(userEntity);

        assertFalse(jwtService.isTokenValid(token, otherUserEntity));

    }

    @Test
    public void isTokenNotExpiredTest() {

        byte[] key = Decoders.BASE64.decode("c894bf3ee9430e7f77a047f0039bd5e27eee89d06a747443f059ce57d982b484");
        Key signinKey = Keys.hmacShaKeyFor(key);

        String Token = Jwts.builder().setSubject(userEntity.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(signinKey, SignatureAlgorithm.HS256)
                .compact();

        assertFalse(jwtService.isTokenExpired(Token));

    }

    @Test
    public void isTokenExpiredTest() {

        byte[] key = Decoders.BASE64.decode("c894bf3ee9430e7f77a047f0039bd5e27eee89d06a747443f059ce57d982b484");
        Key signinKey = Keys.hmacShaKeyFor(key);

        String expiredToken = Jwts.builder().setSubject(userEntity.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 24))
            .signWith(signinKey, SignatureAlgorithm.HS256)
            .compact();

        assertTrue(jwtService.isTokenExpired(expiredToken));

    }
}
