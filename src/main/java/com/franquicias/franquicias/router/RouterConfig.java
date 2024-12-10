package com.franquicias.franquicias.router;

import com.franquicias.franquicias.handler.BranchHandler;
import com.franquicias.franquicias.handler.FranchiseHandler;
import com.franquicias.franquicias.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(
            FranchiseHandler franchiseHandler,
            BranchHandler branchHandler,
            ProductHandler productHandler) {
        return RouterFunctions.route()
                .POST("/api/franchises", franchiseHandler::addFranchise)
                .POST("/api/franchises/{franchiseId}/branches", branchHandler::addBranch)
                .POST("/api/franchises/{franchiseId}/branches/{branchName}/products", productHandler::addProduct)
                .DELETE("/api/products/{productId}", productHandler::deleteProduct)
                .PUT("/api/products/{productId}/stock", productHandler::updateStock)
                .GET("/api/branches/{branchId}/max-stock-product", productHandler::getMaxStockProduct)
                .PUT("/api/franchises/{franchiseId}/name", franchiseHandler::updateFranchiseName)
                .PUT("/api/branches/{branchId}/name", branchHandler::updateBranchName)
                .PUT("/api/products/{productId}/name", productHandler::updateProductName)
                .build();
    }
}
