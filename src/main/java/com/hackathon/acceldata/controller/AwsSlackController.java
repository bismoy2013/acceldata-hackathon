package com.hackathon.acceldata.controller;


import com.hackathon.acceldata.services.AwsServiceImpl;
import com.hackathon.acceldata.services.TskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/apis/slack-aws")
public class AwsSlackController {

    @Autowired
    private AwsServiceImpl awsService;
    @Autowired
    private TskScheduler tskScheduler;

    @GetMapping("/instances")
    public ResponseEntity<?> getAllInstanceInfos() {
        return ResponseEntity.ok().body(awsService.getAllInstanceStates());
    }

    @GetMapping("/trigger")
    public ResponseEntity<?> triggerSlack() {
        return ResponseEntity.ok().body(tskScheduler.triggerSlack());
    }

}
