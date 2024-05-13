package org.enriqueboronat.pruebahexagonal.application.ean.command.handler;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.application.ean.service.EanService;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SearchEanInfoCommandHandlerTest {

    private EanService eanService;
    private SearchEanInfoCommandHandler searchEanInfoCommandHandler;

    @BeforeEach
    public void setUp() {
        eanService = Mockito.mock(EanService.class);
        searchEanInfoCommandHandler = new SearchEanInfoCommandHandler(eanService);
    }

    @Test
    public void testHandleSuccess() {
        String eanCode = "testEan";
        Ean ean = Ean.builder().build();
        when(eanService.searchEanInfo(anyString())).thenReturn(Optional.of(ean));

        Either<Error, Ean> result = searchEanInfoCommandHandler.handle(
            SearchEanInfoCommand.builder().ean(eanCode).build()
        );

        assertTrue(result.isRight());
        assertEquals(Either.right(ean), result);
        Mockito.verify(eanService).searchEanInfo(eanCode);
    }

    @Test
    public void testHandleError() {
        String eanCode = "testEan";
        when(eanService.searchEanInfo(anyString())).thenReturn(Optional.empty());

        Either<Error, Ean> result = searchEanInfoCommandHandler.handle(
            SearchEanInfoCommand.builder().ean(eanCode).build()
        );

        assertTrue(result.isLeft());
        assertEquals("EAN not found", result.getLeft().getMessage());
        Mockito.verify(eanService).searchEanInfo(eanCode);
    }
}