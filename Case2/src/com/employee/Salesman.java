package com.employee;

public class Salesman extends Employee{
    private double salesPerformance = 0;
    public Salesman() {
    }

    public Salesman(String id, String fullName, String age, String address, String phoneNumber, String salary, String bonus, String fine) {
        super(id, fullName, age, address, phoneNumber, salary, bonus, fine);
    }

    public double getSalesPerformance() {
        return salesPerformance;
    }

    public void setSalesPerformance() {
        this.salesPerformance = getSalesPerformance() + 1;
    }
    public void reduceSalesPerformance(){
        this.salesPerformance = getSalesPerformance() - 1;
    }
}
