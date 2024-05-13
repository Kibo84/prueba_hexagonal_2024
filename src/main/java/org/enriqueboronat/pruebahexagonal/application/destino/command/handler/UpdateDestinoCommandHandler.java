package org.enriqueboronat.pruebahexagonal.application.destino.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.destino.command.UpdateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.mapper.DestinoCommandMapper;
import org.enriqueboronat.pruebahexagonal.application.destino.service.DestinoService;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateDestinoCommandHandler implements CommandHandler<UpdateDestinoCommand, Destino> {
    private final DestinoService destinoService;
    private final DestinoCommandMapper destinoCommandMapper;

    @Override
    public Either<Error, Destino> handle(UpdateDestinoCommand command) {
        Optional<Destino> destino = destinoService.updateDestino(destinoCommandMapper.toDestino(command));
        return destino.map(Either::<Error, Destino>right)
            .orElseGet(() -> Either.left(new Error("Error al crear el destino")));
    }
}
