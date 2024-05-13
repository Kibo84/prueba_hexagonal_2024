package org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodigoDestinoVOTest {
    private static final String VALID_VALUE = "3";
    private static final String INVALID_VALUE = "-1";

    @Test
    void testValueAnnotation() {
        CodigoDestinoVO codigo1 = CodigoDestinoVO.of(VALID_VALUE);
        CodigoDestinoVO codigo2 = CodigoDestinoVO.of(VALID_VALUE);

        assertEquals(codigo1, codigo2);
        assertEquals(codigo1.hashCode(), codigo2.hashCode());

        assertTrue(codigo1.equals(codigo2));

        String expectedToString = "CodigoDestinoVO(value=" + VALID_VALUE + ")";
        assertEquals(expectedToString, codigo1.toString());
    }

    @Test
    void testInvalidValue() {
        assertNull(CodigoDestinoVO.of(null));
        assertNull(CodigoDestinoVO.of(INVALID_VALUE));
    }
}