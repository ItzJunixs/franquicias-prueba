package com.franquicias.franquicias.repository;

import com.franquicias.franquicias.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findAllByBranchId(Long branchId);
}