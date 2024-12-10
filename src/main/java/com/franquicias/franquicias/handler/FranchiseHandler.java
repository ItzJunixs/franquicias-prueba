package com.franquicias.franquicias.handler;

import com.franquicias.franquicias.entity.Franchise;
import com.franquicias.franquicias.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class FranchiseHandler {
    private final FranchiseService franchiseService;

    public Mono<ServerResponse> addFranchise(ServerRequest request) {
        return request.bodyToMono(Franchise.class)
                .flatMap(franchiseService::addFranchise)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> updateFranchiseName(ServerRequest request) {
        Long franchiseId = Long.parseLong(request.pathVariable("franchiseId"));

        return request.bodyToMono(String.class)
                .flatMap(newName -> franchiseService.updateFranchiseName(franchiseId, newName))
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }
}
