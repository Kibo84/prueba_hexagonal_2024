package org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateProveedorCommandValidator implements CommandValidator<CreateProveedorCommand> {

    private static final String NULL_MESSAGE = "No puede ser null";

    @Override
    public Either<Error, CreateProveedorCommand> validate(CreateProveedorCommand command) {
        List<String> errorMessages = new ArrayList<>();

        if (command == null) {
            errorMessages.add("ProveedorRequest " + NULL_MESSAGE);
        } else {
            if (command.getId() == null) {
                errorMessages.add("Id " + NULL_MESSAGE);
            }
            if (command.getCodigoProveedor() == null) {
                errorMessages.add("Codigo " + NULL_MESSAGE);
            }
            if (command.getNombre() == null) {
                errorMessages.add("Descripcion " + NULL_MESSAGE);
            }
        }

        if (!errorMessages.isEmpty()) {
            return Either.left(new Error(String.join(", ", errorMessages)));
        }

        return Either.right(command);
    }
}
