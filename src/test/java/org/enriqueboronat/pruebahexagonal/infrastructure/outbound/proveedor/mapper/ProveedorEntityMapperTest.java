package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.mapper;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProveedorEntityMapperTest {

    private final ProveedorEntityMapper mapper = new ProveedorEntityMapper();

    @Test
    void mapToEntity() {
        UUID id = UUID.randomUUID();
        String codigoProveedor = "0000001";
        String nombre = "testNombre";

        Proveedor proveedor = Proveedor.builder()
                .id(id)
                .codigoProveedor(CodigoProveedorVO.of(codigoProveedor))
                .nombre(NombreProveedorVO.of(nombre))
                .build();

        ProveedorEntity proveedorEntity = mapper.mapToEntity(proveedor);

        assertEquals(id, proveedorEntity.getId());
        assertEquals(codigoProveedor, proveedorEntity.getCodigoProveedor());
        assertEquals(nombre, proveedorEntity.getNombre());
    }

    @Test
    void mapToDomain() {
        UUID id = UUID.randomUUID();
        String codigoProveedor = "0000001";
        String nombre = "testNombre";

        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .id(id)
                .codigoProveedor(codigoProveedor)
                .nombre(nombre)
                .build();

        Proveedor proveedor = mapper.mapToDomain(proveedorEntity);

        assertEquals(id, proveedor.getId());
        assertEquals(codigoProveedor, proveedor.getCodigoProveedor().getValue());
        assertEquals(nombre, proveedor.getNombre().getValue());
    }
}