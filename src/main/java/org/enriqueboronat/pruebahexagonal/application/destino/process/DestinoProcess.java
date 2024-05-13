package org.enriqueboronat.pruebahexagonal.application.destino.process;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.destino.command.CreateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.DeleteDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.SearchDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.UpdateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;

import java.util.List;
import java.util.UUID;

public interface DestinoProcess {
    List<Destino> searchDestinos();
    Either<Error, Destino> createDestino(CreateDestinoCommand command);
    Either<Error, Destino> updateDestino(UpdateDestinoCommand command);
    Either<Error, Void> deleteDestino(DeleteDestinoCommand command);
    Either<Error, Destino> searchDestinoById(SearchDestinoCommand command);
}
