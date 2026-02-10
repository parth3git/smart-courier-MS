package com.example.SmartCM.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packagelocationid;

    @ManyToOne
    @JoinColumn(name = "packageid")
    private Packages packages;

    private double latitude;

    private double longitude;

    private LocalDateTime updatedAt;

    public Locations(int packagelocationid, LocalDateTime updatedAt, double longitude, double latitude,
                     Packages packages) {
        this.packagelocationid = packagelocationid;
        this.updatedAt = updatedAt;
        this.longitude = longitude;
        this.latitude = latitude;
        this.packages = packages;
    }

    public Locations() {
    }

    public int getPackagelocationid() {
        return packagelocationid;
    }

    public void setPackagelocationid(int packagelocationid) {
        this.packagelocationid = packagelocationid;
    }

    public Packages getPackages() {
        return packages;
    }

    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void setTime() {
        this.updatedAt = LocalDateTime.now();
    }
}
