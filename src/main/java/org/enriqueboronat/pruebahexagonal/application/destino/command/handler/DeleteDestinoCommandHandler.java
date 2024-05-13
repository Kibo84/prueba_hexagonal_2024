package org.enriqueboronat.pruebahexagonal.application.destino.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.destino.command.DeleteDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.service.DestinoService;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteDestinoCommandHandler implements CommandHandler<DeleteDestinoCommand, Void> {
    private final DestinoService destinoService;

    @Override
    public Either<Error, Void> handle(DeleteDestinoCommand command) {
        destinoService.deleteDestino(command.getId());
        return Either.right(null);
    }
}
