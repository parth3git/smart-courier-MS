package com.example.SmartCM.DTOS;

import java.util.List;

public class agentInfoDTO {
    private int agentid;
    private String agentname;
    private List<Integer> assignedpackage;
    private List<Integer> deliveredpackage;
    private List<Integer> intransistpackage;

    public agentInfoDTO() {
    }

    public agentInfoDTO(int agentid, List<Integer> deliveredpackage, List<Integer> assignedpackage,
                        String agentname, List<Integer> intransistpackage) {
        this.agentid = agentid;
        this.deliveredpackage = deliveredpackage;
        this.assignedpackage = assignedpackage;
        this.agentname = agentname;
        this.intransistpackage = intransistpackage;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public List<Integer> getIntransistpackage() {
        return intransistpackage;
    }

    public void setIntransistpackage(List<Integer> intransistpackage) {
        this.intransistpackage = intransistpackage;
    }

    public List<Integer> getDeliveredpackage() {
        return deliveredpackage;
    }

    public void setDeliveredpackage(List<Integer> deliveredpackage) {
        this.deliveredpackage = deliveredpackage;
    }

    public List<Integer> getAssignedpackage() {
        return assignedpackage;
    }

    public void setAssignedpackage(List<Integer> assignedpackage) {
        this.assignedpackage = assignedpackage;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }
}
