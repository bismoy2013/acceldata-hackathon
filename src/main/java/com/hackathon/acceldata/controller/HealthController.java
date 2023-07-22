package com.hackathon.acceldata.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HealthController {

    private static Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/health")
    public String checkHealth() {
        logger.info("Health API is being invoked");
        return "Acceldata Hackathon";
    }
}
