package com.employee;

public class Technicians extends Employee{
    public Technicians() {
    }

    public Technicians(String id, String fullName, String age, String address, String phoneNumber, String salary, String bonus, String fine) {
        super(id, fullName, age, address, phoneNumber, salary, bonus, fine);
    }
//    @Override
//    public void showInfo() {
//        super.showInfo();
//        System.out.printf("%s\n","Kĩ thuật viên");
//    }
}
