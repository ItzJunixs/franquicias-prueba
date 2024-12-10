package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Product;
import com.franquicias.franquicias.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final BranchService branchService;

    public Mono<Product> addProductToBranch(Long franchiseId, String branchName, Product product) {
        return branchService.findBranchByName(franchiseId, branchName)
                .flatMap(branch -> {
                    product.setBranch(branch);
                    return productRepository.save(product);
                });
    }

    public Mono<Void> deleteProduct(Long productId) {
        return productRepository.deleteById(productId);
    }

    public Mono<Product> updateStock(Long productId, int newStock) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setStock(newStock);
                    return productRepository.save(product);
                });
    }

    public Mono<Product> findMaxStockProductByBranch(Long branchId) {
        return productRepository.findAllByBranchId(branchId)
                .sort(Comparator.comparingInt(Product::getStock).reversed())
                .next();
    }

    public Mono<Product> updateProductName(Long productId, String newName) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setName(newName);
                    return productRepository.save(product);
                });
    }
}
