package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class DestinoEntityTest {

    @Test
    void testDestinoEntity() {
        UUID id = UUID.randomUUID();
        String codigo = "testCodigo";
        String descripcion = "testDescripcion";

        DestinoEntity destinoEntity = DestinoEntity.builder()
                .id(id)
                .codigo(codigo)
                .descripcion(descripcion)
                .build();

        assertThat(destinoEntity.getId()).isEqualTo(id);
        assertThat(destinoEntity.getCodigo()).isEqualTo(codigo);
        assertThat(destinoEntity.getDescripcion()).isEqualTo(descripcion);
    }
}