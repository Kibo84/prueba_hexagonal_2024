package org.enriqueboronat.pruebahexagonal.application.ean.process;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;

public interface EanProcess {
    Either<Error, Ean> searchEanInfo(SearchEanInfoCommand command);
}
