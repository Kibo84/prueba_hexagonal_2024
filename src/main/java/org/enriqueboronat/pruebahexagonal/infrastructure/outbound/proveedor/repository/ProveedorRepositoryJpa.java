package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.repository;

import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProveedorRepositoryJpa extends JpaRepository<ProveedorEntity, UUID> {
    Optional<ProveedorEntity> findByCodigoProveedor(String code);
}
