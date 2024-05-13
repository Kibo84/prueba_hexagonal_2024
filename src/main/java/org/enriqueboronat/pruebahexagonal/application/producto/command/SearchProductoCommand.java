package org.enriqueboronat.pruebahexagonal.application.producto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.enriqueboronat.pruebahexagonal.application.common.command.Command;

import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class SearchProductoCommand implements Command {
    UUID id;
}
