package org.enriqueboronat.pruebahexagonal.config.security.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.exceptions.NotFoundException;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.models.UserSecurity;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.UserEntityRepository;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.models.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
public class UserSecurityService {
    
    private final static String PRE_NOT_FOUND = "Error 404 user with id = ";
    private final static String POST_NOT_FOUND = " Not Found";
    private final static String USER_NOT_FOUND = "user not found";

    private final UserEntityRepository userEntityRepository;

    public UserSecurity findUserEntityById(UUID id){
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() ->
            new NotFoundException(PRE_NOT_FOUND + id + POST_NOT_FOUND)
        );

        return UserSecurity.builder()
        .email(userEntity.getEmail())
        .id(userEntity.getId())
        .role(userEntity.getRole())
        .username(userEntity.getUsername())
        .build();
    }

    public UserSecurity findUserEntityByEmail(String email){
        UserEntity userEntity = userEntityRepository.findByEmail(email).orElseThrow(()
        -> new NotFoundException(USER_NOT_FOUND));

        return UserSecurity.builder()
        .email(userEntity.getEmail())
        .id(userEntity.getId())
        .role(userEntity.getRole())
        .username(userEntity.getUsername())
        .build();
    }

    public String updateRole(UUID id, RolEnum rol){
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() ->
            new NotFoundException(PRE_NOT_FOUND + id + POST_NOT_FOUND)
        );

        userEntity.setRole(rol);
        userEntityRepository.save(userEntity);
        return "rol updated";     
    }

    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username){
                UserEntity userEntity = userEntityRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(USER_NOT_FOUND));
                return UserSecurity.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
            }
        };
    }
}
