package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.destino.command.CreateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.DeleteDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.SearchDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.command.UpdateDestinoCommand;
import org.enriqueboronat.pruebahexagonal.application.destino.process.DestinoProcess;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.entity.DestinoDto;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.entity.DestinoRequest;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.destino.api.mapper.DestinoApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinos")
public class DestinoApiController {
    private final DestinoProcess destinoProcess;
    private final DestinoApiMapper destinoApiMapper;

    @Operation(summary = "Crear Destino")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida", content = @Content(schema = @Schema(implementation = DestinoDto.class))),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        var result = destinoProcess.searchDestinos().stream().map(destinoApiMapper::mapToDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Crear Destino")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Destino creado", content = @Content(schema = @Schema(implementation = DestinoDto.class))),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createDestino(@RequestBody DestinoRequest destinoRequest) {
        var command = CreateDestinoCommand.builder()
            .id(destinoRequest.getId())
            .codigo(destinoRequest.getCodigo())
            .descripcion(destinoRequest.getDescripcion())
            .build();

        var result = destinoProcess.createDestino(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            destino -> ResponseEntity.status(HttpStatus.CREATED).body(destino)
        );
    }

    @Operation(summary = "Obtener Destino")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Destino obtenido",
            content = @Content(schema = @Schema(implementation = DestinoDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getDestino(@PathVariable UUID id) {
        var command = SearchDestinoCommand.builder()
            .id(id)
            .build();

        var result = destinoProcess.searchDestinoById(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            destino -> ResponseEntity.ok(destino)
        );
    }

    @Operation(summary = "Actualizar Destino")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Destino actualizado",
            content = @Content(schema = @Schema(implementation = DestinoDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDestino(@PathVariable UUID id, @RequestBody DestinoRequest destinoRequest) {
        var command = UpdateDestinoCommand.builder()
            .id(id)
            .codigo(destinoRequest.getCodigo())
            .descripcion(destinoRequest.getDescripcion())
            .build();

        var result = destinoProcess.updateDestino(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            destino -> ResponseEntity.ok(destino)
        );
    }

    @Operation(summary = "Eliminar Destino")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Destino eliminado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDestino(@PathVariable UUID id) {
        var command = DeleteDestinoCommand.builder()
            .id(id)
            .build();

        var result = destinoProcess.deleteDestino(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            destino -> ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        );
    }
}
