package org.enriqueboronat.pruebahexagonal.config.security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.enriqueboronat.pruebahexagonal.config.security.util.ValidPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    private String oldPassword;

    @NonNull
    @NotBlank
    @ValidPassword
    private String password;
}
