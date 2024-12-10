package com.franquicias.franquicias.handler;

import com.franquicias.franquicias.entity.Product;
import com.franquicias.franquicias.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductHandler {
    private final ProductService productService;

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        Long franchiseId = Long.parseLong(request.pathVariable("franchiseId"));
        String branchName = request.pathVariable("branchName");

        return request.bodyToMono(Product.class)
                .flatMap(product -> productService.addProductToBranch(franchiseId, branchName, product))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        Long productId = Long.parseLong(request.pathVariable("productId"));

        return productService.deleteProduct(productId)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateStock(ServerRequest request) {
        Long productId = Long.parseLong(request.pathVariable("productId"));
        return request.bodyToMono(Integer.class)
                .flatMap(newStock -> productService.updateStock(productId, newStock))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> getMaxStockProduct(ServerRequest request) {
        Long branchId = Long.parseLong(request.pathVariable("branchId"));

        return productService.findMaxStockProductByBranch(branchId)
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateProductName(ServerRequest request) {
        Long productId = Long.parseLong(request.pathVariable("productId"));

        return request.bodyToMono(String.class)
                .flatMap(newName -> productService.updateProductName(productId, newName))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }
}
