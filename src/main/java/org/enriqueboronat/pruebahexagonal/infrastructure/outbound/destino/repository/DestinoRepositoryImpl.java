package org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.repository;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.repository.DestinoRepository;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.entity.DestinoEntity;
import org.enriqueboronat.pruebahexagonal.infrastructure.outbound.destino.mapper.DestinoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DestinoRepositoryImpl implements DestinoRepository {
    private final DestinoRepositoryJpa destinoRepositoryJpa;
    private final DestinoEntityMapper destinoEntityMapper;

    @Override
    public List<Destino> searchDestinos() {
        return destinoRepositoryJpa.findAll().stream().map(destinoEntityMapper::mapToDomain).toList();
    }

    @Override
    public Optional<Destino> searchDestinoById(UUID id) {
        return destinoRepositoryJpa.findById(id).map(destinoEntityMapper::mapToDomain);
    }

    @Override
    public Optional<Destino> createDestino(Destino destino) {
        DestinoEntity destinoEntity = destinoEntityMapper.mapToEntity(destino);
        try {
            DestinoEntity savedDestinoEntity = destinoRepositoryJpa.save(destinoEntity);
            return Optional.of(destinoEntityMapper.mapToDomain(savedDestinoEntity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Destino> updateDestino(Destino destino) {
        return createDestino(destino);
    }

    @Override
    public void deleteDestino(UUID id) {
        destinoRepositoryJpa.deleteById(id);
    }
}
