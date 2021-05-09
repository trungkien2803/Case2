package com.Product;




import java.io.Serializable;


public abstract class Product implements Serializable{
    private String productId = "";
    private String mfg = "";
    private String company = "";
    private String color = "";
    private String name = "";
    private String price;


    public Product() {

    }

    public Product(String productId, String mfg, String company, String color, String name, String price) {
        this.productId = productId;
        this.mfg = mfg;
        this.company = company;
        this.color = color;
        this.name = name;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




}
