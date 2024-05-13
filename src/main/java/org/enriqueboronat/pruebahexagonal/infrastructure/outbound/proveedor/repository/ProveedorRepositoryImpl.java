package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.repository;

import lombok.AllArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.repository.ProveedorRepository;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity.ProveedorEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.mapper.ProveedorEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProveedorRepositoryImpl implements ProveedorRepository {
    private final ProveedorRepositoryJpa proveedorRepositoryJpa;
    private final ProveedorEntityMapper proveedorEntityMapper;

    @Override
    public List<Proveedor> searchProveedores() {
        return proveedorRepositoryJpa.findAll().stream().map(proveedorEntityMapper::mapToDomain).toList();
    }

    @Override
    public Optional<Proveedor> searchProveedorById(UUID id) {
        return proveedorRepositoryJpa.findById(id).map(proveedorEntityMapper::mapToDomain);
    }

    @Override
    public Optional<Proveedor> createProveedor(Proveedor Proveedor) {
        ProveedorEntity ProveedorEntity = proveedorEntityMapper.mapToEntity(Proveedor);
        try {
            ProveedorEntity savedProveedorEntity = proveedorRepositoryJpa.save(ProveedorEntity);
            return Optional.of(proveedorEntityMapper.mapToDomain(savedProveedorEntity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Proveedor> updateProveedor(Proveedor Proveedor) {
        return createProveedor(Proveedor);
    }

    @Override
    public void deleteProveedor(UUID id) {
        proveedorRepositoryJpa.deleteById(id);
    }
}
