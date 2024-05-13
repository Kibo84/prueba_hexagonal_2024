package org.enriqueboronat.pruebahexagonal.application.ean.service;

import org.enriqueboronat.pruebahexagonal.domain.ean.entity.Ean;

import java.util.Optional;

public interface EanService {
    Optional<Ean> searchEanInfo(String code);
}
