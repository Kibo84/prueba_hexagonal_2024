package org.enriqueboronat.pruebahexagonal.domain.destino.repository;

import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DestinoRepository {
    List<Destino> searchDestinos();

    Optional<Destino> searchDestinoById(UUID id);

    Optional<Destino> createDestino(Destino destino);

    Optional<Destino> updateDestino(Destino destino);

    void deleteDestino(UUID id);
}
