package org.enriqueboronat.pruebahexagonal.application.producto.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.SearchProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.service.ProductoService;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProductoCommandHandler implements CommandHandler<SearchProductoCommand, Producto> {
    private final ProductoService productoService;

    @Override
    public Either<Error, Producto> handle(SearchProductoCommand command) {
        var producto = productoService.searchProducto(command.getId());
        return producto.<Either<Error, Producto>>map(Either::right)
            .orElseGet(() -> Either.left(new Error("Producto no encontrado")));
    }
}
