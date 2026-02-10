package com.example.SmartCM.DTOS;

import java.util.List;

public class inTransistDTO {
    private int agentid;
    private List<Integer> packagelist;

    public inTransistDTO() {
    }

    public inTransistDTO(int agentid, List<Integer> packagelist) {
        this.agentid = agentid;
        this.packagelist = packagelist;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public List<Integer> getPackagelist() {
        return packagelist;
    }

    public void setPackagelist(List<Integer> packagelist) {
        this.packagelist = packagelist;
    }
}
