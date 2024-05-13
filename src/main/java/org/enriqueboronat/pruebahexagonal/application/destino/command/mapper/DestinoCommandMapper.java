package org.enriqueboronat.pruebahexagonal.application.destino.command.mapper;

import org.enriqueboronat.pruebahexagonal.application.destino.command.CreateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.UpdateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import org.springframework.stereotype.Component;

@Component
public class DestinoCommandMapper {

    public Destino toDestino(CreateDestinoCommand command) {
        return Destino.builder()
                .id(command.getId())
                .codigo(CodigoDestinoVO.of(command.getCodigo()))
                .descripcion(command.getDescripcion())
                .build();
    }

    public Destino toDestino(UpdateDestinoCommand command) {
        return Destino.builder()
                .id(command.getId())
                .codigo(CodigoDestinoVO.of(command.getCodigo()))
                .descripcion(command.getDescripcion())
                .build();
    }
}
