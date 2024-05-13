package org.enriqueboronat.pruebahexagonal.domain.producto.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.CodigoProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.vo.NombreProductoVO;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class ProductoTest {
    private static final CodigoProductoVO VALID_CODIGO = CodigoProductoVO.of("12345");
    private static final NombreProductoVO VALID_NOMBRE = NombreProductoVO.of("Leche");
    private static final CodigoProductoVO DIFFERENT_CODIGO = CodigoProductoVO.of("56785");
    private static final NombreProductoVO DIFFERENT_NOMBRE = NombreProductoVO.of("Pan");

    @Test
    void testOfWithCorrectParameters() {
        assertNotNull(Producto.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE));
    }

    @Test
    void testOfWithIncorrectParameters() {
        assertEquals(Optional.empty(), Producto.of(null, VALID_CODIGO, VALID_NOMBRE));
        assertEquals(Optional.empty(), Producto.of(UUID.randomUUID(), null, VALID_NOMBRE));
        assertEquals(Optional.empty(), Producto.of(UUID.randomUUID(), VALID_CODIGO, null));
        assertEquals(Optional.empty(), Producto.of(null, null, null));
    }

    @Test
    void testEquals() {
        var producto1 = Producto.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var producto2 = Producto.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var producto3 = Producto.of(UUID.randomUUID(), DIFFERENT_CODIGO, DIFFERENT_NOMBRE);

        assertEquals(producto1, producto1);
        assertNotEquals(producto1, producto2);
        assertNotEquals(producto1, producto3);
        assertNotEquals(producto1, null);
    }

    @Test
    void testHashCode() {
        var producto1 = Producto.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var producto2 = Producto.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var producto3 = Producto.of(UUID.randomUUID(), DIFFERENT_CODIGO, DIFFERENT_NOMBRE);

        assertNotEquals(producto1.hashCode(), producto2.hashCode());
        assertNotEquals(producto1.hashCode(), producto3.hashCode());
    }
}