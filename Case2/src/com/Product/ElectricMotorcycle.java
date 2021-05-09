package com.Product;

import java.util.Scanner;

public class ElectricMotorcycle extends Product{
    private String maxDistance;
    private String chargingTime;
    private String batteryType;
    private String wattage;
    public ElectricMotorcycle() {
    }

    public ElectricMotorcycle(String maxDistance, String chargingTime, String batteryType, String wattage) {
        this.maxDistance = maxDistance;
        this.chargingTime = chargingTime;
        this.batteryType = batteryType;
        this.wattage = wattage;
    }

    public ElectricMotorcycle(String productId, String mfg, String company, String color, String name, String price, String maxDistance, String chargingTime, String batteryType, String wattage) {
        super(productId, mfg, company, color, name, price);
        this.maxDistance = maxDistance;
        this.chargingTime = chargingTime;
        this.batteryType = batteryType;
        this.wattage = wattage;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(String chargingTime) {
        this.chargingTime = chargingTime;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }
}
