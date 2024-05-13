package org.enriqueboronat.pruebahexagonal.config.security.services;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.exceptions.NotFoundException;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.JwtAuthenticationResponse;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.LogInRequest;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.SignUpRequest;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.models.UserSecurity;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.UserEntityRepository;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.models.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final static String PRE_NOT_FOUND = "Error 404 user with id = ";
    private final static String POST_NOT_FOUND = " Not Found";

    public void signUp(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setRole(RolEnum.USER);
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setUsername(signUpRequest.getEmail());
        userEntity.setFirstname(signUpRequest.getFirstname());
        userEntity.setLastname(signUpRequest.getLastname());
        userEntity.setAcceptedTerms(signUpRequest.getAcceptedTerms());
        userEntity.setAddress(""); 
        userEntity.setBirthday(null);
        userEntity.setActivityPermission(false);

        userEntityRepository.save(userEntity);
    }

    public JwtAuthenticationResponse login(LogInRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        UserEntity user = userEntityRepository.findByUsername(loginRequest.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        UserSecurity userSecurity = UserSecurity.builder()
        .id(user.getId())
        .email(user.getEmail())
        .role(user.getRole())
        .username(user.getUsername())
        .password(user.getPassword())
        .build();
      
        String jwt = jwtService.generateToken(userSecurity);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;

    }

    public String updatePassword(String password, UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElseThrow(()
        -> new NotFoundException(PRE_NOT_FOUND + id + POST_NOT_FOUND));

        user.setPassword(passwordEncoder.encode(password));
        userEntityRepository.save(user);
        return "Password updated";
    }

}