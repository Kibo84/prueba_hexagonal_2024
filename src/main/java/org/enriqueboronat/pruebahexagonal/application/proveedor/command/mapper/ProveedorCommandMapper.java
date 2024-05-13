package org.enriqueboronat.pruebahexagonal.application.proveedor.command.mapper;

import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.UpdateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;
import org.springframework.stereotype.Component;

@Component
public class ProveedorCommandMapper {

    public Proveedor toProveedor(CreateProveedorCommand command) {
        return Proveedor.builder()
            .id(command.getId())
            .nombre(NombreProveedorVO.of(command.getNombre()))
            .codigoProveedor(CodigoProveedorVO.of(command.getCodigoProveedor()))
            .build();
    }

    public Proveedor toProveedor(UpdateProveedorCommand command) {
        return Proveedor.builder()
            .id(command.getId())
            .nombre(NombreProveedorVO.of(command.getNombre()))
            .codigoProveedor(CodigoProveedorVO.of(command.getCodigoProveedor()))
            .build();
    }
}
