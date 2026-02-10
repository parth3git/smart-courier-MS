package com.example.SmartCM.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    private int customerid;

    @Enumerated(EnumType.STRING)
    private deliveryStatus deliveryStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Packages> packages;

    public Orders() {
    }

    public Orders(int orderid, List<Packages> packages, deliveryStatus deliveryStatus, int customerid) {
        this.orderid = orderid;
        this.packages = packages;
        this.deliveryStatus = deliveryStatus;
        this.customerid = customerid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public List<Packages> getPackages() {
        return packages;
    }

    public void setPackages(List<Packages> packages) {
        this.packages = packages;
    }

    public deliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(deliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    public void setDeliveryStatus() {
        if (deliveryStatus == null) {
            deliveryStatus = com.example.SmartCM.Model.deliveryStatus.PLACED;
        }
    }


}
