package org.enriqueboronat.pruebahexagonal.domain.producto.repository;

import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoRepository {
    List<Producto> searchProductos();

    Optional<Producto> searchProductoById(UUID id);

    Optional<Producto> createProducto(Producto Producto);

    Optional<Producto> updateProducto(Producto Producto);

    void deleteProducto(UUID id);
}
