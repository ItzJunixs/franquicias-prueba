package com.franquicias.franquicias.service;

import com.franquicias.franquicias.entity.Product;
import com.franquicias.franquicias.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> addProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> updateProductStock(Long productId, Long newStock) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setStock(newStock);
                    return productRepository.save(product);
                });
    }

    public Flux<Product> getProductsByBranchId(Long branchId) {
        return productRepository.findByBranchId(branchId);
    }

    public Mono<Product> getMaxStockProduct(Long branchId) {
        return productRepository.findByBranchId(branchId)
                .sort((p1, p2) -> Long.compare(p2.getStock(), p1.getStock()))
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
