package com.SalesSlip;

import java.io.Serializable;

public class SalesSlip implements Serializable {
    private String salesSlipId;
    private String dateSale;
    private String customerName;
    private String salesmanId;
    private String salesman;
    private String productId;
    private String productName;
    private String price;
    private String tax;
    private String discount;
    private double totalPayment;

    public SalesSlip() {
    }

    public SalesSlip(String salesSlipId, String dateSale, String customerName, String salesmanId, String salesman, String productId, String productName, String price, String tax, String discount) {
        this.salesSlipId = salesSlipId;
        this.dateSale = dateSale;
        this.customerName = customerName;
        this.salesmanId = salesmanId;
        this.salesman = salesman;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
    }

    public String getSalesSlipId() {
        return salesSlipId;
    }

    public void setSalesSlipId(String salesSlipId) {
        this.salesSlipId = salesSlipId;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale(String dateSale) {
        this.dateSale = dateSale;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDiscount() {
        return discount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Double getTotalPayment() {
        totalPayment = Double.parseDouble(price) + (Double.parseDouble(price) * (Double.parseDouble(tax)/100)) -Double.parseDouble(price) * (Double.parseDouble(discount)/100);
        return totalPayment;
    }
}
