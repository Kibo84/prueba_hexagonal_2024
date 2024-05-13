package org.enriqueboronat.pruebahexagonal.application.common.command;

import io.vavr.control.Either;

public interface CommandValidator<C extends Command> {
    Either<Error, C> validate(C command);
}
