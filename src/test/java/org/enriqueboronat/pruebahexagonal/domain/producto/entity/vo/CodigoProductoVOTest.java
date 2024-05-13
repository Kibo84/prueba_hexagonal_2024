package org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoProductoVOTest {
    private static final String VALID_CODIGO = "12345";
    private static final String INVALID_CODIGO = "asdfg";
    private static final String DIFFERENT_CODIGO = "56785";

    @Test
    void testOfWithCorrectParameter() {
        assertNotNull(CodigoProductoVO.of(VALID_CODIGO));
    }

    @Test
    void testOfWithIncorrectParameters() {
        assertNull(CodigoProductoVO.of(null));
        assertNull(CodigoProductoVO.of(""));
        assertNull(CodigoProductoVO.of(INVALID_CODIGO));
    }

    @Test
    void testEquals() {
        var codigoProducto1 = CodigoProductoVO.of(VALID_CODIGO);
        var codigoProducto2 = CodigoProductoVO.of(VALID_CODIGO);
        var codigoProducto3 = CodigoProductoVO.of(DIFFERENT_CODIGO);

        assertEquals(codigoProducto1, codigoProducto1);
        assertEquals(codigoProducto1, codigoProducto2);
        assertNotEquals(codigoProducto1, codigoProducto3);
        assertNotEquals(codigoProducto1, null);
    }

    @Test
    void testHashCode() {
        var codigoProducto1 = CodigoProductoVO.of(VALID_CODIGO);
        var codigoProducto2 = CodigoProductoVO.of(VALID_CODIGO);
        var codigoProducto3 = CodigoProductoVO.of(DIFFERENT_CODIGO);

        assertEquals(codigoProducto1.hashCode(), codigoProducto2.hashCode());
        assertNotEquals(codigoProducto1.hashCode(), codigoProducto3.hashCode());
    }

    @Test
    void testToString() {
        var codigoProducto = CodigoProductoVO.of(VALID_CODIGO);

        assertEquals("CodigoProductoVO(value=" + VALID_CODIGO + ")", codigoProducto.toString());
    }
}