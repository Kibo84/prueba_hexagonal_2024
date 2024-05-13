package org.enriqueboronat.pruebahexagonal.domain.proveedor.repository;

import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProveedorRepository {
    List<Proveedor> searchProveedores();

    Optional<Proveedor> searchProveedorById(UUID id);

    Optional<Proveedor> createProveedor(Proveedor Proveedor);

    Optional<Proveedor> updateProveedor(Proveedor Proveedor);

    void deleteProveedor(UUID id);
}
