package com.example.SmartCM.DTOS;

import com.example.SmartCM.Model.deliveryStatus;

public class PackageStatusDTO {
    private int packageid;
    private double latitude;
    private double longitude;
    private deliveryStatus status;

    public int getPackageid() {
        return packageid;
    }

    public void setPackageid(int packageid) {
        this.packageid = packageid;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public deliveryStatus getStatus() {
        return status;
    }

    public void setStatus(deliveryStatus status) {
        this.status = status;
    }
}
