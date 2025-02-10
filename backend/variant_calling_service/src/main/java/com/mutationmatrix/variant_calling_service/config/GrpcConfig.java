package com.mutationmatrix.variant_calling_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.devh.boot.grpc.server.config.GrpcServerProperties;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;

@Configuration
public class GrpcConfig {

    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer(GrpcServerProperties properties) {
        return serverBuilder -> serverBuilder.keepAliveTime(properties.getKeepAliveTime())
                .keepAliveTimeout(properties.getKeepAliveTimeout());
    }
}