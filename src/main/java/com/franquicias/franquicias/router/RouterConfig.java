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

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Slf4j
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> route(FranchiseHandler franchiseHandler,
                                                BranchHandler branchHandler,
                                                ProductHandler productHandler) {
        return RouterFunctions
                .route(POST("/franchises"), franchiseHandler::addFranchise)
                .andRoute(PUT("/franchises/{id}/name"), franchiseHandler::updateFranchiseName)
                .andRoute(GET("/franchises/{id}"), franchiseHandler::getFranchiseById)
                .andRoute(POST("/branches"), branchHandler::addBranch)
                .andRoute(PUT("/branches/{id}/name"), branchHandler::updateBranchName)
                .andRoute(GET("/branches/{franchiseId}"), branchHandler::getBranchesByFranchiseId)
                .andRoute(POST("/products"), productHandler::addProduct)
                .andRoute(PUT("/products/{id}/stock"), productHandler::updateProductStock)
                .andRoute(DELETE("/products/{id}"), productHandler::deleteProduct)
                .andRoute(GET("/products/max-stock/{branchId}"), productHandler::getMaxStockProduct)
                .andRoute(PUT("/products/{id}/name"), productHandler::updateProductName);
    }
}
