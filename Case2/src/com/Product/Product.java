package com.Product;

import java.io.Serializable;

public abstract class Product implements Serializable{
    private String productId = "";
    private String mfg = "";
    private String company = "";
    private String color = "";
    private String name = "";
    private String importPrice;
    private String day;

    public Product() {

    }

    public Product(String productId, String mfg, String company, String color, String name, String importPrice) {
        this.productId = productId;
        this.mfg = mfg;
        this.company = company;
        this.color = color;
        this.name = name;
        this.importPrice = importPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImportPrice() {
        return importPrice;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getPrice(){
        return Double.parseDouble(getImportPrice())+Double.parseDouble(getImportPrice())*20/100;
    }

    public void setImportPrice(String importPrice) {
        this.importPrice = importPrice;
    }




}
