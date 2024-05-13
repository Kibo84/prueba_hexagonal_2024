package org.enriqueboronat.pruebahexagonal.application.ean.process;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.application.ean.command.handler.SearchEanInfoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.ean.command.validator.SearchEanInfoCommandValidator;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EanProcessImplTest {

    private SearchEanInfoCommandValidator validator;
    private SearchEanInfoCommandHandler handler;
    private EanProcessImpl eanProcess;

    @BeforeEach
    public void setUp() {
        validator = Mockito.mock(SearchEanInfoCommandValidator.class);
        handler = Mockito.mock(SearchEanInfoCommandHandler.class);
        eanProcess = new EanProcessImpl(validator, handler);
    }

    @Test
    public void testSearchEanInfo_Success() {
        String eanCode = "testEan";
        Ean ean = new Ean();
        when(validator.validate(any(SearchEanInfoCommand.class))).thenReturn(
            Either.right(new SearchEanInfoCommand(eanCode))
        );
        when(handler.handle(any(SearchEanInfoCommand.class))).thenReturn(Either.right(ean));

        Either<Error, Ean> result = eanProcess.searchEanInfo(new SearchEanInfoCommand(eanCode));

        assertEquals(Either.right(ean), result);
    }

    @Test
    public void testSearchEanInfoValidationError() {
        String eanCode = "testEan";
        when(validator.validate(any(SearchEanInfoCommand.class))).thenReturn(
            Either.left(new Error("Validation error"))
        );

        Either<Error, Ean> result = eanProcess.searchEanInfo(new SearchEanInfoCommand(eanCode));

        assertEquals("Validation error", result.getLeft().getMessage());
    }

    @Test
    public void testSearchEanInfoHandlerError() {
        String eanCode = "testEan";
        when(validator.validate(any(SearchEanInfoCommand.class))).thenReturn(
            Either.right(new SearchEanInfoCommand(eanCode)));
        when(handler.handle(any(SearchEanInfoCommand.class))).thenReturn(Either.left(new Error("Handler error"))
        );

        Either<Error, Ean> result = eanProcess.searchEanInfo(new SearchEanInfoCommand(eanCode));

        assertEquals("Handler error", result.getLeft().getMessage());
    }
}