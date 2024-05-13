package org.enriqueboronat.pruebahexagonal.domain.ean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;

import java.util.Objects;
import java.util.Optional;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ean {
    private EanCodigoVO eanCodigo;
    private DestinoVO destino;
    private ProductoVO producto;
    private ProveedorVO proveedor;

    public static Optional<Ean> of(
        EanCodigoVO eanCodigo,
        DestinoVO destino,
        ProductoVO producto,
        ProveedorVO proveedor
    ) {
        if (Objects.isNull(eanCodigo)
            || Objects.isNull(destino)
            || Objects.isNull(producto)
            || Objects.isNull(proveedor)
        ) {
            return Optional.empty();
        }

        String expectedEanCodigo = proveedor.getCodigoProveedor() + producto.getCodigoProducto() + destino.getCodigo();
        if (!eanCodigo.getCodigo().equals(expectedEanCodigo)) {
            return Optional.empty();
        }

        return Optional.of(Ean.builder()
            .eanCodigo(eanCodigo)
            .destino(destino)
            .producto(producto)
            .proveedor(proveedor)
            .build()
        );
    }
}
