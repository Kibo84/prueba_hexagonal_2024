package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EanInfoDtoTest {

    @Test
    public void testEanInfoDtoBuilder() {
        String ean = "testEan";
        String producto = "testProducto";
        String proveedor = "testProveedor";
        String destino = "testDestino";

        EanInfoDto eanInfoDto = EanInfoDto.builder()
                .ean(ean)
                .producto(producto)
                .proveedor(proveedor)
                .destino(destino)
                .build();

        assertEquals(ean, eanInfoDto.getEan());
        assertEquals(producto, eanInfoDto.getProducto());
        assertEquals(proveedor, eanInfoDto.getProveedor());
        assertEquals(destino, eanInfoDto.getDestino());
    }
}