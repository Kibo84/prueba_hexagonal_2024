package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoVOTest {
    @Test
    void testOfWithValidInput() {
        ProductoVO producto = ProductoVO.of("Producto Test", "89012");
        assertNotNull(producto);
        assertEquals("Producto Test", producto.getNombre());
        assertEquals("89012", producto.getCodigoProducto());
    }

    @Test
    void testOfWithInvalidInputWithIncorrectCharacters() {
        ProductoVO producto = ProductoVO.of("Producto Test", "8901a");
        assertNull(producto);
    }

    @Test
    void testOfWithInvalidInputWithIncorrectLength() {
        ProductoVO producto = ProductoVO.of("Producto Test", "89015098");
        assertNull(producto);
    }

    @Test
    void testOfWithInvalidInputWithNullCode() {
        ProductoVO producto = ProductoVO.of("Producto Test", null);
        assertNull(producto);
    }

    @Test
    void testOfWithInvalidInputWithNullName() {
        ProductoVO producto = ProductoVO.of(null, "89012");
        assertNull(producto);
    }

    @Test
    void testValueAnnotation() {
        ProductoVO producto1 = ProductoVO.of("Producto Test", "89012");
        ProductoVO producto2 = ProductoVO.of("Producto Test", "89012");

        assertEquals(producto1, producto2);
        assertEquals(producto1.hashCode(), producto2.hashCode());

        assertTrue(producto1.equals(producto2));

        String expectedToString = "ProductoVO(nombre=Producto Test, codigoProducto=89012)";
        assertEquals(expectedToString, producto1.toString());
    }
}