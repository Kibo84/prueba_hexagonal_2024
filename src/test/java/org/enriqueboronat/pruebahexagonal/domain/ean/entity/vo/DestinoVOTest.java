package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinoVOTest {
    private final String DESTINO_TEST = "Destino Test";
    private final String CODIGO_TEST = "3";

    @Test
    void testOfWithValidInput() {
        DestinoVO destino = DestinoVO.of(DESTINO_TEST, CODIGO_TEST);
        assertNotNull(destino);
        assertEquals(DESTINO_TEST, destino.getDescripcion());
        assertEquals(CODIGO_TEST, destino.getCodigo());
    }

    @Test
    void testOfWithInvalidInputForLength() {
        String CODIGO_TEST_ERROR_MAYOR_9 = "10";

        DestinoVO destino = DestinoVO.of(DESTINO_TEST, CODIGO_TEST_ERROR_MAYOR_9);
        assertNull(destino);
    }

    @Test
    void testOfWithInvalidInputForCodeSeven() {
        String CODIGO_TEST_ERROR_VALOR_7 = "7";

        DestinoVO destino = DestinoVO.of(DESTINO_TEST, CODIGO_TEST_ERROR_VALOR_7);
        assertNull(destino);
    }

    @Test
    void testOfWithInvalidInputForNegativeInt() {
        String CODIGO_TEST_ERROR_NEGATIVE = "-3";

        DestinoVO destino = DestinoVO.of(DESTINO_TEST, CODIGO_TEST_ERROR_NEGATIVE);
        assertNull(destino);
    }

    @Test
    void testOfWithInvalidInputForNullDescription() {
        DestinoVO destino = DestinoVO.of(null, CODIGO_TEST);
        assertNull(destino);
    }

    @Test
    void testOfWithInvalidInputForNullCode() {
        DestinoVO destino = DestinoVO.of(DESTINO_TEST, null);
        assertNull(destino);
    }

    @Test
    void testValueAnnotation() {
        DestinoVO destino1 = DestinoVO.of("Destino Test", "3");
        DestinoVO destino2 = DestinoVO.of("Destino Test", "3");

        assertEquals(destino1, destino2);
        assertEquals(destino1.hashCode(), destino2.hashCode());

        assertTrue(destino1.equals(destino2));

        String expectedToString = "DestinoVO(descripcion=Destino Test, codigo=3)";
        assertEquals(expectedToString, destino1.toString());
    }
}