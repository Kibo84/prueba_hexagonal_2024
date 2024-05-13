package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.mapper;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.springframework.stereotype.Component;

@Component
public class DestinoEntityMapper {
    public DestinoEntity mapToEntity(Destino destino) {
        return DestinoEntity.builder()
            .id(destino.getId())
            .codigo(destino.getCodigo().getValue())
            .descripcion(destino.getDescripcion())
            .build();
    }

    public Destino mapToDomain(DestinoEntity destinoEntity) {
        return Destino.builder()
            .id(destinoEntity.getId())
            .codigo(CodigoDestinoVO.of(destinoEntity.getCodigo()))
            .descripcion(destinoEntity.getDescripcion())
            .build();
    }
}
