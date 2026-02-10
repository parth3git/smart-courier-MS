package com.example.SmartCM.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageid;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders orders;

    @Enumerated(EnumType.STRING)
    private deliveryStatus deliveryStatus;

    public Packages() {
    }

    public Packages(int packageid, Orders orders, deliveryStatus deliveryStatus) {
        this.packageid = packageid;
        this.orders = orders;
        this.deliveryStatus = deliveryStatus;
    }

    public int getPackageid() {
        return packageid;
    }

    public void setPackageid(int packageid) {
        this.packageid = packageid;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public deliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(deliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    public void setDefaultStatus() {
        if (deliveryStatus == null) {
            deliveryStatus = com.example.SmartCM.Model.deliveryStatus.PLACED;
        }
    }
}
