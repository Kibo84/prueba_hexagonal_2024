package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NombreProveedorVOTest {
    private static final String VALID_NOMBRE = "Proveedor1";
    private static final String DIFFERENT_NOMBRE = "Proveedor2";

    @Test
    void of() {
        assertNotNull(NombreProveedorVO.of(VALID_NOMBRE));
        assertNull(NombreProveedorVO.of(null));
        assertNull(NombreProveedorVO.of(""));
    }

    @Test
    void testEquals() {
        var nombreProveedor1 = NombreProveedorVO.of(VALID_NOMBRE);
        var nombreProveedor2 = NombreProveedorVO.of(VALID_NOMBRE);
        var nombreProveedor3 = NombreProveedorVO.of(DIFFERENT_NOMBRE);

        assertEquals(nombreProveedor1, nombreProveedor1);
        assertEquals(nombreProveedor1, nombreProveedor2);
        assertNotEquals(nombreProveedor1, nombreProveedor3);
        assertNotEquals(nombreProveedor1, null);
    }

    @Test
    void testHashCode() {
        var nombreProveedor1 = NombreProveedorVO.of(VALID_NOMBRE);
        var nombreProveedor2 = NombreProveedorVO.of(VALID_NOMBRE);
        var nombreProveedor3 = NombreProveedorVO.of(DIFFERENT_NOMBRE);

        assertEquals(nombreProveedor1.hashCode(), nombreProveedor2.hashCode());
        assertNotEquals(nombreProveedor1.hashCode(), nombreProveedor3.hashCode());
    }

    @Test
    void testToString() {
        var nombreProveedor = NombreProveedorVO.of(VALID_NOMBRE);

        assertEquals("NombreProveedorVO(value=" + VALID_NOMBRE + ")", nombreProveedor.toString());
    }
}