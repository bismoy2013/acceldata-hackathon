package com.hackathon.acceldata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AwsInfo {
    private String instanceId;
    private String instanceType;
    private String state;
    private String cpuUtilization;

    public AwsInfo() {
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(String cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }
}
