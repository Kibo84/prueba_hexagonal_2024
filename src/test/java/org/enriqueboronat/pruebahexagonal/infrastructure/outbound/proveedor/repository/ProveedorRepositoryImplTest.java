package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.repository;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.mapper.ProveedorEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProveedorRepositoryImplTest {

    @Mock
    private ProveedorRepositoryJpa proveedorRepositoryJpa;

    @Mock
    private ProveedorEntityMapper proveedorEntityMapper;

    @InjectMocks
    private ProveedorRepositoryImpl proveedorRepository;

    @Test
    void searchProveedorByCode() {
        String codigo = "0000001";
        UUID id = UUID.randomUUID();
        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .id(id)
                .codigoProveedor(codigo)
                .nombre("testNombre")
                .build();

        Proveedor proveedor = Proveedor.builder()
                .id(proveedorEntity.getId())
                .codigoProveedor(CodigoProveedorVO.of(proveedorEntity.getCodigoProveedor()))
                .nombre(NombreProveedorVO.of(proveedorEntity.getNombre()))
                .build();

        when(proveedorRepositoryJpa.findById(id)).thenReturn(Optional.of(proveedorEntity));
        when(proveedorEntityMapper.mapToDomain(proveedorEntity)).thenReturn(proveedor);

        Optional<Proveedor> result = proveedorRepository.searchProveedorById(id);

        assertTrue(result.isPresent());
        assertEquals(proveedor, result.get());

        verify(proveedorRepositoryJpa, times(1)).findById(id);
        verify(proveedorEntityMapper, times(1)).mapToDomain(proveedorEntity);
    }
}