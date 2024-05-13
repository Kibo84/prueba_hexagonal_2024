package org.enriqueboronat.pruebahexagonal.domain.ean.repository;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.DestinoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProductoVO;
import org.enriqueboronat.pruebahexagonal.domain.ean.entity.vo.ProveedorVO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EanRepository {
    Optional<DestinoVO> searchDestinoVOByCode(String code);

    Optional<ProveedorVO> searchProveedorVOByCode(String code);

    Optional<ProductoVO> searchProductoVOByCode(String code);
}
