package org.enriqueboronat.pruebahexagonal.config.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {

    private String email;
    private String password;
    
}
