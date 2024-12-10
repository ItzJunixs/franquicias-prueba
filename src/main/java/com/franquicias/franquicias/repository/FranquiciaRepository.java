package com.franquicias.franquicias.repository;

import com.franquicias.franquicias.entity.Franquicia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<Franquicia, Long> {

}
