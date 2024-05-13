package org.enriqueboronat.pruebahexagonal.config.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.security.util.ValidPassword;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @NonNull
    @NotEmpty
    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NonNull
    @NotBlank
    @Size(max = 30)
    private String lastname;

    @NonNull
    @NotBlank
    @Email
    private String email;

    @NonNull
    @NotBlank
    @ValidPassword
    private String password;

    private Boolean acceptedTerms;
}