package com.example.SmartCM.Users;

import com.example.SmartCM.Model.DeliveryAssignments;
import com.example.SmartCM.Model.deliveryStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class DeliveryAgents {

    @Id
    private int agentId;

    private String name;

    @OneToMany(mappedBy = "agent")
    private List<DeliveryAssignments> assignments;

    public DeliveryAgents() {
    }

    public DeliveryAgents(int agentId, List<DeliveryAssignments> assignments, String name) {
        this.agentId = agentId;
        this.assignments = assignments;
        this.name = name;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeliveryAssignments> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DeliveryAssignments> assignments) {
        this.assignments = assignments;
    }
}
