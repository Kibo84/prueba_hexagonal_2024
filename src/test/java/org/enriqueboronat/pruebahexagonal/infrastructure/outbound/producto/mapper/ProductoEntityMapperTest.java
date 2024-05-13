package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.mapper;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoEntityMapperTest {

    private final ProductoEntityMapper mapper = new ProductoEntityMapper();

    @Test
    void mapToEntity() {
        UUID id = UUID.randomUUID();
        String codigoProducto = "00001";
        String nombre = "testNombre";

        Producto producto = Producto.builder()
                .id(id)
                .codigoProducto(CodigoProductoVO.of(codigoProducto))
                .nombre(NombreProductoVO.of(nombre))
                .build();

        ProductoEntity productoEntity = mapper.mapToEntity(producto);

        assertEquals(id, productoEntity.getId());
        assertEquals(codigoProducto, productoEntity.getCodigoProducto());
        assertEquals(nombre, productoEntity.getNombre());
    }

    @Test
    void mapToDomain() {
        UUID id = UUID.randomUUID();
        String codigoProducto = "00001";
        String nombre = "testNombre";

        ProductoEntity productoEntity = ProductoEntity.builder()
                .id(id)
                .codigoProducto(codigoProducto)
                .nombre(nombre)
                .build();

        Producto producto = mapper.mapToDomain(productoEntity);

        assertEquals(id, producto.getId());
        assertEquals(codigoProducto, producto.getCodigoProducto().getValue());
        assertEquals(nombre, producto.getNombre().getValue());
    }
}