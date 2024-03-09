package com.bishwa.practice.limitsservice.controller;

import com.bishwa.practice.limitsservice.bean.Limits;
import com.bishwa.practice.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bishwa
 * Date: 03/07/2024
 * Time: 11:49 PM
 */
@RestController
public class LimitsController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
