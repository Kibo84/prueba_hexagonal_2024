package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity;

import lombok.Builder;
import lombok.Getter;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Builder
@Getter
public class Proveedor {
    private UUID id;
    private CodigoProveedorVO codigoProveedor;
    private NombreProveedorVO nombre;

    public static Optional<Proveedor> of(
            UUID id,
            CodigoProveedorVO codigo,
            NombreProveedorVO nombre
    ) {
        if (Objects.isNull(id)
            || Objects.isNull(nombre)
            || Objects.isNull(codigo)
        ) {
            return Optional.empty();
        }

        return Optional.of(
            Proveedor.builder()
                .id(id)
                .nombre(nombre)
                .codigoProveedor(codigo)
                .build()
        );
    }
}
