package org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class NombreProductoVO {
    String value;

    public static NombreProductoVO of(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            return null;
        }

        return NombreProductoVO.builder().value(value).build();
    }
}
