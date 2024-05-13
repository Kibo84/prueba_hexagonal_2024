package org.enriqueboronat.pruebahexagonal.application.destino.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.destino.command.SearchDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.service.DestinoService;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchDestinoCommandHandler implements CommandHandler<SearchDestinoCommand, Destino> {
    private final DestinoService destinoService;

    @Override
    public Either<Error, Destino> handle(SearchDestinoCommand command) {
        var destino = destinoService.searchDestino(command.getId());
        return destino.<Either<Error, Destino>>map(Either::right)
            .orElseGet(() -> Either.left(new Error("Destino no encontrado")));
    }
}
