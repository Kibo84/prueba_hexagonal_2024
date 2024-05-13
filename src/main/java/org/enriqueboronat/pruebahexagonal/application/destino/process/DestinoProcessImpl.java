package org.enriqueboronat.pruebahexagonal.application.destino.process;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.application.destino.command.*;
import org.enriqueboronat.pruebahexagonal.application.destino.command.handler.*;
import org.enriqueboronat.pruebahexagonal.application.destino.command.validator.*;
import org.enriqueboronat.pruebahexagonal.application.destino.service.DestinoService;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class DestinoProcessImpl implements DestinoProcess {
    private final CreateDestinoCommandHandler createDestinoCommandHandler;
    private final UpdateDestinoCommandHandler updateDestinoCommandHandler;
    private final DeleteDestinoCommandHandler deleteDestinoCommandHandler;
    private final SearchDestinoCommandHandler searchDestinoByIdCommandHandler;
    private final CreateDestinoCommandValidator createDestinoCommandValidator;
    private final UpdateDestinoCommandValidator updateDestinoCommandValidator;
    private final DeleteDestinoCommandValidator deleteDestinoCommandValidator;
    private final SearchDestinoCommandValidator searchDestinoByIdCommandValidator;
    private final DestinoService destinoService;

    private static final String DESTINO_CREATING = "Creating Destino: {}";
    private static final String DESTINO_UPDATING = "Updating Destino: {}";
    private static final String DESTINO_DELETING = "Deleting Destino: {}";
    private static final String DESTINO_SEARCHING = "Searching Destino by id: {}";

    @Override
    public List<Destino> searchDestinos() {
        return destinoService.searchDestinos();
    }

    @Override
    public Either<Error, Destino> createDestino(CreateDestinoCommand command) {
        log.info(DESTINO_CREATING, command.getId());
        return createDestinoCommandValidator.validate(command)
                .flatMap(createDestinoCommandHandler::handle);
    }

    @Override
    public Either<Error, Destino> updateDestino(UpdateDestinoCommand command) {
        log.info(DESTINO_UPDATING, command.getId());
        return updateDestinoCommandValidator.validate(command)
                .flatMap(updateDestinoCommandHandler::handle);
    }

    @Override
    public Either<Error, Void> deleteDestino(DeleteDestinoCommand command) {
        log.info(DESTINO_DELETING, command.getId());
        return deleteDestinoCommandValidator.validate(command)
                .flatMap(deleteDestinoCommandHandler::handle);
    }

    @Override
    public Either<Error, Destino> searchDestinoById(SearchDestinoCommand command) {
        log.info(DESTINO_SEARCHING, command.getId());
        return searchDestinoByIdCommandValidator.validate(command)
                .flatMap(searchDestinoByIdCommandHandler::handle);
    }
}
