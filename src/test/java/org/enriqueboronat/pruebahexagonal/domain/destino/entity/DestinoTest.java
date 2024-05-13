package org.enriqueboronat.pruebahexagonal.domain.destino.entity;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class DestinoTest {
    private static final UUID VALID_UUID = UUID.randomUUID();
    private static final CodigoDestinoVO VALID_CODIGO = CodigoDestinoVO.of("3");
    private static final String VALID_DESCRIPCION = "Destino Test";

    @Test
    void testBuilder() {
        Destino destino = Destino.builder()
                                .id(VALID_UUID)
                                .codigo(VALID_CODIGO)
                                .descripcion(VALID_DESCRIPCION)
                                .build();

        assertEquals(VALID_UUID, destino.getId());
        assertEquals(VALID_CODIGO, destino.getCodigo());
        assertEquals(VALID_DESCRIPCION, destino.getDescripcion());
    }

    @Test
    void testOfMethod() {
        Optional<Destino> destinoOptional = Destino.of(VALID_UUID, VALID_CODIGO, VALID_DESCRIPCION);

        assertTrue(destinoOptional.isPresent());

        Destino destino = destinoOptional.get();

        assertEquals(VALID_UUID, destino.getId());
        assertEquals(VALID_CODIGO, destino.getCodigo());
        assertEquals(VALID_DESCRIPCION, destino.getDescripcion());
    }

    @Test
    void testOfMethodWithNullValues() {
        assertFalse(Destino.of(null, VALID_CODIGO, VALID_DESCRIPCION).isPresent());
        assertFalse(Destino.of(VALID_UUID, null, VALID_DESCRIPCION).isPresent());
        assertFalse(Destino.of(VALID_UUID, VALID_CODIGO, null).isPresent());
    }
}