package org.enriqueboronat.pruebahexagonal.domain.destino.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Destino {
    private UUID id;
    private CodigoDestinoVO codigo;
    private String descripcion;

    public static Optional<Destino> of(
            UUID id,
            CodigoDestinoVO codigo,
            String descripcion
    ) {
        if (Objects.isNull(id)
            || Objects.isNull(codigo)
            || Objects.isNull(descripcion)
        ) {
            return Optional.empty();
        }

        return Optional.of(
            Destino.builder()
                .id(id)
                .codigo(codigo)
                .descripcion(descripcion)
                .build()
        );
    }
}
