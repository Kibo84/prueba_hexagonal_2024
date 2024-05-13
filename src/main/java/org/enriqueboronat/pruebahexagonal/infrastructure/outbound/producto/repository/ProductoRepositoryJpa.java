package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.repository;

import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoRepositoryJpa extends JpaRepository<ProductoEntity, UUID> {
    Optional<ProductoEntity> findByCodigoProducto(String codigoProducto);
}
