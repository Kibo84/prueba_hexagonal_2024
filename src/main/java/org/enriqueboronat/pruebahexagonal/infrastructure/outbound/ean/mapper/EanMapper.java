package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.mapper;

import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class EanMapper {
    public DestinoVO mapToDestinoVO(DestinoEntity destinoEntity) {
        log.debug("Mapping destino entity to destino VO: {}", destinoEntity.getId());
        return DestinoVO.builder()
            .codigo(destinoEntity.getCodigo())
            .descripcion(destinoEntity.getDescripcion())
            .build();
    }

    public ProductoVO mapToProductoVO(ProductoEntity productoEntity) {
        log.debug("Mapping producto entity to producto VO: {}", productoEntity.getId());
        return ProductoVO.builder()
            .codigoProducto(productoEntity.getCodigoProducto())
            .nombre(productoEntity.getNombre())
            .build();
    }

    public ProveedorVO mapToProveedorVO(ProveedorEntity proveedorEntity) {
        log.debug("Mapping proveedor entity to proveedor VO: {}", proveedorEntity.getId());
        return ProveedorVO.builder()
            .codigoProveedor(proveedorEntity.getCodigoProveedor())
            .nombre(proveedorEntity.getNombre())
            .build();
    }
}
