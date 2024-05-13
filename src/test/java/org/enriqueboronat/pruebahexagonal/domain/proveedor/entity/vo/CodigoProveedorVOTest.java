package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoProveedorVOTest {
    private static final String VALID_CODIGO = "1234567";
    private static final String INVALID_CODIGO = "asdfg";
    private static final String DIFFERENT_CODIGO = "5678575";

    @Test
    void testOfWithCorrectParameter() {
        assertNotNull(CodigoProveedorVO.of(VALID_CODIGO));
    }

    @Test
    void testOfWithIncorrectParameters() {
        assertNull(CodigoProveedorVO.of(null));
        assertNull(CodigoProveedorVO.of(""));
        assertNull(CodigoProveedorVO.of(INVALID_CODIGO));
    }

    @Test
    void testEquals() {
        var codigoProveedor1 = CodigoProveedorVO.of(VALID_CODIGO);
        var codigoProveedor2 = CodigoProveedorVO.of(VALID_CODIGO);
        var codigoProveedor3 = CodigoProveedorVO.of(DIFFERENT_CODIGO);

        assertEquals(codigoProveedor1, codigoProveedor1);
        assertEquals(codigoProveedor1, codigoProveedor2);
        assertNotEquals(codigoProveedor1, codigoProveedor3);
        assertNotEquals(codigoProveedor1, null);
    }

    @Test
    void testHashCode() {
        var codigoProveedor1 = CodigoProveedorVO.of(VALID_CODIGO);
        var codigoProveedor2 = CodigoProveedorVO.of(VALID_CODIGO);
        var codigoProveedor3 = CodigoProveedorVO.of(DIFFERENT_CODIGO);

        assertEquals(codigoProveedor1.hashCode(), codigoProveedor2.hashCode());
        assertNotEquals(codigoProveedor1.hashCode(), codigoProveedor3.hashCode());
    }

    @Test
    void testToString() {
        var codigoProveedor = CodigoProveedorVO.of(VALID_CODIGO);

        assertEquals("CodigoProveedorVO(value=" + VALID_CODIGO + ")", codigoProveedor.toString());
    }
}