package org.enriqueboronat.pruebahexagonal.application.destino.service;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.repository.DestinoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DestinoServiceImplTest {

    @Mock
    private DestinoRepository destinoRepository;

    @InjectMocks
    private DestinoServiceImpl destinoService;

    @Test
    public void testSearchDestinoByCode() {
        UUID id = UUID.randomUUID();
        Destino destino = Destino.builder().build();
        when(destinoRepository.searchDestinoById(any())).thenReturn(Optional.of(destino));

        Optional<Destino> result = destinoService.searchDestino(id);

        assertEquals(Optional.of(destino), result);
    }
}