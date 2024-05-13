package org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.application.producto.command.CreateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.DeleteProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.SearchProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.command.UpdateProductoCommand;
import org.enriqueboronat.pruebahexagonal.application.producto.process.ProductoProcess;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.entity.ProductoDto;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.entity.ProductoRequest;
import org.enriqueboronat.pruebahexagonal.infrastructure.inbound.producto.api.mapper.ProductoApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoApiController {
    private final ProductoProcess productoProcess;
    private final ProductoApiMapper productoApiMapper;

    @Operation(summary = "Crear Producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        var result = productoProcess.searchProductos().stream().map(productoApiMapper::mapToDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Crear Producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductoRequest productoRequest) {
        var command = CreateProductoCommand.builder()
            .id(productoRequest.getId())
            .codigoProducto(productoRequest.getCodigoProducto())
            .nombre(productoRequest.getNombre())
            .build();

        var result = productoProcess.createProducto(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            Producto -> ResponseEntity.status(HttpStatus.CREATED).body(Producto)
        );
    }

    @Operation(summary = "Obtener Producto")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto obtenido",
            content = @Content(schema = @Schema(implementation = ProductoDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable UUID id) {
        var command = SearchProductoCommand.builder()
            .id(id)
            .build();

        var result = productoProcess.searchProductoById(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            ResponseEntity::ok
        );
    }

    @Operation(summary = "Actualizar Producto")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado",
            content = @Content(schema = @Schema(implementation = ProductoDto.class))
        ),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable UUID id, @RequestBody ProductoRequest ProductoRequest) {
        var command = UpdateProductoCommand.builder()
            .id(id)
            .codigoProducto(ProductoRequest.getCodigoProducto())
            .nombre(ProductoRequest.getNombre())
            .build();

        var result = productoProcess.updateProducto(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            ResponseEntity::ok
        );
    }

    @Operation(summary = "Eliminar Producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error de servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable UUID id) {
        var command = DeleteProductoCommand.builder()
            .id(id)
            .build();

        var result = productoProcess.deleteProducto(command);

        return result.fold(
            error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage()),
            Producto -> ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        );
    }
}
