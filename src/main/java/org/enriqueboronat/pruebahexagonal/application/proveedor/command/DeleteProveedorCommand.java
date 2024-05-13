package org.enriqueboronat.pruebahexagonal.application.proveedor.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.enriqueboronat.pruebahexagonal.application.common.command.Command;

import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class DeleteProveedorCommand implements Command {
    UUID id;
}