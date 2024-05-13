package org.enriqueboronat.pruebahexagonal.application.ean.process;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.application.ean.command.handler.SearchEanInfoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.ean.command.validator.SearchEanInfoCommandValidator;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class EanProcessImpl implements EanProcess {
    private final SearchEanInfoCommandValidator searchEanInfoCommandValidator;
    private final SearchEanInfoCommandHandler eanCommandHandler;

    private static final String EAN_INFO_SEARCHING = "Searching EAN info for EAN: {}";

    @Override
    public Either<Error, Ean> searchEanInfo(SearchEanInfoCommand command) {
        log.info(EAN_INFO_SEARCHING, command.getEan());
        return searchEanInfoCommandValidator.validate(command)
                .flatMap(eanCommandHandler::handle);
    }
}
