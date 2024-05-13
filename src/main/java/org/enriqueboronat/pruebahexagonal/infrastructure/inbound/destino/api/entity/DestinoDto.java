package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class DestinoDto {
    UUID id;
    String codigo;
    String descripcion;
}
