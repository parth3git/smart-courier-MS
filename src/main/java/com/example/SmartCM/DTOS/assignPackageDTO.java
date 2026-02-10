package com.example.SmartCM.DTOS;

import java.util.List;

public class assignPackageDTO {
    private int agentid;
    private List<Integer> packageid;

    public assignPackageDTO() {
    }

    public assignPackageDTO(int agentid, List<Integer> packageid) {
        this.agentid = agentid;
        this.packageid = packageid;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public List<Integer> getPackageid() {
        return packageid;
    }

    public void setPackageid(List<Integer> packageid) {
        this.packageid = packageid;
    }
}
