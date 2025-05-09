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
@Slf4j
public class ProductHandler {
    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        return request.bodyToMono(Product.class)
                .flatMap(productService::addProduct)
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> updateProductStock(ServerRequest request) {
        Long productId = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(Long.class)
                .flatMap(newStock -> productService.updateProductStock(productId, newStock))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        Long productId = Long.valueOf(request.pathVariable("id"));
        return productService.deleteProduct(productId)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getMaxStockProduct(ServerRequest request) {
        Long branchId = Long.valueOf(request.pathVariable("branchId"));
        return productService.getMaxStockProduct(branchId)
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateProductName(ServerRequest request) {
        Long productId = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(String.class)
                .flatMap(newName -> productService.updateProductName(productId, newName))
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
