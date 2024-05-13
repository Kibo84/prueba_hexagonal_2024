package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class DestinoVO {
    private final static String SINGLE_DIGIT_EXCEPT_SEVEN_REGEX = "[0-689]";

    String descripcion;
    String codigo;

    public static DestinoVO of(String descripcion, String codigo) {
        if (Objects.isNull(codigo) || !codigo.matches(SINGLE_DIGIT_EXCEPT_SEVEN_REGEX)) {
            return null;
        }

        if (Objects.isNull(descripcion)) {
            return null;
        }
        return DestinoVO.builder().descripcion(descripcion).codigo(codigo).build();
    }
}
