package org.enriqueboronat.pruebahexagonal.config.security.repositories.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;
    
    @NonNull
    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private RolEnum role;

    private String password;
    
    @NonNull
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String firstname;

    @NonNull
    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String lastname;

    private String address;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    private Boolean activityPermission;

    private Boolean acceptedTerms;
}