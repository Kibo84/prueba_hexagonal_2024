package org.enriqueboronat.pruebahexagonal.application.producto.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.DeleteProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.service.ProductoService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductoCommandHandler implements CommandHandler<DeleteProductoCommand, Void> {
    private final ProductoService productoService;

    @Override
    public Either<Error, Void> handle(DeleteProductoCommand command) {
        productoService.deleteProducto(command.getId());
        return Either.right(null);
    }
}
