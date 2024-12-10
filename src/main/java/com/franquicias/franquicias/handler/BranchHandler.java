package com.franquicias.franquicias.handler;

import com.franquicias.franquicias.entity.Branch;
import com.franquicias.franquicias.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BranchHandler {
    private final BranchService branchService;

    public BranchHandler(BranchService branchService) {
        this.branchService = branchService;
    }

    public Mono<ServerResponse> addBranch(ServerRequest request) {
        return request.bodyToMono(Branch.class)
                .flatMap(branchService::addBranch)
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> updateBranchName(ServerRequest request) {
        Long branchId = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(String.class)
                .flatMap(newName -> branchService.updateBranchName(branchId, newName))
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> getBranchesByFranchiseId(ServerRequest request) {
        Long franchiseId = Long.valueOf(request.pathVariable("franchiseId"));
        return branchService.getBranchesByFranchiseId(franchiseId)
                .collectList()
                .flatMap(branches -> ServerResponse.ok().bodyValue(branches));
    }
}
