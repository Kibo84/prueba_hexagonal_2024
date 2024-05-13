package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.ean.command.SearchEanInfoCommand;
import org.enriqueboronat.pruebahexagonal.application.ean.process.EanProcess;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.dto.EanInfoDto;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.ean.api.mapper.EanDtoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/ean")
@RequiredArgsConstructor
public class EanApiController {
    private final EanDtoMapper eanDtoMapper;
    private final EanProcess eanProcess;

    @Operation(summary = "Ean Info")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ean Info",
            content = @Content(schema = @Schema(implementation = EanInfoDto.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Error de servidor",
            content = @Content),
    })
    @Cacheable("eanInfo")
    @GetMapping("/{ean}")
    public ResponseEntity<?> getEanInfo(@PathVariable String ean) {
        var command = SearchEanInfoCommand.builder()
            .ean(ean)
            .build();

        var result = eanProcess.searchEanInfo(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            eanInfo -> ResponseEntity.ok(eanDtoMapper.mapToDto(eanInfo))
        );
    }
}
