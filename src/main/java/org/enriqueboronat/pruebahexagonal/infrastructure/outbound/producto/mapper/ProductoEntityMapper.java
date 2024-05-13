package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.mapper;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoEntityMapper {
    public ProductoEntity mapToEntity(Producto producto) {
        return ProductoEntity.builder()
            .id(producto.getId())
            .codigoProducto(producto.getCodigoProducto().getValue())
            .nombre(producto.getNombre().getValue())
            .build();
    }

    public Producto mapToDomain(ProductoEntity productoEntity) {
        return Producto.builder()
            .id(productoEntity.getId())
            .codigoProducto(CodigoProductoVO.of(productoEntity.getCodigoProducto()))
            .nombre(NombreProductoVO.of(productoEntity.getNombre()))
            .build();
    }
}
