package com.bishwa.practice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author bishwa
 * Date: 03/08/2024
 * Time: 1:50 PM
 */
@RestController
public class CurrencyExchangeController {
    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        var currencyExchange = repository.findByFromAndTo(from, to);

        if(Objects.isNull(currencyExchange)) {
            throw new RuntimeException("Unable to find data for " + from + " to" + to);
        }

        final String port = environment.getProperty("local.server.port");
        final var host = environment.getProperty("HOSTNAME");
        final var version = "v12";

        currencyExchange.setEnvironment(port + " " + version + " " + host);
        return currencyExchange;
    }

}
