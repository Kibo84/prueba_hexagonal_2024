package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class EanCodigoVO {
    private final static int CODIGO_LENGTH = 13;
    private final static String CODIGO_REGEX = "[0-9]+";

    String codigo;

    public static EanCodigoVO of(String codigo) {
        if (Objects.isNull(codigo)
                || codigo.length() != CODIGO_LENGTH
                || !codigo.matches(CODIGO_REGEX)
        ) {
            return null;
        }
        return EanCodigoVO.builder().codigo(codigo).build();
    }
}
