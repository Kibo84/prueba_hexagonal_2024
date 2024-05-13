package org.enriqueboronat.pruebahexagonal.application.destino.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.destino.command.DeleteDestinoCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteDestinoCommandValidator implements CommandValidator<DeleteDestinoCommand> {

    private static final String NULL_MESSAGE = "No puede ser null";

    @Override
    public Either<Error, DeleteDestinoCommand> validate(DeleteDestinoCommand command) {
        List<String> errorMessages = new ArrayList<>();

        if (command == null) {
            errorMessages.add("DeleteDestinoCommand " + NULL_MESSAGE);
        } else {
            if (command.getId() == null) {
                errorMessages.add("Id " + NULL_MESSAGE);
            }
        }

        if (!errorMessages.isEmpty()) {
            return Either.left(new Error(String.join(", ", errorMessages)));
        }

        return Either.right(command);
    }
}
