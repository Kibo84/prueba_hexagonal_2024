package org.enriqueboronat.pruebahexagonal.application.destino.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.destino.command.CreateDestinoCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateDestinoCommandValidator implements CommandValidator<CreateDestinoCommand> {

    private static final String NULL_MESSAGE = "No puede ser null";

    @Override
    public Either<Error, CreateDestinoCommand> validate(CreateDestinoCommand command) {
        List<String> errorMessages = new ArrayList<>();

        if (command == null) {
            errorMessages.add("DestinoRequest " + NULL_MESSAGE);
        } else {
            if (command.getId() == null) {
                errorMessages.add("Id " + NULL_MESSAGE);
            }
            if (command.getCodigo() == null) {
                errorMessages.add("Codigo " + NULL_MESSAGE);
            }
            if (command.getDescripcion() == null) {
                errorMessages.add("Descripcion " + NULL_MESSAGE);
            }
        }

        if (!errorMessages.isEmpty()) {
            return Either.left(new Error(String.join(", ", errorMessages)));
        }

        return Either.right(command);
    }
}
