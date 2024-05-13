package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ProveedorDto {
    UUID id;
    String codigoProveedor;
    String nombre;
}
