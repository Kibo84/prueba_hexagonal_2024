package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class ProductoVO {
    private final static int CODIGO_PRODUCTO_LENGTH = 5;
    private final static String CODIGO_PRODUCTO_REGEX = "[0-9]+";

    String nombre;
    String codigoProducto;

    public static ProductoVO of(String nombre, String codigoProducto) {
        if (Objects.isNull(codigoProducto)
                || codigoProducto.length() != CODIGO_PRODUCTO_LENGTH
                || !codigoProducto.matches(CODIGO_PRODUCTO_REGEX)
        ) {
            return null;
        }

        if (Objects.isNull(nombre)) {
            return null;
        }
        return ProductoVO.builder().nombre(nombre).codigoProducto(codigoProducto).build();
    }
}