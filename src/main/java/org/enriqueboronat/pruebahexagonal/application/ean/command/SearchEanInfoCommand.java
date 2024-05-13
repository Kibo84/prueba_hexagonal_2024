package org.enriqueboronat.pruebahexagonal.application.ean.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.enriqueboronat.pruebahexagonal.application.common.command.Command;

@Builder
@Value
@AllArgsConstructor
public class SearchEanInfoCommand implements Command {
    String ean;
}
