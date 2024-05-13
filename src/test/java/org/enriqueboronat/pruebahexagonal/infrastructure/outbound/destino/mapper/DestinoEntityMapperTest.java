package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.mapper;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DestinoEntityMapperTest {

    private final DestinoEntityMapper mapper = new DestinoEntityMapper();

    @Test
    void mapToEntity() {
        UUID id = UUID.randomUUID();
        String codigo = "1";
        String descripcion = "testDescripcion";

        Destino destino = Destino.builder()
                .id(id)
                .codigo(CodigoDestinoVO.of(codigo))
                .descripcion(descripcion)
                .build();

        DestinoEntity destinoEntity = mapper.mapToEntity(destino);

        assertThat(destinoEntity.getId()).isEqualTo(id);
        assertThat(destinoEntity.getCodigo()).isEqualTo(codigo);
        assertThat(destinoEntity.getDescripcion()).isEqualTo(descripcion);
    }

    @Test
    void mapToDomain() {
        UUID id = UUID.randomUUID();
        String codigo = "1";
        String descripcion = "testDescripcion";

        DestinoEntity destinoEntity = DestinoEntity.builder()
                .id(id)
                .codigo(codigo)
                .descripcion(descripcion)
                .build();

        Destino destino = mapper.mapToDomain(destinoEntity);

        assertThat(destino.getId()).isEqualTo(id);
        assertThat(destino.getCodigo().getValue()).isEqualTo(codigo);
        assertThat(destino.getDescripcion()).isEqualTo(descripcion);
    }
}