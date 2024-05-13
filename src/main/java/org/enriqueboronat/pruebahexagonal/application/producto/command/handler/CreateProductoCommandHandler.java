package org.enriqueboronat.pruebahexagonal.application.producto.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.mapper.ProductoCommandMapper;
import org.enriqueboronat.pruebahexagonal.application.producto.service.ProductoService;
import org.enriqueboronat.pruebahexagonal.domain.producto.entity.Producto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateProductoCommandHandler implements CommandHandler<CreateProductoCommand, Producto> {
    private final ProductoService productoService;
    private final ProductoCommandMapper productoCommandMapper;

    @Override
    public Either<Error, Producto> handle(CreateProductoCommand command) {
        Optional<Producto> producto = productoService.createProducto(productoCommandMapper.toProducto(command));
        return producto.map(Either::<Error, Producto>right)
            .orElseGet(() -> Either.left(new Error("Error al crear el Producto")));
    }
}
