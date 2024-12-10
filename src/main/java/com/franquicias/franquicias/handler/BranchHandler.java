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
@RequiredArgsConstructor
@Slf4j
public class BranchHandler {
    private final BranchService branchService;

    public Mono<ServerResponse> addBranch(ServerRequest request) {
        Long franchiseId = Long.parseLong(request.pathVariable("franchiseId"));
        return request.bodyToMono(Branch.class)
                .flatMap(branch -> branchService.addBranchToFranchise(franchiseId, branch))
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> updateBranchName(ServerRequest request) {
        Long branchId = Long.parseLong(request.pathVariable("branchId"));

        return request.bodyToMono(String.class)
                .flatMap(newName -> branchService.updateBranchName(branchId, newName))
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

}
