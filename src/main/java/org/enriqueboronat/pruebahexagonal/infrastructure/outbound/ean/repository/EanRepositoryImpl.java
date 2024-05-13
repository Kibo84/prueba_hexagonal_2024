package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.repository.EanRepository;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.repository.DestinoRepositoryJpa;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.ean.mapper.EanMapper;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.repository.ProductoRepositoryJpa;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.repository.ProveedorRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
@RequiredArgsConstructor
public class EanRepositoryImpl implements EanRepository {
    private final DestinoRepositoryJpa destinoRepositoryJpa;
    private final ProductoRepositoryJpa productoRepositoryJpa;
    private final ProveedorRepositoryJpa proveedorRepositoryJpa;
    private final EanMapper eanMapper;

    @Override
    public Optional<DestinoVO> searchDestinoVOByCode(String code) {
        log.info("Searching destino by code: {}", code);
        return destinoRepositoryJpa.findByCodigo(code).map(eanMapper::mapToDestinoVO);
    }

    @Override
    public Optional<ProveedorVO> searchProveedorVOByCode(String code) {
        log.info("Searching proveedor by code: {}", code);
        return proveedorRepositoryJpa.findByCodigoProveedor(code).map(eanMapper::mapToProveedorVO);
    }

    @Override
    public Optional<ProductoVO> searchProductoVOByCode(String code) {
        log.info("Searching producto by code: {}", code);
        return productoRepositoryJpa.findByCodigoProducto(code).map(eanMapper::mapToProductoVO);
    }
}
