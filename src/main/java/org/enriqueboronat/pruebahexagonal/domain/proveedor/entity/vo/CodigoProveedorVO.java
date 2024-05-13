package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class CodigoProveedorVO {
    private final static String CODIGO_PROVEEDOR_REGEX = "[0-9]+";
    private final static int CODIGO_PROVEEDOR_LENGTH = 7;

    String value;

    public static CodigoProveedorVO of(String value) {
        if (Objects.isNull(value)
            || value.length() != CODIGO_PROVEEDOR_LENGTH
            || !value.matches(CODIGO_PROVEEDOR_REGEX)
        ) {
            return null;
        }

        return CodigoProveedorVO.builder().value(value).build();
    }
}
