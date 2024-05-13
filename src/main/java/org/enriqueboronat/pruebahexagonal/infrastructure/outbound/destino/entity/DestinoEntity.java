package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "destino")
public class DestinoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String codigo;

    private String descripcion;
}