package org.enriqueboronat.pruebahexagonal.application.proveedor.service;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> searchProveedores() {
        return proveedorRepository.searchProveedores();
    }

    @Override
    public Optional<Proveedor> searchProveedor(UUID id) {
        return proveedorRepository.searchProveedorById(id);
    }

    @Override
    public Optional<Proveedor> createProveedor(Proveedor Proveedor) {
        return proveedorRepository.createProveedor(Proveedor);
    }

    @Override
    public Optional<Proveedor> updateProveedor(Proveedor Proveedor) {
        return proveedorRepository.updateProveedor(Proveedor);
    }

    @Override
    public void deleteProveedor(UUID id) {
        proveedorRepository.deleteProveedor(id);
    }
}
