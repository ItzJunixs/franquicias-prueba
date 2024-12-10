package com.franquicias.franquicias.repository;

import com.franquicias.franquicias.entity.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {

}
