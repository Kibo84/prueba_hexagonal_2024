package org.enriqueboronat.pruebahexagonal.application.producto.command.mapper;

import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.UpdateProductoCommand;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;
import org.springframework.stereotype.Component;

@Component
public class ProductoCommandMapper {

    public Producto toProducto(CreateProductoCommand command) {
        return Producto.builder()
            .id(command.getId())
            .nombre(NombreProductoVO.of(command.getNombre()))
            .codigoProducto(CodigoProductoVO.of(command.getCodigoProducto()))
            .build();
    }

    public Producto toProducto(UpdateProductoCommand command) {
        return Producto.builder()
            .id(command.getId())
            .nombre(NombreProductoVO.of(command.getNombre()))
            .codigoProducto(CodigoProductoVO.of(command.getCodigoProducto()))
            .build();
    }
}
