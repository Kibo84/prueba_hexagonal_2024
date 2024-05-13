package org.enriqueboronat.pruebahexagonal.application.ean.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchEanInfoCommandTest {

    @Test
    public void testEanGetter() {
        String eanCode = "testEan";
        SearchEanInfoCommand command = SearchEanInfoCommand.builder().ean(eanCode).build();

        assertEquals(eanCode, command.getEan());
    }

    @Test
    public void testConstructor() {
        String eanCode = "testEan";
        SearchEanInfoCommand command = new SearchEanInfoCommand(eanCode);

        assertEquals(eanCode, command.getEan());
    }
}