package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class ProveedorVO {
    private final static String CODIGO_PROVEEDOR_REGEX = "[0-9]+";
    private final static int CODIGO_PROVEEDOR_LENGTH = 7;

    String nombre;
    String codigoProveedor;

    public static ProveedorVO of(String nombre, String codigoProveedor) {
        if (Objects.isNull(codigoProveedor)
                || codigoProveedor.length() != CODIGO_PROVEEDOR_LENGTH
                || !codigoProveedor.matches(CODIGO_PROVEEDOR_REGEX)
        ) {
            return null;
        }

        if (Objects.isNull(nombre)) {
            return null;
        }
        return ProveedorVO.builder().nombre(nombre).codigoProveedor(codigoProveedor).build();
    }
}