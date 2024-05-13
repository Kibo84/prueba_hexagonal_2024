package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.mapper;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class ProveedorEntityMapper {
    public ProveedorEntity mapToEntity(Proveedor proveedor) {
        return ProveedorEntity.builder()
            .id(proveedor.getId())
            .codigoProveedor(proveedor.getCodigoProveedor().getValue())
            .nombre(proveedor.getNombre().getValue())
            .build();
    }

    public Proveedor mapToDomain(ProveedorEntity proveedorEntity) {
        return Proveedor.builder()
            .id(proveedorEntity.getId())
            .codigoProveedor(CodigoProveedorVO.of(proveedorEntity.getCodigoProveedor()))
            .nombre(NombreProveedorVO.of(proveedorEntity.getNombre()))
            .build();
    }
}
