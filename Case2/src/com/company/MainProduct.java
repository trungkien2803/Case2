package com.company;

import com.Product.*;
import com.employee.Employee;

import java.io.File;
import java.util.Scanner;

public class MainProduct {
    private final String S_40_S_60_S = "%-40s%-50s%-40s%40s";

    public void menu() {
        for (int  i = 0; i < 170; i++){
            System.out.printf("-");
        }
        System.out.println();
        System.out.printf("%-70s%-50s%-40s%10s" + "\n","|","QUẢN LÍ SẢN PHẨM","","|");
        System.out.printf(S_40_S_60_S + "\n","|","","","|");
        System.out.printf(S_40_S_60_S + "\n","|","1. Thêm sản phẩm xe máy","8. Sắp xếp sản phẩm theo năm sản xuất","|");
        System.out.printf(S_40_S_60_S + "\n","|","2. Thêm sản phẩm xe điện","9. Sắp xếp sản phẩm theo giá giảm dần","|");
        System.out.printf(S_40_S_60_S + "\n","|","3. Hiển thị danh sách sản phẩm","10. Sắp xếp sản phẩm theo giá tăng dần","|");
        System.out.printf(S_40_S_60_S + "\n","|","4. Hiển thị thông số kĩ thuật","11. Xóa sản phẩm","|");
        System.out.printf(S_40_S_60_S + "\n","|","5. Sửa thông tin sản phẩm","12. Hiển thị các sản phẩm xe số","|");
        System.out.printf(S_40_S_60_S + "\n","|","6. Sửa thông số kĩ thuật sản phẩm","13. Hiển thị các sản phẩm xe điện","|");
        System.out.printf(S_40_S_60_S + "\n","|","7. Tìm kiếm sản phẩm","0. Thoát","|");
        for (int  i = 0; i < 170; i++){
            System.out.printf("-");
        }
        System.out.println();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String choice;
        ProductManagement productManagement = new ProductManagement();
        File file = new File(productManagement.getPATH());
        do {
            if (file.length() > 0){
                productManagement.setProductList();
            }
            this.menu();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    Motorbike motorbike = new Motorbike();
                    productManagement.addInfo(motorbike);
                    break;
                }
                case "2": {
                    ElectricMotorcycle electricMotorcycle = new ElectricMotorcycle();
                    productManagement.addInfo(electricMotorcycle);
                    break;
                }
                case "3": {
                    productManagement.showListInfo();
                    break;
                }
                case "4": {
                    productManagement.showSpecificationsList();
                    break;
                }
                case "5": {
                    productManagement.editInfoById();
                    break;
                }
                case "6": {
                    productManagement.editSpecificationsById();
                    break;
                }
                case "7":{
                    productManagement.findProductById();
                    break;
                }
                case "8":{
                    productManagement.sortInfoMFG();
                    break;
                }
                case "9":{
                    productManagement.sortInfoPriceMaxMin();
                    break;
                }
                case "10": {
                    productManagement.sortInfoPriceMinMax();
                    break;
                }
                case "11": {
                    productManagement.deleteInfo();
                    break;
                }
                case "12": {
                    productManagement.showMotorbikeListInfo();
                    break;
                }
                case "13":{
                    productManagement.showElectricMotorcycleListInfo();
                }
                case "0":{
                    break;
                }
                default: {
                    System.err.printf("Không có sự lựa chọn này\n");
                }
            }
        } while (!choice.equals("0"));

    }

}
