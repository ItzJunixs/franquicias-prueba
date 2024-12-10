package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Franchise;
import com.franquicias.franquicias.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Mono<Franchise> findFranchiseById(Long id) {
        return franchiseRepository.findById(id);
    }

    public Mono<Franchise> updateFranchiseName(Long franchiseId, String newName) {
        return franchiseRepository.findById(franchiseId)
                .flatMap(franchise -> {
                    franchise.setName(newName);
                    return franchiseRepository.save(franchise);
                });
    }
}
