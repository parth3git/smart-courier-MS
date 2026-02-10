package com.example.SmartCM.Model;

import com.example.SmartCM.Users.DeliveryAgents;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DeliveryAssignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;

    @ManyToOne
    @JoinColumn(name = "agentid")
    @JsonIgnore
    private DeliveryAgents agent;

    @ManyToOne
    @JoinColumn(name = "packageid")
    private Packages pkg;

    @Enumerated(EnumType.STRING)
    private deliveryStatus deliveryStatus;

    private LocalDateTime assignedTime;
    private LocalDateTime deliveredTime;

    public DeliveryAssignments() {
    }

    public DeliveryAssignments(int assignmentId, DeliveryAgents agent, Packages pkg, deliveryStatus deliveryStatus,
                               LocalDateTime assignedTime, LocalDateTime deliveredTime) {
        this.assignmentId = assignmentId;
        this.agent = agent;
        this.pkg = pkg;
        this.deliveryStatus = deliveryStatus;
        this.assignedTime = assignedTime;
        this.deliveredTime = deliveredTime;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public DeliveryAgents getAgent() {
        return agent;
    }

    public void setAgent(DeliveryAgents agent) {
        this.agent = agent;
    }

    public Packages getPkg() {
        return pkg;
    }

    public void setPkg(Packages pkg) {
        this.pkg = pkg;
    }

    public deliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(deliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(LocalDateTime assignedTime) {
        this.assignedTime = assignedTime;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    @PrePersist
    public void setDefaults() {
        assignedTime = LocalDateTime.now();
        if (deliveryStatus == null) {
            deliveryStatus = com.example.SmartCM.Model.deliveryStatus.ASSIGNED;
        }
    }
}
