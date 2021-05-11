package com.employee;

public class Manager extends Employee{
    private String ACCOUNT_REGEX = "[A-Z1-9]*";
    private String PASSWORD_REGEX = "[A-Z]*[a-z]*\\d*]";
    private String account;
    private String password;

    public Manager() {
    }

    public Manager(String id, String fullName, String age, String address, String phoneNumber, String salary, String bonus, String fine) {
        super(id, fullName, age, address, phoneNumber, salary, bonus, fine);
    }
}
