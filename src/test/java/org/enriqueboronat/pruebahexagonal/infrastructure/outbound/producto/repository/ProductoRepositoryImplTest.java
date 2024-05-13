package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.repository;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.mapper.ProductoEntityMapper;
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
class ProductoRepositoryImplTest {

    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Mock
    private ProductoEntityMapper productoEntityMapper;

    @InjectMocks
    private ProductoRepositoryImpl productoRepository;

    @Test
    void searchProductoByCode() {
        String codigo = "0000001";
        UUID id = UUID.randomUUID();
        ProductoEntity productoEntity = ProductoEntity.builder()
                .id(id)
                .codigoProducto(codigo)
                .nombre("testNombre")
                .build();

        Producto producto = Producto.builder()
                .id(productoEntity.getId())
                .codigoProducto(CodigoProductoVO.of(productoEntity.getCodigoProducto()))
                .nombre(NombreProductoVO.of(productoEntity.getNombre()))
                .build();

        when(productoRepositoryJpa.findById(id)).thenReturn(Optional.of(productoEntity));
        when(productoEntityMapper.mapToDomain(productoEntity)).thenReturn(producto);

        Optional<Producto> result = productoRepository.searchProductoById(id);

        assertTrue(result.isPresent());
        assertEquals(producto, result.get());

        verify(productoRepositoryJpa, times(1)).findById(id);
        verify(productoEntityMapper, times(1)).mapToDomain(productoEntity);
    }
}