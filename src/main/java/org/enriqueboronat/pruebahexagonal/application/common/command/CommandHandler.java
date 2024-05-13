package org.enriqueboronat.pruebahexagonal.application.common.command;

import io.vavr.control.Either;

public interface CommandHandler<C extends Command, R> {
    Either<Error, R> handle(C command);
}
