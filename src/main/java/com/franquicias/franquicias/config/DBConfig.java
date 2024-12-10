package com.franquicias.franquicias.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

public class DBConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                "r2dbc:mysql://admin:Tonyyzoe23972@franquicias-db.c90kykg8ycbp.us-east-2.rds.amazonaws.com:3306/franquicias_database"
        );
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
