package com.bishwa.practice.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author bishwa
 * Date: 03/08/2024
 * Time: 4:29 PM
 */
// k8s automatically create CURRENCY_EXCHANGE_SERVICE_HOST environment variable whenever the service is up, and allows service discovery through this env variable. But better to use custom env variable because what if a service is down at a particular moment. No env variable is found.
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")

public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieverExchangeValue(@PathVariable String from, @PathVariable String to);
}
