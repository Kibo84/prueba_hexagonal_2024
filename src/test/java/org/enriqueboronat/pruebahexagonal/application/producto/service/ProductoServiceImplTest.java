package org.enriqueboronat.pruebahexagonal.application.producto.service;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductoServiceImplTest {

    private ProductoRepository productoRepository;
    private ProductoServiceImpl productoService;

    @BeforeEach
    public void setUp() {
        productoRepository = Mockito.mock(ProductoRepository.class);
        productoService = new ProductoServiceImpl(productoRepository);
    }

    @Test
    public void testSearchProductoByCodeSuccess() {
        UUID id = UUID.randomUUID();
        Producto producto = Producto.builder().build();
        when(productoRepository.searchProductoById(any())).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoService.searchProducto(id);

        assertTrue(result.isPresent());
        assertEquals(producto, result.get());
    }

    @Test
    public void testSearchProductoByCodeEmptyOptional() {
        UUID id = UUID.randomUUID();
        when(productoRepository.searchProductoById(any())).thenReturn(Optional.empty());

        Optional<Producto> result = productoService.searchProducto(id);

        assertTrue(result.isEmpty());
    }
}