package org.enriqueboronat.pruebahexagonal.domain.producto.entity;

import lombok.Builder;
import lombok.Getter;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Builder
@Getter
public class Producto {
    private UUID id;
    private CodigoProductoVO codigoProducto;
    private NombreProductoVO nombre;

    public static Optional<Producto> of(
            UUID id,
            CodigoProductoVO codigoProducto,
            NombreProductoVO nombre
    ) {
        if (Objects.isNull(id)
            || Objects.isNull(codigoProducto)
            || Objects.isNull(nombre)
        ) {
            return Optional.empty();
        }

        return Optional.of(
            Producto.builder()
                .id(id)
                .codigoProducto(codigoProducto)
                .nombre(nombre)
                .build()
        );
    }
}
