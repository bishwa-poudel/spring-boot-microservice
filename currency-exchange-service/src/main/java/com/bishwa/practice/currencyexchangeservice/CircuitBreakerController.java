package com.bishwa.practice.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author bishwa
 * Date: 03/08/2024
 * Time: 5:56 PM
 */
@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    public String sampleApi() {
        logger.info("Sample API call received");
        var response = new RestTemplate().getForEntity("http://localhost:1234/some-dummy-url", String.class);

        return response.getBody();
    }

    public String hardCodedResponse(Exception exception) {
        return "fallback-response: " + exception.getMessage();
    }

    @GetMapping("/circuit-api")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    public String circuitApi() {
        logger.info("Circuit API call received");
        var response = new RestTemplate().getForEntity("http://localhost:1234/some-dummy-url", String.class);

        return response.getBody();
    }

    @GetMapping("/rateLimit-api")
    @RateLimiter(name = "sample-api")
    public String rateLimitApi() {
        logger.info("RateLimit API call received");
        return "Success";
    }

    @GetMapping("/bulkHead-api")
    @Bulkhead(name = "sample-api")
    public String bulkHeadApi() {
        logger.info("BulkHead API call received");
        return "Success";
    }

}
