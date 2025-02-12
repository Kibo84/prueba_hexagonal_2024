package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ProductoDto {
    UUID id;
    String codigoProducto;
    String nombre;
}
