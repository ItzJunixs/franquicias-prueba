package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Branch;
import com.franquicias.franquicias.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Mono<Branch> addBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Mono<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }

    public Mono<Void> deleteBranch(Long id) {
        return branchRepository.deleteById(id);
    }

    public Mono<Branch> updateBranchName(Long id, String newName) {
        return branchRepository.findById(id)
                .flatMap(branch -> {
                    branch.setName(newName);
                    return branchRepository.save(branch);
                });
    }

    public Flux<Branch> getBranchesByFranchiseId(Long franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId);
    }
}
