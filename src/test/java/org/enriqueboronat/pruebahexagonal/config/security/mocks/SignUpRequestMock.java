package org.enriqueboronat.pruebahexagonal.config.security.mocks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestMock {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Boolean acceptedTerms;
}