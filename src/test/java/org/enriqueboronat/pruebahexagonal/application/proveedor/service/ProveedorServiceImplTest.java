package org.enriqueboronat.pruebahexagonal.application.proveedor.service;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProveedorServiceImplTest {

    private ProveedorRepository proveedorRepository;
    private ProveedorServiceImpl proveedorService;

    @BeforeEach
    public void setUp() {
        proveedorRepository = Mockito.mock(ProveedorRepository.class);
        proveedorService = new ProveedorServiceImpl(proveedorRepository);
    }

    @Test
    public void testSearchProveedorByCodeSuccess() {
        UUID id = UUID.randomUUID();
        Proveedor proveedor = Proveedor.builder().build();
        when(proveedorRepository.searchProveedorById(any())).thenReturn(Optional.of(proveedor));

        Optional<Proveedor> result = proveedorService.searchProveedor(id);

        assertTrue(result.isPresent());
        assertEquals(proveedor, result.get());
    }

    @Test
    public void testSearchProveedorByCodeEmptyOptional() {
        UUID id = UUID.randomUUID();
        when(proveedorRepository.searchProveedorById(any())).thenReturn(Optional.empty());

        Optional<Proveedor> result = proveedorService.searchProveedor(id);

        assertTrue(result.isEmpty());
    }
}