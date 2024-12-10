package com.franquicias.franquicias.repository;

import com.franquicias.franquicias.entity.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchRepository extends ReactiveCrudRepository<Branch, Long> {
    Mono<Branch> findByNameAndFranchiseId(String name, Long franchiseId);
}
