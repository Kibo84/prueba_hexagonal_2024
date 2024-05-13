package org.enriqueboronat.pruebahexagonal.application.producto.process;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.DeleteProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.SearchProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.UpdateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.handler.CreateProductoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.handler.DeleteProductoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.handler.SearchProductoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.handler.UpdateProductoCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.validator.CreateProductoCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.producto.command.validator.DeleteProductoCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.producto.command.validator.SearchProductoCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.producto.command.validator.UpdateProductoCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.producto.service.ProductoService;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductoProcessImpl implements ProductoProcess {
    private final CreateProductoCommandHandler createProductoCommandHandler;
    private final UpdateProductoCommandHandler updateProductoCommandHandler;
    private final DeleteProductoCommandHandler deleteProductoCommandHandler;
    private final SearchProductoCommandHandler searchProductoByIdCommandHandler;
    private final CreateProductoCommandValidator createProductoCommandValidator;
    private final UpdateProductoCommandValidator updateProductoCommandValidator;
    private final DeleteProductoCommandValidator deleteProductoCommandValidator;
    private final SearchProductoCommandValidator searchProductoByIdCommandValidator;
    private final ProductoService productoService;

    private static final String Producto_CREATING = "Creating Producto: {}";
    private static final String Producto_UPDATING = "Updating Producto: {}";
    private static final String Producto_DELETING = "Deleting Producto: {}";
    private static final String Producto_SEARCHING = "Searching Producto by id: {}";

    @Override
    public List<Producto> searchProductos() {
        return productoService.searchProductos();
    }

    @Override
    public Either<Error, Producto> createProducto(CreateProductoCommand command) {
        log.info(Producto_CREATING, command.getId());
        return createProductoCommandValidator.validate(command)
                .flatMap(createProductoCommandHandler::handle);
    }

    @Override
    public Either<Error, Producto> updateProducto(UpdateProductoCommand command) {
        log.info(Producto_UPDATING, command.getId());
        return updateProductoCommandValidator.validate(command)
                .flatMap(updateProductoCommandHandler::handle);
    }

    @Override
    public Either<Error, Void> deleteProducto(DeleteProductoCommand command) {
        log.info(Producto_DELETING, command.getId());
        return deleteProductoCommandValidator.validate(command)
                .flatMap(deleteProductoCommandHandler::handle);
    }

    @Override
    public Either<Error, Producto> searchProductoById(SearchProductoCommand command) {
        log.info(Producto_SEARCHING, command.getId());
        return searchProductoByIdCommandValidator.validate(command)
                .flatMap(searchProductoByIdCommandHandler::handle);
    }
}
