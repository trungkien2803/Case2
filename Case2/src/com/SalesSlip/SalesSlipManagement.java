package com.SalesSlip;

import com.Product.Product;
import com.Product.ProductManagement;
import com.company.ReadWriteFile;
import com.employee.Employee;
import com.employee.EmployeeManagement;
import com.employee.Salesman;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SalesSlipManagement {
    private static final String ID_REGEX = "^[A-Z]{2}\\d*";
    private static final String NUMBER_REGEX = "\\d*";
    private static final String NAME_REGEX = "[a-zA-Z]*";
    private static final String SALES_SLIP_ID = "Mã phiếu";
    private static final String DATE_SALE = "Ngày bán";
    private static final String SALESMAN = "Nhân viên bán hàng";
    private static final String PRODUCT_ID = "Mã sản phẩm";
    private static final String PRODUCT_NAME = "Tên sản phẩm";
    private static final String PRODUCT_PRICE = "Giá bán";
    private static final String TAX = "Thuế";
    private static final String TOTAL_PAYMENT = "Tổng tiền";
    private static final String DISCOUNT = "Giảm giá";
    public static final String CUSTOMER_NAME = "Tên khách hàng";
    private final String SALES_SLIP_TXT = "SalesSlip.txt";
    private final Scanner sc = new Scanner(System.in);
    private List<SalesSlip> salesSlipList = new ArrayList<>();
    private static final ProductManagement productManagement = new ProductManagement();
    private static final EmployeeManagement employeeManagement = new EmployeeManagement();
    private final ReadWriteFile<SalesSlip> readWriteFile = new ReadWriteFile();
    private final File file = new File(SALES_SLIP_TXT);
    private final Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String strDate = formatter.format(date);


    public SalesSlipManagement() {
    }

    public void writeSalesSlipListToFile() {
        readWriteFile.writeDataToFile(SALES_SLIP_TXT, salesSlipList);
    }

    public void readSalesSlipListFromFile() {
        if (file.length() > 0) {
            salesSlipList = readWriteFile.readDataFromFile(SALES_SLIP_TXT);
        }
    }

    public void addInfo(SalesSlip salesSlip) {
        employeeManagement.setEmployees();
        productManagement.setProductList();
        draw();
        System.out.println(SALES_SLIP_ID);
        salesSlip.setSalesSlipId(checkInput(ID_REGEX));
        checkEnteredId(salesSlip);
        salesSlip.setDateSale(strDate);
        System.out.println(CUSTOMER_NAME);
        salesSlip.setCustomerName(checkInput(NAME_REGEX));
        enterSalesman(salesSlip);
        enterProduct(salesSlip);
        System.out.println(TAX);
        salesSlip.setTax(checkInput(NUMBER_REGEX));
        System.out.println(DISCOUNT);
        salesSlip.setDiscount(checkInput(NUMBER_REGEX));
        salesSlipList.add(salesSlip);
        salesPerformance(salesSlip);
        addProductSold(salesSlip);
        warehouse(salesSlip);
        writeSalesSlipListToFile();
    }

    private void addProductSold(SalesSlip salesSlip) {
        productManagement.setProductList();
        if (productManagement.getProductList() != null) {
            for (Product product : productManagement.getProductList()) {
                if (salesSlip.getProductId().equals(product.getProductId())) {
                    productManagement.getProductSoldList().add(product);
                    productManagement.getReadWriteDataFile().writeDataToFile("productsSold.txt", productManagement.getProductSoldList());
                    break;
                }
            }
        }
    }

    private void salesPerformance(SalesSlip salesSlip) {
        employeeManagement.setEmployees();
        if (employeeManagement.getEmployeeList() != null) {
            for (Employee employee : employeeManagement.getEmployeeList()) {
                if (employee instanceof Salesman) {
                    if (salesSlip.getSalesmanId().equals(employee.getId())) {
                        ((Salesman) employee).setSalesPerformance();
                        employeeManagement.getReadWriteDataFile().writeDataToFile(employeeManagement.getListEmployeeTxt(), employeeManagement.getEmployeeList());
                        break;
                    }
                }
            }
        }
    }

    private void warehouse(SalesSlip salesSlip) {
        productManagement.setProductList();
        if (productManagement.getProductList() != null) {
            for (Product product : productManagement.getProductList()) {
                if (salesSlip.getProductId().equals(product.getProductId())) {
                    productManagement.getProductList().remove(product);
                    productManagement.getReadWriteDataFile().writeDataToFile(productManagement.getPATH(), productManagement.getProductList());
                    break;
                }
            }
        }
    }

    public List<SalesSlip> getSalesSlipList() {
        return salesSlipList;
    }

    public void showList() {
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH PHIẾU BÁN HÀNG", "");
        if (salesSlipList != null){
            readSalesSlipListFromFile();
            for (SalesSlip salesSlip : salesSlipList) {
                showInfo(salesSlip);
                System.out.println();
            }
        }
    }

    private void showInfo(SalesSlip salesSlip) {
        draw();
        System.out.printf("%-35s" + "%-35s" + "%-35s\n", SALES_SLIP_ID + ": " + salesSlip.getSalesSlipId(), DATE_SALE + ": " + salesSlip.getDateSale(), SALESMAN + ": " + salesSlip.getSalesman());
        System.out.printf("%-35s" + "%-35s" + "%-35s\n", PRODUCT_ID + ": " + salesSlip.getProductId(), PRODUCT_NAME + ": " + salesSlip.getProductName(), PRODUCT_PRICE + ": " + salesSlip.getPrice() + "vnd");
        System.out.printf("%-35s" + "%-35s" + "%-35s\n", TAX + ": " + salesSlip.getTax() + "%", DISCOUNT + ": " + salesSlip.getDiscount() + "%", CUSTOMER_NAME + ": " + salesSlip.getCustomerName());
        System.out.printf("%-35s" + "%-35s" + "%-35s\n", TOTAL_PAYMENT + ": " + salesSlip.getTotalPayment() + "vnd", "", "");
        draw();
    }

    private void enterSalesman(SalesSlip salesSlip) {
        employeeManagement.setEmployees();
        boolean check = true;
        String salesmanId;
        System.out.println("Mã nhân viên");
        while (check) {
            salesmanId = sc.next();
            if (employeeManagement.getEmployeeList() != null){
                for (Employee employee : employeeManagement.getEmployeeList()) {
                    if (employee instanceof Salesman) {
                        if (salesmanId.equals(employee.getId())) {
                            salesSlip.setSalesmanId(salesmanId);
                            salesSlip.setSalesman(employee.getFullName());
                            check = false;
                            break;
                        }
                    }
                }
            }
            if (check) {
                System.err.println("Mã nhân viên không tồn tại");
                System.err.println("Nhập lại");
            }
        }
    }

    private void enterProduct(SalesSlip salesSlip) {
        productManagement.setProductList();
        boolean check = true;
        String productId;
        System.out.println("Mã sản phẩm");
        while (check) {
            productId = sc.next();
            if (productManagement.getProductList() != null) {
                for (Product product : productManagement.getProductList()) {
                    if (productId.equals(product.getProductId())) {
                        salesSlip.setProductId(product.getProductId());
                        salesSlip.setProductName(product.getName());
                        salesSlip.setPrice(product.getImportPrice());
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                System.err.println("Mã sản phẩm tồn tại");
                System.err.println("Nhập lại");
            }
        }
    }

    public void deleteSalesSlipById() {
        readSalesSlipListFromFile();
        System.out.print("Nhập mã phiếu cần xóa ");
        String id = sc.next();
        draw();
        for (SalesSlip salesSlip : salesSlipList) {
            if (salesSlip.getSalesSlipId().equals(id)) {
                salesSlipList.remove(salesSlip);
                deleteSalesPerformance(salesSlip);
                writeSalesSlipListToFile();
                break;
            }
        }

    }

    private void deleteSalesPerformance(SalesSlip salesSlip) {
        employeeManagement.setEmployees();
        if (employeeManagement.getEmployeeList() != null) {
            for (Employee employee : employeeManagement.getEmployeeList()) {
                if (employee instanceof Salesman) {
                    if (salesSlip.getSalesmanId().equals(employee.getId())) {
                        ((Salesman) employee).reduceSalesPerformance();
                        employeeManagement.getReadWriteDataFile().writeDataToFile(employeeManagement.getListEmployeeTxt(), employeeManagement.getEmployeeList());
                        break;
                    }
                }
            }
        }
        productManagement.getReadWriteDataFile().readDataFromFile("productsSold.txt");
        for (Product product : productManagement.getProductSoldList()) {
            if (salesSlip.getProductId().equals(product.getProductId())) {
                productManagement.getProductList().add(product);
                productManagement.getReadWriteDataFile().writeDataToFile(productManagement.getPATH(), productManagement.getProductList());
                break;
            }
        }
    }
    public void findSalesPerformance(){
        System.out.println("Nhập mã phiếu cần tìm ");
        String id = checkInput(ID_REGEX);
        boolean check = false;
        for (SalesSlip salesSlip : salesSlipList) {
            if (id.equals(salesSlip.getSalesSlipId())) {
                showInfo(salesSlip);
                System.out.println();
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Không tồn tại mã phiếu này");
        }
    }
    public String checkInput(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Scanner sc = new Scanner(System.in);
        String string = sc.next();
        while (!pattern.matcher(string).matches()) {
            if (pattern.matcher(string).matches()) {
                return string;
            }
            System.err.println("Dữ liệu nhập vào không đúng");
            System.err.println("Nhập lại");
            string = sc.next();
        }
        return string;
    }

    private void checkEnteredId(SalesSlip salesSlip) {
        while (true) {
            int index = 0;
            readSalesSlipListFromFile();
            for (SalesSlip value : salesSlipList) {
                if (salesSlip.getSalesSlipId().equals(value.getSalesSlipId())) {
                    System.err.println("Mã phiếu đã tồn tại");
                    salesSlip.setSalesSlipId(checkInput(ID_REGEX));
                    index++;
                }
            }
            if (index == 0) {
                break;
            }
        }
    }

    public void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
