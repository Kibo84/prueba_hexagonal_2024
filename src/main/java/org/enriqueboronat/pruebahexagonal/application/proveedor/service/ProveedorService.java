package org.enriqueboronat.pruebahexagonal.application.proveedor.service;


import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProveedorService {
    List<Proveedor> searchProveedores();

    Optional<Proveedor> searchProveedor(UUID id);

    Optional<Proveedor> createProveedor(Proveedor Proveedor);

    Optional<Proveedor> updateProveedor(Proveedor Proveedor);

    void deleteProveedor(UUID id);
}
