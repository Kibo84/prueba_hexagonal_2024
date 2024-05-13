package org.enriqueboronat.pruebahexagonal.application.ean.service;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.repository.EanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class EanServiceImplTest {

    private EanRepository eanRepository;
    private EanServiceImpl eanService;

    @BeforeEach
    public void setUp() {
        eanRepository = Mockito.mock(EanRepository.class);
        eanService = new EanServiceImpl(eanRepository);
    }

    @Test
    public void testSearchEanInfoSuccess() {
        String eanCode = "1234567890123";
        ProveedorVO proveedor = ProveedorVO.builder().build();
        ProductoVO producto = ProductoVO.builder().build();
        DestinoVO destino = DestinoVO.builder().build();
        when(eanRepository.searchProveedorVOByCode(anyString())).thenReturn(Optional.of(proveedor));
        when(eanRepository.searchProductoVOByCode(anyString())).thenReturn(Optional.of(producto));
        when(eanRepository.searchDestinoVOByCode(anyString())).thenReturn(Optional.of(destino));

        Optional<Ean> result = eanService.searchEanInfo(eanCode);

        assertTrue(result.isPresent());
        assertEquals(EanCodigoVO.of(eanCode), result.get().getEanCodigo());
        assertEquals(proveedor, result.get().getProveedor());
        assertEquals(producto, result.get().getProducto());
        assertEquals(destino, result.get().getDestino());
    }

    @Test
    public void testSearchEanInfoEmptyOptional() {
        String eanCode = "1234567890123";
        when(eanRepository.searchProveedorVOByCode(anyString())).thenReturn(Optional.empty());

        Optional<Ean> result = eanService.searchEanInfo(eanCode);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchEanInfoSubstring() {
        String eanCode = "1234567890123";
        String proveedorCode = eanCode.substring(0, 7);
        String productoCode = eanCode.substring(7, 12);
        String destinoCode = eanCode.substring(12, 13);

        assertEquals("1234567", proveedorCode);
        assertEquals("89012", productoCode);
        assertEquals("3", destinoCode);
    }
}