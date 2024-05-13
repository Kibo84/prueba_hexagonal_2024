package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.repository;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.repository.ProductoRepository;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.entity.ProductoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.producto.mapper.ProductoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductoRepositoryImpl implements ProductoRepository {
    private final ProductoRepositoryJpa ProductoRepositoryJpa;
    private final ProductoEntityMapper ProductoEntityMapper;

    @Override
    public List<Producto> searchProductos() {
        return ProductoRepositoryJpa.findAll().stream().map(ProductoEntityMapper::mapToDomain).toList();
    }

    @Override
    public Optional<Producto> searchProductoById(UUID id) {
        return ProductoRepositoryJpa.findById(id).map(ProductoEntityMapper::mapToDomain);
    }

    @Override
    public Optional<Producto> createProducto(Producto Producto) {
        ProductoEntity ProductoEntity = ProductoEntityMapper.mapToEntity(Producto);
        try {
            ProductoEntity savedProductoEntity = ProductoRepositoryJpa.save(ProductoEntity);
            return Optional.of(ProductoEntityMapper.mapToDomain(savedProductoEntity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Producto> updateProducto(Producto Producto) {
        return createProducto(Producto);
    }

    @Override
    public void deleteProducto(UUID id) {
        ProductoRepositoryJpa.deleteById(id);
    }
}
