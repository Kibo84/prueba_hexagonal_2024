package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.mapper;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EanMapperTest {

    private final EanMapper mapper = new EanMapper();

    @Test
    void mapToDestinoVO() {
        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setCodigo("testCodigo");
        destinoEntity.setDescripcion("testDescripcion");

        DestinoVO destinoVO = mapper.mapToDestinoVO(destinoEntity);

        assertThat(destinoVO.getCodigo()).isEqualTo(destinoEntity.getCodigo());
        assertThat(destinoVO.getDescripcion()).isEqualTo(destinoEntity.getDescripcion());
    }

    @Test
    void mapToProductoVO() {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setCodigoProducto("testCodigoProducto");
        productoEntity.setNombre("testNombre");

        ProductoVO productoVO = mapper.mapToProductoVO(productoEntity);

        assertThat(productoVO.getCodigoProducto()).isEqualTo(productoEntity.getCodigoProducto());
        assertThat(productoVO.getNombre()).isEqualTo(productoEntity.getNombre());
    }

    @Test
    void mapToProveedorVO() {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setCodigoProveedor("testCodigoProveedor");
        proveedorEntity.setNombre("testNombre");

        ProveedorVO proveedorVO = mapper.mapToProveedorVO(proveedorEntity);

        assertThat(proveedorVO.getCodigoProveedor()).isEqualTo(proveedorEntity.getCodigoProveedor());
        assertThat(proveedorVO.getNombre()).isEqualTo(proveedorEntity.getNombre());
    }
}