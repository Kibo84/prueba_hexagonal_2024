package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.mapper;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.entity.DestinoDto;
import org.springframework.stereotype.Component;

@Component
public class DestinoApiMapper {
    public DestinoDto mapToDto(Destino destino) {
        return DestinoDto.builder()
                .id(destino.getId())
                .codigo(destino.getCodigo().getValue())
                .descripcion(destino.getDescripcion())
                .build();
    }
}
