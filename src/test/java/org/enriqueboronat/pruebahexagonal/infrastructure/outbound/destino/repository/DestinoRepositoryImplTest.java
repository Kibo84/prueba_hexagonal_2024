package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.repository;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.vo.CodigoDestinoVO;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.mapper.DestinoEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DestinoRepositoryImplTest {

    @Mock
    private DestinoRepositoryJpa destinoRepositoryJpa;

    @Mock
    private DestinoEntityMapper destinoEntityMapper;

    @InjectMocks
    private DestinoRepositoryImpl destinoRepository;

    @Test
    void searchDestinoById() {
        UUID id = UUID.randomUUID();
        DestinoEntity destinoEntity = new DestinoEntity();
        destinoEntity.setId(id);
        destinoEntity.setCodigo("testCodigo");
        destinoEntity.setDescripcion("testDescripcion");

        Destino destino = Destino.builder()
                .id(destinoEntity.getId())
                .codigo(CodigoDestinoVO.of(destinoEntity.getCodigo()))
                .descripcion(destinoEntity.getDescripcion())
                .build();

        when(destinoRepositoryJpa.findById(id)).thenReturn(Optional.of(destinoEntity));
        when(destinoEntityMapper.mapToDomain(destinoEntity)).thenReturn(destino);

        Optional<Destino> result = destinoRepository.searchDestinoById(id);

        assertTrue(result.isPresent());
        assertEquals(destino, result.get());

        verify(destinoRepositoryJpa, times(1)).findById(id);
        verify(destinoEntityMapper, times(1)).mapToDomain(destinoEntity);
    }
}