package org.enriqueboronat.pruebahexagonal.config.security.services;

import org.enriqueboronat.pruebahexagonal.config.security.dtos.JwtAuthenticationResponse;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.LogInRequest;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.SignUpRequest;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.UserEntityRepository;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AutenticationServiceTest {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    JwtService jwtService;

    @Test
    public void signUpTest(){
        SignUpRequest signUpRequest = new SignUpRequest(
            "prueba300","prueba300","prueba300@gmail.com","123",true
        );
        List<UserEntity> userList = userEntityRepository.findAll();

        //Act
        authenticationService.signUp(signUpRequest);
        List<UserEntity> newUserList = userEntityRepository.findAll();

        //Assert
        assertEquals(userList.size()+1,newUserList.size());

        UserEntity userEntityToDelete = userEntityRepository.findByUsername("prueba300@gmail.com").get();
        UUID id = userEntityToDelete.getId();
        userEntityRepository.deleteById(id);
    }

    @Test
    public void loginTest(){
        LogInRequest loginRequest = new LogInRequest("prueba300@gmail.com","123aA?45");

        UserEntity userTest = UserEntity.builder()
                        .username("prueba300@gmail.com")
                        .role(RolEnum.USER)
                        .password(new BCryptPasswordEncoder().encode("123aA?45"))
                        .firstname("prueba300")
                        .lastname("prueba300")
                        .email("prueba300@gmail.com")
                        .address(null)
                        .acceptedTerms(true)
                        .activityPermission(false)
                        .birthday(null)
                        .build();
            userEntityRepository.save(userTest);

        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.login(loginRequest);
        String token = jwtAuthenticationResponse.getToken();
        String username = jwtService.getEmailFromToken(token);
        
        assertEquals("prueba300@gmail.com", username);

        UserEntity userEntityToDelete = userEntityRepository.findByUsername("prueba300@gmail.com").get();
        UUID id = userEntityToDelete.getId();
        userEntityRepository.deleteById(id);
        
    }
}
