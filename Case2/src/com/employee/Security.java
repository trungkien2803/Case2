package com.employee;

public class Security extends Employee{
    public Security() {
    }

    public Security(String id, String fullName, String age, String address, String phoneNumber, String salary, String bonus, String fine) {
        super(id, fullName, age, address, phoneNumber, salary, bonus, fine);
    }
//    @Override
//    public void showInfo() {
//        super.showInfo();
//        System.out.printf("%s\n","Nhân viên bảo vệ");
//    }
}
