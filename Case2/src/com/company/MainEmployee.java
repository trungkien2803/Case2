package com.company;

import com.employee.*;

import java.io.File;
import java.util.Scanner;

public class MainEmployee {
    private static final String S_40_S_60_S = "%-40s%-50s%-40s%40s";
    private static final EmployeeManagement employeeManagement = new EmployeeManagement();

    public void menu() {
        draw();
        System.out.printf("%-70s%-50s%-40s%10s" + "\n", "|", "QUẢN LÍ NHÂN VIÊN", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "1. Thêm thông tin quản lí", "8. Xóa thông tin nhân viên", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "2. Thêm thông tin kế toán", "9. Sắp xếp lương nhân viên", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "3. Thêm thông tin nhân viên bán hàng", "10. Hiển thị tổng lương phải trả", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "4. Thêm thông tin kĩ thuật viên", "11. Tìm kiếm nhân viên theo ID ", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "5. Thêm thông tin nhân viên bảo vệ", "12. Hiển thị thành tích bán hàng ", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "6. Hiển thị danh sách nhân viên", "0. Thoát ", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "7. Sửa thông tin nhân viên", "", "|");
        draw();
    }

    private void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String choice;
        File file = new File(employeeManagement.getPath());
        do {
            if (file.length() > 0){
                employeeManagement.setEmployees();
            }
            menu();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    Manager manager = new Manager();
                    employeeManagement.addInfo(manager);
                    break;
                }
                case "2": {
                    Accountant accountant = new Accountant();
                    employeeManagement.addInfo(accountant);
                    break;
                }
                case "3": {
                    Salesman salesman = new Salesman();
                    employeeManagement.addInfo(salesman);
                    break;
                }
                case "4": {
                    Technicians technicians = new Technicians();
                    employeeManagement.addInfo(technicians);
                    break;
                }
                case "5": {
                    Security security = new Security();
                    employeeManagement.addInfo(security);
                    break;
                }
                case "6": {
                    employeeManagement.showList();
                    break;
                }
                case "7": {
                    employeeManagement.editInfoById();
                    break;
                }
                case "8": {
                    employeeManagement.deleteInfo();
                    break;
                }
                case "9": {
                    employeeManagement.sortSalary();
                    break;
                }
                case "10": {
                    System.out.println("TỔNG TIỀN LƯƠNG PHẢI TRẢ: " + employeeManagement.getTotalSalrary());
                    break;
                }
                case "11": {
                    employeeManagement.findEmployeeById();
                    break;
                }
                case "12": {
                    employeeManagement.showSalesPerformance();
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.err.print("Không có sự lựa chọn này\n");
                }
            }
        } while (!choice.equals("0"));

    }
}
