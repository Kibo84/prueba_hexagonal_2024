package org.enriqueboronat.pruebahexagonal.config.security.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String address;

    private LocalDate birthday;

    private RolEnum role;
}
