package com.hackathon.acceldata.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.acceldata.constants.AccelDataConstants;
import com.hackathon.acceldata.model.AwsInfo;
import com.hackathon.acceldata.model.SlackTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Service
public class AwsServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(AwsServiceImpl.class);

    public List<AwsInfo> getAllInstanceStates() {

        try {
            logger.info("Initiating trigger for getAllInstanceStates method");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String testJson = restTemplate.exchange(AccelDataConstants.ALL_AWS_EC2_INSTANCE_URL, HttpMethod.GET, entity, String.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<AwsInfo> infos = mapper.readValue(testJson, new TypeReference<ArrayList<AwsInfo>>() {
            });

            StringBuilder sb = new StringBuilder();
            for (AwsInfo awsInfo : infos) {
                sb.append("Instance ID : ").append(awsInfo.getInstanceId()).append(" ").append("Instance Type : ").append(awsInfo.getInstanceType()).append(" ")
                        .append("State : ").append(awsInfo.getState()).append(" ").append("Cpu Utilisation : ").append(awsInfo.getCpuUtilization()).append("\n");
            }

            SlackTrigger st = new SlackTrigger();
            st.setText(sb.toString());
            HttpEntity<SlackTrigger> stEntity = new HttpEntity<SlackTrigger>(st, headers);
            restTemplate.exchange(AccelDataConstants.SLACK_WEBHOOK_URL, HttpMethod.POST, stEntity, String.class).getBody();
            logger.info("slack message sent successfully");
            return mapper.readValue(testJson, new TypeReference<List<AwsInfo>>() {
            });
        } catch (Exception e) {
            logger.error("[getAllInstanceStates] Error while fetching instance infos ", e);
            getAllInstanceStates();
        }
        return new ArrayList<>();
    }

}
