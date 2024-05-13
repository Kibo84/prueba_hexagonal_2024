package org.enriqueboronat.pruebahexagonal.common;

import org.enriqueboronat.pruebahexagonal.application.common.Error;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {

    @Test
    public void testErrorBuilder() {
        String message = "testMessage";
        String code = "testCode";

        Error error = Error.builder()
                .message(message)
                .code(code)
                .build();

        assertEquals(message, error.getMessage());
        assertEquals(code, error.getCode());
    }
}