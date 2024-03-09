package com.bishwa.practice.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author bishwa
 * Date: 03/08/2024
 * Time: 5:45 PM
 */
@Component
public class LoggingFilter implements GlobalFilter {
    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Request received : {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
