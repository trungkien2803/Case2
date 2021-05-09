package com.company;

import com.employee.EmployeeManagement;
import com.employee.Manager;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static final String ENTER_ACCOUNT = "Nhập tài khoản";
    public static final String ENTER_PASSWORD = "Nhập mật khẩu";

    public static void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.printf("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final String EDIT_PRINTF = "%-70s%-30s%s\n";
        EmployeeManagement employeeManagement = new EmployeeManagement();
        MainSalesSlip mainSalesSlip = new MainSalesSlip();
        MainEmployee mainEmployee = new MainEmployee();
        MainProduct mainProduct = new MainProduct();
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String account = employeeManagement.login();

            if (account.equals("Manager")) {
                String choice = "-1";
                do {
                    draw();
                    System.out.printf(EDIT_PRINTF + "\n", "", "QUẢN LÍ", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "1. Quản lí nhân viên", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "2. Quản lí sản phẩm", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "3. Quản lí phiếu bán hàng", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "4. Thay đổi mật khẩu", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "5. Tạo tài khản quản lí", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "6. Tạo tài khoản kế toán", "");
                    System.out.printf(EDIT_PRINTF + "\n", "", "0. Đăng xuất", "");
                    choice = sc.next();
                    switch (choice) {
                        case "1": {
                            mainEmployee.run();
                            break;
                        }
                        case "2": {
                            mainProduct.run();
                            break;
                        }
                        case "3": {
                            mainSalesSlip.run();
                            break;
                        }
                        case "4": {
                            while (true) {
                                System.out.printf(EDIT_PRINTF + "\n", "", "Nhập tài khoản cần thay đổi", "");
                                String oldAccount = employeeManagement.checkInput(employeeManagement.getACCOUNT_REGEX());
                                System.out.printf(EDIT_PRINTF, "", "Mật khẩu cũ", "");
                                String oldPassword = sc.next();
                                if (oldPassword.equals(employeeManagement.getAccountManagerList().get(oldAccount))) {
                                    System.out.printf(EDIT_PRINTF + "\n", "", "Mật khẩu mới (Có chữ hoa, chữ thường và số)", "");
                                    String newPassword = employeeManagement.checkInput(employeeManagement.getPASSWORD_REGEX());
                                    employeeManagement.setPasswordManager(account, newPassword);
                                    break;
                                } else if (oldPassword.equals(employeeManagement.getAccountAccountantList().get(oldAccount))) {
                                    System.out.printf(EDIT_PRINTF + "\n", "", "Mật khẩu mới (Có chữ hoa, chữ thường và số)", "");
                                    String newPassword = employeeManagement.checkInput(employeeManagement.getPASSWORD_REGEX());
                                    employeeManagement.setPasswordAccountant(account, newPassword);
                                    break;
                                }
                            }
                            break;
                        }
                        case "5": {
                            System.out.printf(EDIT_PRINTF, "", ENTER_ACCOUNT, "");
                            String newAccount = employeeManagement.checkInput(employeeManagement.getACCOUNT_REGEX());
                            System.out.printf(EDIT_PRINTF, "", ENTER_PASSWORD, "");
                            String newPassword = employeeManagement.checkInput(employeeManagement.getPASSWORD_REGEX());
                            employeeManagement.addAccountManager(newAccount, newPassword);
                            break;
                        }
                        case "6": {
                            System.out.printf(EDIT_PRINTF, "", ENTER_ACCOUNT, "");
                            String newAccount = employeeManagement.checkInput(employeeManagement.getACCOUNT_REGEX());
                            System.out.printf(EDIT_PRINTF, "", ENTER_PASSWORD, "");
                            String newPassword = employeeManagement.checkInput(employeeManagement.getPASSWORD_REGEX());
                            employeeManagement.addAccountAccountant(newAccount, newPassword);
                            break;
                        }
                        case "0": {
                            run = false;
                            System.err.println("GOOD BYE");
                            break;
                        }
                        default: {
                            System.err.println("Không có sự lựa chọn này");
                        }
                    }
                } while (!choice.equals("0"));

            } else if (account.equals("Accountant")) {
                String choice = "-1";
                do {
                    draw();
                    System.out.printf(EDIT_PRINTF, "", "KẾ TOÁN", "");
                    System.out.printf(EDIT_PRINTF, "", "1. Hiển thị danh sách nhân viên", "");
                    System.out.printf(EDIT_PRINTF, "", "2. Sắp xếp bảng lương", "");
                    System.out.printf(EDIT_PRINTF, "", "3. Hiển thị tổng lương phải trả", "");
                    System.out.printf(EDIT_PRINTF, "", "4. Quản lí sản phẩm", "");
                    System.out.printf(EDIT_PRINTF, "", "5. Quản lí phiếu bán hàng", "");
                    System.out.printf(EDIT_PRINTF, "", "0. Đăng xuất", "");
                    choice = sc.next();
                    switch (choice) {
                        case "1": {
                            employeeManagement.showList();
                            break;
                        }
                        case "2": {
                            employeeManagement.sortSalary();
                            break;
                        }
                        case "3": {
                            employeeManagement.getTotalSalrary();
                            break;
                        }
                        case "4": {
                            mainProduct.run();
                            break;
                        }
                        case "5": {
                            mainSalesSlip.run();
                            break;
                        }
                        case "0": {
                            run = false;
                            System.err.println("GOOD BYE");
                            break;
                        }
                        default: {
                            System.err.println("Không có sự lựa chọn này");
                        }
                    }
                } while (!choice.equals("0"));
            }
        }
    }
}
