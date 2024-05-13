package org.enriqueboronat.pruebahexagonal.application.ean.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.application.ean.service.EanService;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchEanInfoCommandHandler implements CommandHandler<SearchEanInfoCommand, Ean> {
    private final EanService eanService;

    @Override
    public Either<Error, Ean> handle(SearchEanInfoCommand command) {
        var ean = eanService.searchEanInfo(command.getEan());
        return ean.<Either<Error, Ean>>map(Either::right)
            .orElseGet(() -> Either.left(new Error("EAN not found")));
    }
}