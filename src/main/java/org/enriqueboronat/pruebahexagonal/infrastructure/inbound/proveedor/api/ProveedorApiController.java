package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.DeleteProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.SearchProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.UpdateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.process.ProveedorProcess;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.entity.ProveedorDto;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.entity.ProveedorRequest;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.proveedor.api.mapper.ProveedorApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proveedores")
public class ProveedorApiController {
    private final ProveedorProcess proveedorProcess;
    private final ProveedorApiMapper proveedorApiMapper;

    @Operation(summary = "Crear Proveedor")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida",
            content = @Content(schema = @Schema(implementation = ProveedorDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        var result = proveedorProcess.searchProveedors().stream().map(proveedorApiMapper::mapToDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Crear Proveedor")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Proveedor creado",
            content = @Content(schema = @Schema(implementation = ProveedorDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorRequest proveedorRequest) {
        var command = CreateProveedorCommand.builder()
            .id(proveedorRequest.getId())
            .codigoProveedor(proveedorRequest.getCodigoProveedor())
            .nombre(proveedorRequest.getNombre())
            .build();

        var result = proveedorProcess.createProveedor(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            Proveedor -> ResponseEntity.status(HttpStatus.CREATED).body(Proveedor)
        );
    }

    @Operation(summary = "Obtener Proveedor")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Proveedor obtenido",
            content = @Content(schema = @Schema(implementation = ProveedorDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedor(@PathVariable UUID id) {
        var command = SearchProveedorCommand.builder()
            .id(id)
            .build();

        var result = proveedorProcess.searchProveedorById(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            ResponseEntity::ok
        );
    }

    @Operation(summary = "Actualizar Proveedor")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Proveedor actualizado",
            content = @Content(schema = @Schema(implementation = ProveedorDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable UUID id, @RequestBody ProveedorRequest ProveedorRequest) {
        var command = UpdateProveedorCommand.builder()
            .id(id)
            .codigoProveedor(ProveedorRequest.getCodigoProveedor())
            .nombre(ProveedorRequest.getNombre())
            .build();

        var result = proveedorProcess.updateProveedor(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            ResponseEntity::ok
        );
    }

    @Operation(summary = "Eliminar Proveedor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Proveedor eliminado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable UUID id) {
        var command = DeleteProveedorCommand.builder()
            .id(id)
            .build();

        var result = proveedorProcess.deleteProveedor(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            Proveedor -> ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        );
    }
}
