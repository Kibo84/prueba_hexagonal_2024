package org.enriqueboronat.pruebahexagonal.application.producto.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateProductoCommandValidator implements CommandValidator<CreateProductoCommand> {

    private static final String NULL_MESSAGE = "No puede ser null";

    @Override
    public Either<Error, CreateProductoCommand> validate(CreateProductoCommand command) {
        List<String> errorMessages = new ArrayList<>();

        if (command == null) {
            errorMessages.add("ProductoRequest " + NULL_MESSAGE);
        } else {
            if (command.getId() == null) {
                errorMessages.add("Id " + NULL_MESSAGE);
            }
            if (command.getCodigoProducto() == null) {
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
