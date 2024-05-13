package org.enriqueboronat.pruebahexagonal.application.proveedor.process;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.DeleteProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.SearchProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.UpdateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler.CreateProveedorCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler.DeleteProveedorCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler.SearchProveedorCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.handler.UpdateProveedorCommandHandler;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator.CreateProveedorCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator.DeleteProveedorCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator.SearchProveedorCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator.UpdateProveedorCommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.service.ProveedorService;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProveedorProcessImpl implements ProveedorProcess {
    private final CreateProveedorCommandHandler createProveedorCommandHandler;
    private final UpdateProveedorCommandHandler updateProveedorCommandHandler;
    private final DeleteProveedorCommandHandler deleteProveedorCommandHandler;
    private final SearchProveedorCommandHandler searchProveedorByIdCommandHandler;
    private final CreateProveedorCommandValidator createProveedorCommandValidator;
    private final UpdateProveedorCommandValidator updateProveedorCommandValidator;
    private final DeleteProveedorCommandValidator deleteProveedorCommandValidator;
    private final SearchProveedorCommandValidator searchProveedorByIdCommandValidator;
    private final ProveedorService proveedorService;

    private static final String Proveedor_CREATING = "Creating Proveedor: {}";
    private static final String Proveedor_UPDATING = "Updating Proveedor: {}";
    private static final String Proveedor_DELETING = "Deleting Proveedor: {}";
    private static final String Proveedor_SEARCHING = "Searching Proveedor by id: {}";

    @Override
    public List<Proveedor> searchProveedors() {
        return proveedorService.searchProveedores();
    }

    @Override
    public Either<Error, Proveedor> createProveedor(CreateProveedorCommand command) {
        log.info(Proveedor_CREATING, command.getId());
        return createProveedorCommandValidator.validate(command)
                .flatMap(createProveedorCommandHandler::handle);
    }

    @Override
    public Either<Error, Proveedor> updateProveedor(UpdateProveedorCommand command) {
        log.info(Proveedor_UPDATING, command.getId());
        return updateProveedorCommandValidator.validate(command)
                .flatMap(updateProveedorCommandHandler::handle);
    }

    @Override
    public Either<Error, Void> deleteProveedor(DeleteProveedorCommand command) {
        log.info(Proveedor_DELETING, command.getId());
        return deleteProveedorCommandValidator.validate(command)
                .flatMap(deleteProveedorCommandHandler::handle);
    }

    @Override
    public Either<Error, Proveedor> searchProveedorById(SearchProveedorCommand command) {
        log.info(Proveedor_SEARCHING, command.getId());
        return searchProveedorByIdCommandValidator.validate(command)
                .flatMap(searchProveedorByIdCommandHandler::handle);
    }
}
