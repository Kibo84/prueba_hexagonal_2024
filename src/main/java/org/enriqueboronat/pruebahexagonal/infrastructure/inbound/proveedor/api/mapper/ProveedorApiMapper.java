package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.mapper;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.entity.ProveedorDto;
import org.springframework.stereotype.Component;

@Component
public class ProveedorApiMapper {
    public ProveedorDto mapToDto(Proveedor proveedor) {
        return ProveedorDto.builder()
                .id(proveedor.getId())
                .codigoProveedor(proveedor.getCodigoProveedor().getValue())
                .nombre(proveedor.getNombre().getValue())
                .build();
    }
}
