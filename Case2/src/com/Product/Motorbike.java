package com.Product;

import java.util.Scanner;

public class Motorbike extends Product {

    private String cylinderCapacity;
    private String maxSpeed;
    private String fuelConsumption;
    private String fuelTankVolume;
    public Motorbike() {

    }

    public Motorbike(String cylinderCapacity, String maxSpeed, String fuelConsumption, String fuelTankVolume) {
        this.cylinderCapacity = cylinderCapacity;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankVolume = fuelTankVolume;
    }

    public Motorbike(String productId, String mfg, String company, String color, String name, String price, String cylinderCapacity, String maxSpeed, String fuelConsumption, String fuelTankVolume) {
        super(productId, mfg, company, color, name, price);
        this.cylinderCapacity = cylinderCapacity;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankVolume = fuelTankVolume;
    }

    public String getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(String cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getFuelTankVolume() {
        return fuelTankVolume;
    }

    public void setFuelTankVolume(String fuelTankVolume) {
        this.fuelTankVolume = fuelTankVolume;
    }
}
