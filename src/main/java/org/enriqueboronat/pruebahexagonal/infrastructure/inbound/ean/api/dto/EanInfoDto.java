package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class EanInfoDto {
    String ean;
    String producto;
    String proveedor;
    String destino;
}
