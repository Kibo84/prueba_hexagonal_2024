package org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.SearchProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.service.ProveedorService;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProveedorCommandHandler implements CommandHandler<SearchProveedorCommand, Proveedor> {
    private final ProveedorService proveedorService;

    @Override
    public Either<Error, Proveedor> handle(SearchProveedorCommand command) {
        var proveedor = proveedorService.searchProveedor(command.getId());
        return proveedor.<Either<Error, Proveedor>>map(Either::right)
            .orElseGet(() -> Either.left(new Error("Proveedor no encontrado")));
    }
}
