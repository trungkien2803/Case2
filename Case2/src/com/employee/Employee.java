package com.employee;

import java.io.Serializable;

public abstract class Employee implements Serializable{
    private String id;
    private String fullName;
    private String age;
    private String address;
    private String phoneNumber;
    private String salary;
    private String bonus;
    private String fine;

    public Employee() {

    }

    public Employee(String id, String fullName, String age, String address, String phoneNumber, String salary, String bonus, String fine) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.bonus = bonus;
        this.fine = fine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
    public double getRealWages() {
        return Double.parseDouble(getSalary()) + Double.parseDouble(getBonus()) - Double.parseDouble(getFine());
    }

}
