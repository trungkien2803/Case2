package com.company;

import com.Product.Product;
import com.Product.ProductManagement;
import com.SalesSlip.SalesSlip;
import com.SalesSlip.SalesSlipManagement;
import com.account.Account;
import com.employee.EmployeeManagement;

import java.util.Scanner;

public class Main extends Thread {
    private static final String EDIT_PRINTF = "%-70s%-30s%s\n";
    private static final EmployeeManagement employeeManagement = new EmployeeManagement();
    private static final ProductManagement productManagement = new ProductManagement();
    private static final SalesSlipManagement salesSlipManagement = new SalesSlipManagement();
    private static final Account account = new Account();
    private static final MainSalesSlip mainSalesSlip = new MainSalesSlip();
    private static final MainEmployee mainEmployee = new MainEmployee();
    private static final MainProduct mainProduct = new MainProduct();
    private static final Scanner sc = new Scanner(System.in);

    private static Main instance;

    private Main(){}

    public static Main getInstance(){
        if(instance == null){
            instance = new Main();
        }
        return instance;
    }
    private static void menuAccountant() {
        draw();
        System.out.printf(EDIT_PRINTF, "", "KẾ TOÁN", "");
        System.out.printf(EDIT_PRINTF, "", "1. Hiển thị danh sách nhân viên", "");
        System.out.printf(EDIT_PRINTF, "", "2. Sắp xếp bảng lương", "");
        System.out.printf(EDIT_PRINTF, "", "3. Hiển thị tổng lương phải trả", "");
        System.out.printf(EDIT_PRINTF, "", "4. Quản lí sản phẩm", "");
        System.out.printf(EDIT_PRINTF, "", "5. Quản lí phiếu bán hàng", "");
        System.out.printf(EDIT_PRINTF, "", "6. Hiển thị thu chi", "");
        System.out.printf(EDIT_PRINTF, "", "0. Đăng xuất", "");
    }

    private static void menuManager() {
        draw();
        System.out.printf(EDIT_PRINTF + "\n", "", "QUẢN LÍ", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "1. Quản lí nhân viên", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "2. Quản lí sản phẩm", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "3. Quản lí phiếu bán hàng", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "4. Thay đổi mật khẩu", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "5. Tạo tài khản quản lí", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "6. Tạo tài khoản kế toán", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "7. Hiển thị thu chi", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "0. Đăng xuất", "");
    }

    public static void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static double showTotalExpenditure(){
        productManagement.setProductList();
        double sum = 0;
        double productMoney = 0;
        if (productManagement.getProductList() != null) {
            for (Product product : productManagement.getProductList()) {
                productMoney += Double.parseDouble(product.getImportPrice());
            }
        }
        sum = productMoney + employeeManagement.getTotalSalrary();
        System.out.printf("%-70s%-30s%s\n\n", "","TỔNG CHI: "+ sum,"");
        System.out.printf("%-80s%-30s%s\n", "","TIỀN NHẬP HÀNG: " + productMoney,"");
        System.out.printf("%-80s%-30s%s\n\n", "","TIỀN LƯƠNG NHÂN VIÊN: " + employeeManagement.getTotalSalrary(),"");

        return sum;
    }
    public static Double showTotalRevenue(){
        double sum = 0;
        draw();
        if (salesSlipManagement.getSalesSlipList() != null){
            salesSlipManagement.readSalesSlipListFromFile();
            for (SalesSlip salesSlip : salesSlipManagement.getSalesSlipList()) {
                sum += salesSlip.getTotalPayment();
                System.out.println();
            }
        }
        System.out.printf("%-70s%-30s%s\n\n", "","TỔNG THU: "+sum,"");
        return sum;
    }
    public static void result(){
        Double result = showTotalRevenue() - showTotalExpenditure();
        if (result < 0){
            System.out.printf("%-70s%-30s%s\n", "","LỖ: " + result +"vnd","");
        } else if (result >= 0){
            System.out.printf("%-70s%-30s%s\n", "","LÃI: " + result +"vnd","");
        }
    }
    private static void accountingRights() {
        String choice;
        do {
            menuAccountant();
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
                }case "6": {
                    result();
                    break;
                }
                case "0": {
                    System.err.println("GOOD BYE");
                    break;
                }
                default: {
                    System.err.println("Không có sự lựa chọn này");
                }
            }
        } while (!choice.equals("0"));
    }

    private static void administer(String login) {
        String choice;
        do {
            menuManager();
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
                    account.changePassword(employeeManagement, account, sc, login);
                    break;
                }
                case "5": {
                    account.signUpManagerAccount();
                    break;
                }
                case "6": {
                    account.signUpAccountAccountant();
                    break;
                }
                case "7": {
                    result();
                    break;
                }
                case "0": {
                    System.err.println("GOOD BYE");
                    break;
                }
                default: {
                    System.err.println("Không có sự lựa chọn này");
                }
            }
        } while (!choice.equals("0"));
    }

    public void run() {
        while (true) {
            String login = account.login();
            switch (login) {
                case "Manager":
                    administer(login);
                    break;
                case "Accountant":
                    accountingRights();
                    break;
//                case "false":
//                    try {
//                        System.err.printf(EDIT_PRINTF,"","Bạn đã nhập sai quá nhiều lần, vui lòng đợi để thử lại","");
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    break;
            }
        }
    }
}
