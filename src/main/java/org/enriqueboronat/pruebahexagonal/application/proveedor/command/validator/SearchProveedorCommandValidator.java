package org.enriqueboronat.pruebahexagonal.application.proveedor.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.SearchProveedorCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchProveedorCommandValidator implements CommandValidator<SearchProveedorCommand> {

    private static final String NULL_MESSAGE = "No puede ser null";

    @Override
    public Either<Error, SearchProveedorCommand> validate(SearchProveedorCommand command) {
        List<String> errorMessages = new ArrayList<>();

        if (command == null) {
            errorMessages.add("SearchProveedorCommand " + NULL_MESSAGE);
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
