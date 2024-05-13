package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.mapper;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.dto.EanInfoDto;
import org.springframework.stereotype.Component;

@Component
public class EanDtoMapper {
    public EanInfoDto mapToDto(Ean ean) {
        return EanInfoDto.builder()
            .ean(ean.getEanCodigo().getCodigo())
            .producto(ean.getProducto().getNombre())
            .proveedor(ean.getProveedor().getNombre())
            .destino(ean.getDestino().getDescripcion())
            .build();
    }
}
