package org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.mapper.ProveedorCommandMapper;
import org.enriqueboronat.pruebahexagonal.application.proveedor.service.ProveedorService;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateProveedorCommandHandler implements CommandHandler<CreateProveedorCommand, Proveedor> {
    private final ProveedorService proveedorService;
    private final ProveedorCommandMapper proveedorCommandMapper;

    @Override
    public Either<Error, Proveedor> handle(CreateProveedorCommand command) {
        Optional<Proveedor> proveedor = proveedorService.createProveedor(proveedorCommandMapper.toProveedor(command));
        return proveedor.map(Either::<Error, Proveedor>right)
            .orElseGet(() -> Either.left(new Error("Error al crear el Proveedor")));
    }
}
