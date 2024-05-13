package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.proveedor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedor")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;

    @Column(length = 7)
    private String codigoProveedor;

}