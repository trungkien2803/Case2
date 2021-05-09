package com.company;

import com.SalesSlip.SalesSlip;
import com.SalesSlip.SalesSlipManagement;
import java.util.Scanner;

public class MainSalesSlip {
    private final String S_40_S_60_S = "%-40s%-50s%-40s%40s";
    private SalesSlipManagement salesSlipManagement = new SalesSlipManagement();

    public  void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void menu() {
        draw();
        System.out.printf("%-70s%-50s%-40s%10s" + "\n", "|", "QUẢN LÍ PHIẾU BÁN HÀNG", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "1. Thêm thông tin phiếu bán hàng", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "2. Hiển thị danh sách phiếu bán hàng", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "3. Xóa thông tin phiếu bán hàng", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "4. Tìm phiếu bán hàng", "", "|");
        System.out.printf(S_40_S_60_S + "\n", "|", "0. Thoát ", "", "|");
        draw();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String choice = "-1";
        do {
            menu();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    SalesSlip salesSlip = new SalesSlip();
                    salesSlipManagement.addInfo(salesSlip);
                    break;
                }
                case "2": {
                    salesSlipManagement.showList();
                    break;
                }
                case "3": {
                    salesSlipManagement.deleteSalesSlipById();
                    break;
                }
                case "4":{
                    salesSlipManagement.findSalesPerformance();
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.err.printf("Không có sự lựa chọn này\n");
                }
            }
        } while (!choice.equals("0"));
    }
}
