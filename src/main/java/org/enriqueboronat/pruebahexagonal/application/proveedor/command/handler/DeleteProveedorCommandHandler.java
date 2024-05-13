package org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.DeleteProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.service.ProveedorService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProveedorCommandHandler implements CommandHandler<DeleteProveedorCommand, Void> {
    private final ProveedorService ProveedorService;

    @Override
    public Either<Error, Void> handle(DeleteProveedorCommand command) {
        ProveedorService.deleteProveedor(command.getId());
        return Either.right(null);
    }
}
