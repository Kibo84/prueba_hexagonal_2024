package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.repository;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.repository.DestinoRepositoryJpa;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.mapper.EanMapper;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.repository.ProductoRepositoryJpa;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.repository.ProveedorRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EanRepositoryImplTest {

    @Mock
    private DestinoRepositoryJpa destinoRepositoryJpa;

    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Mock
    private ProveedorRepositoryJpa proveedorRepositoryJpa;

    @Mock
    private EanMapper eanMapper;

    @InjectMocks
    private EanRepositoryImpl eanRepository;

    @Test
    void searchDestinoVOByCode() {
        String codigo = "testCodigo";
        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setCodigo(codigo);
        destinoEntity.setDescripcion("testDescripcion");

        DestinoVO destinoVO = DestinoVO.builder()
            .codigo(codigo)
            .descripcion("testDescripcion")
            .build();

        when(destinoRepositoryJpa.findByCodigo(codigo)).thenReturn(Optional.of(destinoEntity));
        when(eanMapper.mapToDestinoVO(destinoEntity)).thenReturn(destinoVO);

        Optional<DestinoVO> result = eanRepository.searchDestinoVOByCode(codigo);

        assertTrue(result.isPresent());
        assertEquals(destinoVO, result.get());

        verify(destinoRepositoryJpa, times(1)).findByCodigo(codigo);
        verify(eanMapper, times(1)).mapToDestinoVO(destinoEntity);
    }

    @Test
    void searchProveedorVOByCode() {
        String codigo = "testCodigo";
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setCodigoProveedor(codigo);
        proveedorEntity.setNombre("testNombre");

        ProveedorVO proveedorVO = ProveedorVO.builder()
            .codigoProveedor(codigo)
            .nombre("testNombre")
            .build();

        when(proveedorRepositoryJpa.findByCodigoProveedor(codigo)).thenReturn(Optional.of(proveedorEntity));
        when(eanMapper.mapToProveedorVO(proveedorEntity)).thenReturn(proveedorVO);

        Optional<ProveedorVO> result = eanRepository.searchProveedorVOByCode(codigo);

        assertTrue(result.isPresent());
        assertEquals(proveedorVO, result.get());

        verify(proveedorRepositoryJpa, times(1)).findByCodigoProveedor(codigo);
        verify(eanMapper, times(1)).mapToProveedorVO(proveedorEntity);
    }

    @Test
    void searchProductoVOByCode() {
        String codigo = "testCodigo";
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setCodigoProducto(codigo);
        productoEntity.setNombre("testNombre");

        ProductoVO productoVO = ProductoVO.builder()
            .codigoProducto(codigo)
            .nombre("testNombre")
            .build();

        when(productoRepositoryJpa.findByCodigoProducto(codigo)).thenReturn(Optional.of(productoEntity));
        when(eanMapper.mapToProductoVO(productoEntity)).thenReturn(productoVO);

        Optional<ProductoVO> result = eanRepository.searchProductoVOByCode(codigo);

        assertTrue(result.isPresent());
        assertEquals(productoVO, result.get());

        verify(productoRepositoryJpa, times(1)).findByCodigoProducto(codigo);
        verify(eanMapper, times(1)).mapToProductoVO(productoEntity);
    }
}