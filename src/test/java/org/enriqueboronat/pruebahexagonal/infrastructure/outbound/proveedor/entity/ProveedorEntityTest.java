package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProveedorEntityTest {

    @Test
    void testProveedorEntity() {
        UUID id = UUID.randomUUID();
        String codigoProveedor = "testCodigo";
        String nombre = "testNombre";

        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .id(id)
                .codigoProveedor(codigoProveedor)
                .nombre(nombre)
                .build();

        assertEquals(id, proveedorEntity.getId());
        assertEquals(codigoProveedor, proveedorEntity.getCodigoProveedor());
        assertEquals(nombre, proveedorEntity.getNombre());

        String newNombre = "newTestNombre";
        proveedorEntity.setNombre(newNombre);

        assertEquals(newNombre, proveedorEntity.getNombre());
    }
}