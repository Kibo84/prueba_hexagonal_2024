package org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class CodigoProductoVO {
    private final static int CODIGO_PRODUCTO_LENGTH = 5;
    private final static String CODIGO_PRODUCTO_REGEX = "[0-9]+";

    String value;

    public static CodigoProductoVO of(String value) {
        if (Objects.isNull(value)
            || value.length() != CODIGO_PRODUCTO_LENGTH
            || !value.matches(CODIGO_PRODUCTO_REGEX)
        ) {
            return null;
        }

        return CodigoProductoVO.builder().value(value).build();
    }
}
