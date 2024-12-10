package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Franchise;
import com.franquicias.franquicias.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Franchise> addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Mono<Franchise> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    public Mono<Void> deleteFranchise(Long id) {
        return franchiseRepository.deleteById(id);
    }

    public Mono<Franchise> updateFranchiseName(Long id, String newName) {
        return franchiseRepository.findById(id)
                .flatMap(franchise -> {
                    franchise.setName(newName);
                    return franchiseRepository.save(franchise);
                });
    }

    public Flux<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }
}
