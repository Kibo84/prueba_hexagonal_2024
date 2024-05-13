package org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Builder
@Value
public class CodigoDestinoVO {
    private final static String SINGLE_DIGIT_EXCEPT_SEVEN_REGEX = "[0-689]";

    String value;

    public static CodigoDestinoVO of(String value) {
        if (Objects.isNull(value) || !value.matches(SINGLE_DIGIT_EXCEPT_SEVEN_REGEX)) {
            return null;
        }

        return CodigoDestinoVO.builder().value(value).build();
    }
}