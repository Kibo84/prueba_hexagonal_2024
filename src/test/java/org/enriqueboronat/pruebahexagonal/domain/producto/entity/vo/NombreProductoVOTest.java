package org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NombreProductoVOTest {
    private static final String VALID_NOMBRE = "Leche";
    private static final String DIFFERENT_NOMBRE = "Pan";

    @Test
    void of() {
        assertNotNull(NombreProductoVO.of(VALID_NOMBRE));
        assertNull(NombreProductoVO.of(null));
        assertNull(NombreProductoVO.of(""));
    }

    @Test
    void testEquals() {
        var nombreProducto1 = NombreProductoVO.of(VALID_NOMBRE);
        var nombreProducto2 = NombreProductoVO.of(VALID_NOMBRE);
        var nombreProducto3 = NombreProductoVO.of(DIFFERENT_NOMBRE);

        assertEquals(nombreProducto1, nombreProducto1);
        assertEquals(nombreProducto1, nombreProducto2);
        assertNotEquals(nombreProducto1, nombreProducto3);
        assertNotEquals(nombreProducto1, null);
    }

    @Test
    void testHashCode() {
        var nombreProducto1 = NombreProductoVO.of(VALID_NOMBRE);
        var nombreProducto2 = NombreProductoVO.of(VALID_NOMBRE);
        var nombreProducto3 = NombreProductoVO.of(DIFFERENT_NOMBRE);

        assertEquals(nombreProducto1.hashCode(), nombreProducto2.hashCode());
        assertNotEquals(nombreProducto1.hashCode(), nombreProducto3.hashCode());
    }

    @Test
    void testToString() {
        var nombreProducto = NombreProductoVO.of(VALID_NOMBRE);

        assertEquals("NombreProductoVO(value=" + VALID_NOMBRE + ")", nombreProducto.toString());
    }
}