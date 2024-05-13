package org.enriqueboronat.pruebahexagonal.application.ean.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEanInfoCommandValidatorTest {

    private SearchEanInfoCommandValidator searchEanInfoCommandValidator;

    @BeforeEach
    public void setUp() {
        searchEanInfoCommandValidator = new SearchEanInfoCommandValidator();
    }

    @Test
    public void testValidateInvalidLength() {
        String eanCode = "1234567890";
        Either<Error, SearchEanInfoCommand> result = searchEanInfoCommandValidator.validate(new SearchEanInfoCommand(eanCode));

        assertTrue(result.isLeft());
        assertEquals("EAN must be 13 digits long", result.getLeft().getMessage());
    }

    @Test
    public void testValidateNonNumeric() {
        String eanCode = "123456789012a";
        Either<Error, SearchEanInfoCommand> result = searchEanInfoCommandValidator.validate(new SearchEanInfoCommand(eanCode));

        assertTrue(result.isLeft());
        assertEquals("EAN must be numeric", result.getLeft().getMessage());
    }

    @Test
    public void testValidateEndsWithSeven() {
        String eanCode = "1234567890127";
        Either<Error, SearchEanInfoCommand> result = searchEanInfoCommandValidator.validate(new SearchEanInfoCommand(eanCode));

        assertTrue(result.isLeft());
        assertEquals("EAN must not end with 7", result.getLeft().getMessage());
    }

    @Test
    public void testValidateMultipleErrors() {
        String eanCode = "12345678907";
        Either<Error, SearchEanInfoCommand> result = searchEanInfoCommandValidator.validate(new SearchEanInfoCommand(eanCode));

        assertTrue(result.isLeft());
        assertEquals("EAN must be 13 digits long, EAN must not end with 7", result.getLeft().getMessage());
    }

    @Test
    public void testValidateSuccess() {
        String eanCode = "1234567890123";
        Either<Error, SearchEanInfoCommand> result = searchEanInfoCommandValidator.validate(new SearchEanInfoCommand(eanCode));

        assertTrue(result.isRight());
        assertEquals(eanCode, result.get().getEan());
    }
}