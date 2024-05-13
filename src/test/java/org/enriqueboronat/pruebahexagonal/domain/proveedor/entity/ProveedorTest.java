package org.enriqueboronat.pruebahexagonal.domain.proveedor.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.CodigoProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.vo.NombreProveedorVO;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class ProveedorTest {
    private static final CodigoProveedorVO VALID_CODIGO = CodigoProveedorVO.of("1234567");
    private static final NombreProveedorVO VALID_NOMBRE = NombreProveedorVO.of("Leche");
    private static final CodigoProveedorVO DIFFERENT_CODIGO = CodigoProveedorVO.of("5678575");
    private static final NombreProveedorVO DIFFERENT_NOMBRE = NombreProveedorVO.of("Pan");

    @Test
    void testOfWithCorrectParameters() {
        assertNotNull(Proveedor.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE));
    }

    @Test
    void testOfWithIncorrectParameters() {
        assertEquals(Optional.empty(), Proveedor.of(null, VALID_CODIGO, VALID_NOMBRE));
        assertEquals(Optional.empty(), Proveedor.of(UUID.randomUUID(), null, VALID_NOMBRE));
        assertEquals(Optional.empty(), Proveedor.of(UUID.randomUUID(), VALID_CODIGO, null));
        assertEquals(Optional.empty(), Proveedor.of(null, null, null));
    }

    @Test
    void testEquals() {
        var proveedor1 = Proveedor.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var proveedor2 = Proveedor.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var proveedor3 = Proveedor.of(UUID.randomUUID(), DIFFERENT_CODIGO, DIFFERENT_NOMBRE);

        assertEquals(proveedor1, proveedor1);
        assertNotEquals(proveedor1, proveedor2); // UUIDs are different
        assertNotEquals(proveedor1, proveedor3);
        assertNotEquals(proveedor1, null);
    }

    @Test
    void testHashCode() {
        var proveedor1 = Proveedor.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var proveedor2 = Proveedor.of(UUID.randomUUID(), VALID_CODIGO, VALID_NOMBRE);
        var proveedor3 = Proveedor.of(UUID.randomUUID(), DIFFERENT_CODIGO, DIFFERENT_NOMBRE);

        assertNotEquals(proveedor1.hashCode(), proveedor2.hashCode()); // UUIDs are different
        assertNotEquals(proveedor1.hashCode(), proveedor3.hashCode());
    }
}