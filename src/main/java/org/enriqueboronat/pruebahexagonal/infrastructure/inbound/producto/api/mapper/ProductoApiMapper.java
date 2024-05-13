package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.mapper;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.entity.ProductoDto;
import org.springframework.stereotype.Component;

@Component
public class ProductoApiMapper {
    public ProductoDto mapToDto(Producto producto) {
        return ProductoDto.builder()
                .id(producto.getId())
                .codigoProducto(producto.getCodigoProducto().getValue())
                .nombre(producto.getNombre().getValue())
                .build();
    }
}
