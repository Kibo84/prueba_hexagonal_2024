package org.enriqueboronat.pruebahexagonal.application.destino.service;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DestinoService {
    List<Destino> searchDestinos();

    Optional<Destino> searchDestino(UUID id);

    Optional<Destino> createDestino(Destino destino);

    Optional<Destino> updateDestino(Destino destino);

    void deleteDestino(UUID id);
}
