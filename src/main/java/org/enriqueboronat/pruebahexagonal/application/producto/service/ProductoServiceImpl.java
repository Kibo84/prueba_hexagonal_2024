package org.enriqueboronat.pruebahexagonal.application.producto.service;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.enriqueboronat.pruebahexagonal.domain.producto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> searchProductos() {
        return productoRepository.searchProductos();
    }

    @Override
    public Optional<Producto> searchProducto(UUID id) {
        return productoRepository.searchProductoById(id);
    }

    @Override
    public Optional<Producto> createProducto(Producto Producto) {
        return productoRepository.createProducto(Producto);
    }

    @Override
    public Optional<Producto> updateProducto(Producto Producto) {
        return productoRepository.updateProducto(Producto);
    }

    @Override
    public void deleteProducto(UUID id) {
        productoRepository.deleteProducto(id);
    }
}
