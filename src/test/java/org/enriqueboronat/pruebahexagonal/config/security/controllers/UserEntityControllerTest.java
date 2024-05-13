package org.enriqueboronat.pruebahexagonal.config.security.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.enriqueboronat.pruebahexagonal.config.security.controllers.dtos.UserDataDto;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.LogInRequest;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.PasswordDto;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.RolDto;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.SignUpRequest;
import org.enriqueboronat.pruebahexagonal.config.security.mocks.SignUpRequestMock;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.models.UserSecurity;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.UserEntityRepository;
import org.enriqueboronat.pruebahexagonal.config.security.repositories.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UserEntityControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        UserEntityRepository userEntityRepository;

        final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                        .create();

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

        SignUpRequest userRegisterTest = SignUpRequest.builder()
                        .firstname("prueba300")
                        .lastname("prueba300")
                        .email("prueba300@gmail.com")
                        .password("123aA?45")
                        .acceptedTerms(true)
                        .build();

        SignUpRequestMock userRegisterTestMock = SignUpRequestMock.builder()
                        .firstname("prueba300")
                        .lastname("prueba300")
                        .email("prueba300@gmail.com")
                        .password("123aA?45")
                        .acceptedTerms(true)
                        .build();

        UserDataDto userDataDto = UserDataDto.builder()
                        .id(null)
                        .firstname("prueba300")
                        .lastname("prueba300")
                        .email("prueba300@gmail.com")
                        .address(null)
                        .birthday(null)
                        .build();

        @Test
        void signupTest() throws Exception {

                List<UserEntity> userList = userEntityRepository.findAll();

                String userJson = gson.toJson(userRegisterTest);
                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isOk());
                List<UserEntity> newUserList = userEntityRepository.findAll();
                assertEquals(userList.size() + 1, newUserList.size());
        }

        @Test
        void signupNotnullFirstnameTest() throws Exception {

                userRegisterTestMock.setFirstname(null);
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNotEmptyFirstnameTest() throws Exception {
                userRegisterTestMock.setFirstname("");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupSizeMaxFirstnameTest() throws Exception {
                userRegisterTestMock.setFirstname("012345678901234567890123456789");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNotnullLastnameTest() throws Exception {
                userRegisterTestMock.setLastname(null);
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNotEmptyLastnameTest() throws Exception {
                userRegisterTestMock.setLastname("");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupSizeMaxLastnameTest() throws Exception {
                userRegisterTestMock.setLastname("012345678901234567890123456789prueba300");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNotnullEmailTest() throws Exception {
                userRegisterTestMock.setEmail(null);
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNotEmptyEmailTest() throws Exception {
                userRegisterTestMock.setEmail("");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupNoIsEmailTest() throws Exception {
                userRegisterTestMock.setEmail("estoNoEsUnEmail");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupRepeatEmailTest() throws Exception {

                String userJson = gson.toJson(userRegisterTest);
                userEntityRepository.save(userTest);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void signupBadPasswordTest() throws Exception {
                userRegisterTestMock.setPassword("123");
                String userJson = gson.toJson(userRegisterTestMock);

                ResultActions result = mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        void loginTest() throws Exception {
                LogInRequest logInRequest = new LogInRequest("prueba300@gmail.com", "123aA?45");
                String userJson = gson.toJson(logInRequest);
                userEntityRepository.save(userTest);

                ResultActions result = mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson));

                result.andExpect(status().isOk());
        }

        @Test
        @Transactional(propagation = Propagation.NOT_SUPPORTED)
        void updatePasswordTest() throws Exception {
                PasswordDto passwordDto = new PasswordDto("123aA?45", "123aA?456");
                String passwordDtoJson = gson.toJson(passwordDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                UserSecurity userSecurity = UserSecurity.builder()
                        .id(userTest.getId())
                        .email(userTest.getEmail())
                        .role(userTest.getRole())
                        .username(userTest.getUsername())
                        .password(userTest.getPassword())
                        .build();

                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userSecurity,
                                null, userSecurity.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updatePassword", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(passwordDtoJson));

                UserEntity userTestChangued = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                userEntityRepository.deleteById(id);
                assertNotEquals(userTest.getPassword(), userTestChangued.getPassword());
                result.andExpect(status().isOk());
        }

        @Test
        void updatePasswordWithoutRolTest() throws Exception {

                PasswordDto passwordDto = new PasswordDto("123aA?45", "1234");
                String passwordDtoJson = gson.toJson(passwordDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updatePassword", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(passwordDtoJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        @WithMockUser(roles = { "USER" })
        void updatePasswordUserRolTest() throws Exception {

                PasswordDto passwordDto = new PasswordDto("123aA?45", "1234");
                String passwordDtoJson = gson.toJson(passwordDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updatePassword", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(passwordDtoJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        @WithMockUser(roles = { "ADMIN" })
        void updatePasswordAdminRolTest() throws Exception {

                PasswordDto passwordDto = new PasswordDto("123aA?45", "1234");
                String passwordDtoJson = gson.toJson(passwordDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updatePassword", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(passwordDtoJson));

                result.andExpect(status().isInternalServerError());
        }

        @Test
        @WithMockUser(roles = { "ADMIN" })
        void updateRolAdminRolTest() throws Exception {

                RolDto rolDto = new RolDto("ADMIN");
                String rolDtoJson = gson.toJson(rolDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updateRol", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(rolDtoJson));

                UserEntity userTestChangued = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                assertEquals(RolEnum.ADMIN, userTestChangued.getRole());
                result.andExpect(status().isOk());
        }

        @Test
        @WithMockUser(roles = { "USER" })
        void updateRolUserRolTest() throws Exception {

                RolDto rolDto = new RolDto("USER");
                String rolDtoJson = gson.toJson(rolDto);

                userEntityRepository.save(userTest);
                userTest = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                UUID id = userTest.getId();

                ResultActions result = mockMvc.perform(patch("/auth/{id}/updateRol", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(rolDtoJson));

                UserEntity userTestChangued = userEntityRepository.findByEmail("prueba300@gmail.com").get();
                assertNotEquals(RolEnum.ADMIN, userTestChangued.getRole());
                result.andExpect(status().isForbidden());
        }
}
