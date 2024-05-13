package org.enriqueboronat.pruebahexagonal.application.ean.service;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.EanCodigoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.repository.EanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EanServiceImpl implements EanService {
    private final EanRepository eanRepository;

    @Override
    public Optional<Ean> searchEanInfo(String code) {
        String proveedorCode = code.substring(0, 7);
        String productoCode = code.substring(7, 12);
        String destinoCode = code.substring(12, 13);

        Optional<ProveedorVO> proveedor = eanRepository.searchProveedorVOByCode(proveedorCode);
        Optional<ProductoVO> producto = eanRepository.searchProductoVOByCode(productoCode);
        Optional<DestinoVO> destino = eanRepository.searchDestinoVOByCode(destinoCode);

        if (proveedor.isEmpty() || producto.isEmpty() || destino.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
            Ean.builder()
                .eanCodigo(EanCodigoVO.of(code))
                .proveedor(proveedor.get())
                .producto(producto.get())
                .destino(destino.get())
                .build()
        );
    }
}
