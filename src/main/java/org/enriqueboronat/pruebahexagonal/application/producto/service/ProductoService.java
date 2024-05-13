package org.enriqueboronat.pruebahexagonal.application.producto.service;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoService {
    List<Producto> searchProductos();

    Optional<Producto> searchProducto(UUID id);

    Optional<Producto> createProducto(Producto Producto);

    Optional<Producto> updateProducto(Producto Producto);

    void deleteProducto(UUID id);
}
