package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.mapper;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.dto.EanInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EanDtoMapperTest {

    @Test
    public void testMapToDto() {
        Ean ean = mock(Ean.class);
        EanCodigoVO eanCodigo = EanCodigoVO.of("1234567890123");
        when(ean.getEanCodigo()).thenReturn(eanCodigo);
        when(ean.getProducto()).thenReturn(ProductoVO.builder().nombre("testProducto").build());
        when(ean.getProveedor()).thenReturn(ProveedorVO.builder().nombre("testProveedor").build());
        when(ean.getDestino()).thenReturn(DestinoVO.builder().descripcion("testDestino").build());

        EanDtoMapper eanDtoMapper = new EanDtoMapper();
        EanInfoDto eanInfoDto = eanDtoMapper.mapToDto(ean);

        assertEquals("1234567890123", eanInfoDto.getEan());
        assertEquals("testProducto", eanInfoDto.getProducto());
        assertEquals("testProveedor", eanInfoDto.getProveedor());
        assertEquals("testDestino", eanInfoDto.getDestino());
    }
}