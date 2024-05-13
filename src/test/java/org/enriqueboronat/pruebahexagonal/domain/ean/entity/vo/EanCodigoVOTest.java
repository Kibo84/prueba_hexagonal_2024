package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EanCodigoVOTest {
    @Test
    void testOfWithValidInput() {
        EanCodigoVO eanCodigo = EanCodigoVO.of("1234567890123");
        assertNotNull(eanCodigo);
        assertEquals("1234567890123", eanCodigo.getCodigo());
    }

    @Test
    void testOfWithInvalidInputWithIncorrectLength() {
        EanCodigoVO eanCodigo = EanCodigoVO.of("123456789012");
        assertNull(eanCodigo);
    }

    @Test
    void testOfWithInvalidInputWithIncorrectCharacters() {
        EanCodigoVO eanCodigo = EanCodigoVO.of("1234567890PPP");
        assertNull(eanCodigo);
    }

    @Test
    void testValueAnnotation() {
        EanCodigoVO eanCodigo1 = EanCodigoVO.of("1234567890123");
        EanCodigoVO eanCodigo2 = EanCodigoVO.of("1234567890123");

        assertEquals(eanCodigo1, eanCodigo2);
        assertEquals(eanCodigo1.hashCode(), eanCodigo2.hashCode());

        assertTrue(eanCodigo1.equals(eanCodigo2));

        String expectedToString = "EanCodigoVO(codigo=1234567890123)";
        assertEquals(expectedToString, eanCodigo1.toString());
    }
}