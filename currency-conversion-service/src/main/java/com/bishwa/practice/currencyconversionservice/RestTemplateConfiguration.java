package com.bishwa.practice.currencyconversionservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author bishwa
 * Date: 03/09/2024
 * Time: 1:56 PM
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
