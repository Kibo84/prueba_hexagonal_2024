package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ProveedorRequest {
    UUID id;
    String codigoProveedor;
    String nombre;
}
