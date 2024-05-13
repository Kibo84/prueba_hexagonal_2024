package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.repository;

import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DestinoRepositoryJpa extends JpaRepository<DestinoEntity, UUID> {
    Optional<DestinoEntity> findByCodigo(String codigo);
}
