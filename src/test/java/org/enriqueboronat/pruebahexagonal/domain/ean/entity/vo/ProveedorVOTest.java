package org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorVOTest {
    @Test
    void testOfWithValidInput() {
        ProveedorVO proveedor = ProveedorVO.of("Proveedor Test", "8901293");
        assertNotNull(proveedor);
        assertEquals("Proveedor Test", proveedor.getNombre());
        assertEquals("8901293", proveedor.getCodigoProveedor());
    }

    @Test
    void testOfWithInvalidInputWithIncorrectCharacters() {
        ProveedorVO proveedor = ProveedorVO.of("Proveedor Test", "8901a");
        assertNull(proveedor);
    }

    @Test
    void testOfWithInvalidInputWithIncorrectLength() {
        ProveedorVO proveedor = ProveedorVO.of("Proveedor Test", "89015098");
        assertNull(proveedor);
    }

    @Test
    void testOfWithInvalidInputWithNullCode() {
        ProveedorVO proveedor = ProveedorVO.of("Proveedor Test", null);
        assertNull(proveedor);
    }

    @Test
    void testOfWithInvalidInputWithNullName() {
        ProveedorVO proveedor = ProveedorVO.of(null, "8901293");
        assertNull(proveedor);
    }

    @Test
    void testValueAnnotation() {
        ProveedorVO proveedor1 = ProveedorVO.of("Proveedor Test", "8901293");
        ProveedorVO proveedor2 = ProveedorVO.of("Proveedor Test", "8901293");

        assertEquals(proveedor1, proveedor2);
        assertEquals(proveedor1.hashCode(), proveedor2.hashCode());

        assertTrue(proveedor1.equals(proveedor2));

        String expectedToString = "ProveedorVO(nombre=Proveedor Test, codigoProveedor=8901293)";
        assertEquals(expectedToString, proveedor1.toString());
    }
}