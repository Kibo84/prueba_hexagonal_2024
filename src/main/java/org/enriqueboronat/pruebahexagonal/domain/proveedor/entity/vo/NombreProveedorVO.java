package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class NombreProveedorVO {
    String value;

    public static NombreProveedorVO of(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            return null;
        }

        return NombreProveedorVO.builder().value(value).build();
    }
}