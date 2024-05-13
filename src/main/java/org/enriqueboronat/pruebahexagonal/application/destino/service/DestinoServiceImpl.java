package org.enriqueboronat.pruebahexagonal.application.destino.service;

import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.domain.destino.entity.Destino;
import org.enriqueboronat.pruebahexagonal.domain.destino.repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DestinoServiceImpl implements DestinoService {
    private final DestinoRepository destinoRepository;

    @Override
    public List<Destino> searchDestinos() {
        return destinoRepository.searchDestinos();
    }

    @Override
    public Optional<Destino> searchDestino(UUID id) {
        return destinoRepository.searchDestinoById(id);
    }

    @Override
    public Optional<Destino> createDestino(Destino destino) {
        return destinoRepository.createDestino(destino);
    }

    @Override
    public Optional<Destino> updateDestino(Destino destino) {
        return destinoRepository.updateDestino(destino);
    }

    @Override
    public void deleteDestino(UUID id) {
        destinoRepository.deleteDestino(id);
    }
}
