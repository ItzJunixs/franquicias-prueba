package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Branch;
import com.franquicias.franquicias.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {
    private final BranchRepository branchRepository;
    private final FranchiseService franchiseService;

    public Mono<Branch> addBranchToFranchise(Long franchiseId, Branch branch) {
        return franchiseService.findFranchiseById(franchiseId)
                .flatMap(franchise -> {
                    branch.setFranchise(franchise);
                    return branchRepository.save(branch);
                });
    }

    public Mono<Branch> updateBranchName(Long branchId, String newName) {
        return branchRepository.findById(branchId)
                .flatMap(branch -> {
                    branch.setName(newName);
                    return branchRepository.save(branch);
                });
    }

    public Mono<Branch> findBranchByName(Long franchiseId, String branchName) {
        return branchRepository.findByNameAndFranchiseId(branchName, franchiseId);
    }
}
