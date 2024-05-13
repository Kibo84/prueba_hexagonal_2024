package org.enriqueboronat.pruebahexagonal.application.ean.command.validator;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.common.command.CommandValidator;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEanInfoCommandValidator implements CommandValidator<SearchEanInfoCommand> {

    private static final String NUMERIC_REGEX = "\\d+";
    private static final String INVALID_EAN_TERMINATION = "7";
    private static final Integer EAN_LENGTH = 13;
    private static final String INVALID_LENGTH_MESSAGE = "EAN must be 13 digits long";
    private static final String NON_NUMERIC_MESSAGE = "EAN must be numeric";
    private static final String ENDS_WITH_SEVEN_MESSAGE = "EAN must not end with 7";

    @Override
    public Either<Error, SearchEanInfoCommand> validate(SearchEanInfoCommand command) {
        List<String> errorMessages = new ArrayList<>();
        String ean = command.getEan();

        if (ean.length() != EAN_LENGTH) {
            errorMessages.add(INVALID_LENGTH_MESSAGE);
        }

        if (!ean.matches(NUMERIC_REGEX)) {
            errorMessages.add(NON_NUMERIC_MESSAGE);
        }

        if (ean.endsWith(INVALID_EAN_TERMINATION)) {
            errorMessages.add(ENDS_WITH_SEVEN_MESSAGE);
        }

        if (!errorMessages.isEmpty()) {
            return Either.left(new Error(String.join(", ", errorMessages)));
        }

        return Either.right(command);
    }
}
