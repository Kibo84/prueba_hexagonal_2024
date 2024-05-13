package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoEntityTest {

    @Test
    void testProductoEntity() {
        UUID id = UUID.randomUUID();
        String codigoProducto = "testCodigo";
        String nombre = "testNombre";

        ProductoEntity productoEntity = ProductoEntity.builder()
                .id(id)
                .codigoProducto(codigoProducto)
                .nombre(nombre)
                .build();

        assertEquals(id, productoEntity.getId());
        assertEquals(codigoProducto, productoEntity.getCodigoProducto());
        assertEquals(nombre, productoEntity.getNombre());

        String newNombre = "newTestNombre";
        productoEntity.setNombre(newNombre);

        assertEquals(newNombre, productoEntity.getNombre());
    }
}