package org.enriqueboronat.pruebahexagonal.domain.ean;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EanTest {

    EanCodigoVO eanCodigo;
    DestinoVO destino;
    ProductoVO producto;
    ProveedorVO proveedor;

    @BeforeEach
    void setUp() {
        eanCodigo = EanCodigoVO.of("1234567890123");
        destino = DestinoVO.of("Destino Test", "3");
        producto = ProductoVO.of("Producto Test", "89012");
        proveedor = ProveedorVO.of("Proveedor Test", "1234567");
    }

    @Test
    void testEanCreation() {
        Optional<Ean> eanOptional = Ean.of(eanCodigo, destino, producto, proveedor);

        assertTrue(eanOptional.isPresent());

        Ean ean = eanOptional.get();

        assertEquals(eanCodigo, ean.getEanCodigo());
        assertEquals(destino, ean.getDestino());
        assertEquals(producto, ean.getProducto());
        assertEquals(proveedor, ean.getProveedor());
    }

    @Test
    void testEanCreationWithIncorrectCode() {
        eanCodigo = EanCodigoVO.of("1234567890123");
        destino = DestinoVO.of("Destino Test", "1");
        producto = ProductoVO.of("Producto Test", "12345");
        proveedor = ProveedorVO.of("Proveedor Test", "1234567");

        Optional<Ean> eanOptional = Ean.of(eanCodigo, destino, producto, proveedor);

        assertFalse(eanOptional.isPresent());
    }

    @Test
    void testEanCreationWithNull() {
        Optional<Ean> eanOptional = Ean.of(eanCodigo, destino, producto, null);

        assertFalse(eanOptional.isPresent());
    }

    @Test
    void testEanGetters() {
        Ean ean = Ean.builder()
                .eanCodigo(eanCodigo)
                .destino(destino)
                .producto(producto)
                .proveedor(proveedor)
                .build();

        assertEquals(eanCodigo, ean.getEanCodigo());
        assertEquals(destino, ean.getDestino());
        assertEquals(producto, ean.getProducto());
        assertEquals(proveedor, ean.getProveedor());
    }
}