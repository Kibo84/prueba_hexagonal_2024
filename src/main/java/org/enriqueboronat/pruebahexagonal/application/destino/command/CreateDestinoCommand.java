package org.enriqueboronat.pruebahexagonal.application.destino.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.enriqueboronat.pruebahexagonal.application.common.command.Command;

import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class CreateDestinoCommand implements Command {
    UUID id;
    String codigo;
    String descripcion;
}
