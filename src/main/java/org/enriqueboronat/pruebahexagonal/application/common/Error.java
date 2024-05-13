package org.enriqueboronat.pruebahexagonal.application.common;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Error {
    String message;
    String code;
}
