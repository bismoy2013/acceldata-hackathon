package com.hackathon.acceldata.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TskScheduler {

    private static Logger log = LoggerFactory.getLogger(TskScheduler.class);

    @Scheduled(cron = "0 0 0/5 * * ?")
    public String triggerSlack() {
        RestTemplate client = new RestTemplate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String inputUrl = "http://13.234.59.87:8080/v1/apis/slack-aws/instances";
        HttpHeaders headers = getHeaders();
        client.exchange(inputUrl, HttpMethod.GET, new HttpEntity<byte[]>(headers),
                String.class);
        log.info("Triggered time now : {}", dateFormat.format(new Date()));
        return "success";
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }
}
