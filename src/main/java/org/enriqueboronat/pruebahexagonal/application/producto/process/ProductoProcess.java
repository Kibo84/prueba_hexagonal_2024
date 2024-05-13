package org.enriqueboronat.pruebahexagonal.application.producto.process;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.DeleteProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.SearchProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.UpdateProductoCommand;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;

import java.util.List;

public interface ProductoProcess {
    List<Producto> searchProductos();
    Either<Error, Producto> createProducto(CreateProductoCommand command);
    Either<Error, Producto> updateProducto(UpdateProductoCommand command);
    Either<Error, Void> deleteProducto(DeleteProductoCommand command);
    Either<Error, Producto> searchProductoById(SearchProductoCommand command);
}
