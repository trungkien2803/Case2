package com.Product;

import com.company.ReadWriteFile;
import com.employee.EmployeeManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductManagement {
    private static final String EXAMPLE_PRICE = "(VD:20000000)";
    private static final String PRODUCT_ID = "Mã sản phẩm";
    private static final String NAME = "Tên sản phẩm";
    private static final String MFG = "Năm sản xuất";
    private static final String COMPANY = "Hãng sản xuất";
    private static final String COLOR = "Màu sắc";
    private static final String IMPORT_PRICE = "Giá nhập";
    private static final String PRICE = "Giá bán";
    private static final String EDIT_PRINTF = "%-70s%-30s%s";
    private static final String EXAMPLE_PRODUCT_ID = "(VD:AA11)";
    private static final String EXAMPLE_NAME = "(VD:WinnerX)";
    private static final String EXAMPLE_MFG = "(VD:2021)";
    private static final String EXAMPLE_COMPANY = "(VD:Honda)";
    private static final String EXAMPLE_COLOR = "(VD:Red)";
    private static final String PRODUCT_ID_REGEX = "^[A-Z]{2}\\d*$";
    private static final String MFG_REGEX = "\\d{4}";
    private static final String COMPANY_REGEX = "^[a-zA-Z]\\w*";
    private static final String COLOR_REGEX = "[a-zA-Z]*";
    private static final String NAME_REGEX = "^[a-zA-Z]\\w*";
    private static final String PRICE_REGEX = "\\d*";
    private static final String FUEL_CONSUMPTION_REGEX = "\\d*(\\.*)\\d*";
    private static final String FUEL_TANK_VOLUME_REGEX = "\\d*(\\.*)\\d*";
    private static final String CYLINDER_CAPACITY_REGEX = "\\d*";
    private static final String MAX_SPEED_REGEX = "\\d*";
    private static final String MAX_DISTANCE_REGEX = "\\d*(\\.*)\\d*";
    private static final String CHARGING_TIME_REGEX = "\\d*(\\.*)\\d*";
    private static final String FUEL_CONSUMPTION = "Mức tiêu thụ nhiên liệu";
    private static final String FUEL_TANK_VOLUME = "Dung tích bình xăng";
    private static final String CYLINDER_CAPACITY = "Dung tích xi lanh";
    private static final String MAX_SPEED = "Vận Tốc Tối Đa";
    private static final String MAX_DISTANCE = "Quãng đường di chuyển tối đa";
    private static final String CHARGING_TIME = "Thời gian sạc";
    private static final String WATTAGE = "Công suất";
    private static final String BATTERY_TYPE = "Loại pin";
    private static final String CC = "(cc)";
    private static final String KM_H = "(km/h)";
    private static final String KM_L = "(km/l)";
    private static final String L = "(l)";
    private static final String KM = "(km)";
    private static final String H = "(h)";
    private static final String W = "(w)";
    private static List<Product> productList = new ArrayList<>();
    private static List<Product> productSoldList = new ArrayList<>();
    private static final String PATH = "listProduct.txt";
    private static final Scanner sc = new Scanner(System.in);
    private static final ReadWriteFile<Product> readWriteDataFile = new ReadWriteFile<>();

    public static void main(String[] args) {
        System.out.println(productSoldList.size());
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getPATH() {
        return PATH;
    }

    public List<Product> getProductSoldList() {
        return productSoldList;
    }

    public ReadWriteFile<Product> getReadWriteDataFile() {
        return readWriteDataFile;
    }

    public void setProductList() {
        productList = readWriteDataFile.readDataFromFile(PATH);
    }

    public void addInfo(Product product) {
        if (product instanceof Motorbike) {
            enterInfo(product);
            System.out.println(CYLINDER_CAPACITY + CC);
            ((Motorbike) product).setCylinderCapacity(checkInput(CYLINDER_CAPACITY_REGEX));
            System.out.println(MAX_SPEED + KM_H);
            ((Motorbike) product).setMaxSpeed(checkInput(MAX_SPEED_REGEX));
            System.out.println(FUEL_CONSUMPTION + KM_L);
            ((Motorbike) product).setFuelConsumption(checkInput(FUEL_CONSUMPTION_REGEX));
            System.out.println(FUEL_TANK_VOLUME + L);
            ((Motorbike) product).setFuelTankVolume(checkInput(FUEL_TANK_VOLUME_REGEX));
        } else if (product instanceof ElectricMotorcycle) {
            editInfo(product);
            System.out.println(MAX_DISTANCE + KM);
            ((ElectricMotorcycle) product).setMaxDistance(checkInput(MAX_DISTANCE_REGEX));
            System.out.println(CHARGING_TIME + H);
            ((ElectricMotorcycle) product).setChargingTime(checkInput(CHARGING_TIME_REGEX));
            System.out.println(WATTAGE + W);
            ((ElectricMotorcycle) product).setWattage(checkInput(MAX_DISTANCE_REGEX));
            System.out.println(BATTERY_TYPE);
            ((ElectricMotorcycle) product).setBatteryType(checkInput(NAME_REGEX));
        }
        draw();
        productList.add(product);
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    private void enterInfo(Product product) {
        draw();
        System.out.println(PRODUCT_ID + EXAMPLE_PRODUCT_ID);
        product.setProductId(checkInput(PRODUCT_ID_REGEX));
        checkEnteredId(product);
        System.out.println(NAME + EXAMPLE_NAME);
        product.setName(checkInput(NAME_REGEX));
        System.out.println(MFG + EXAMPLE_MFG);
        product.setMfg(checkInput(MFG_REGEX));
        System.out.println(COMPANY + EXAMPLE_COMPANY);
        product.setCompany(checkInput(COMPANY_REGEX));
        System.out.println(COLOR + EXAMPLE_COLOR);
        product.setColor(checkInput(COLOR_REGEX));
        System.out.println(IMPORT_PRICE + EXAMPLE_PRICE);
        product.setImportPrice(checkInput(PRICE_REGEX));
    }

    private void checkEnteredId(Product product) {
        while (true) {
            int index = 0;
            for (Product value : productList) {
                if (product.getProductId().equals(value.getProductId())) {
                    System.err.println("Mã sản phẩm đã tồn tại");
                    product.setProductId(checkInput(PRODUCT_ID_REGEX));
                    index++;
                }
            }
            if (index == 0) {
                break;
            }
        }
    }

    public void showListInfo() {
        setProductList();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH SẢN PHẨM TRONG KHO", "");
        if (productList != null) {
            for (Product product : productList) {
                showInfo(product);
                System.out.println();
            }
        }
        System.out.print("\n\n");
        draw();
    }

    public void showProductsSold() {
        productSoldList = readWriteDataFile.readDataFromFile("productsSold.txt");
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH SẢN PHẨM ĐÃ BÁN", "");
        if (productSoldList != null) {
            for (Product product : productSoldList) {
                showInfo(product);
                System.out.println();
            }
        }
        System.out.print("\n\n");
        draw();
    }

    public void showMotorbikeListInfo() {
        setProductList();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH SẢN PHẨM XE MÁY TRONG KHO", "");
        if (productList != null) {
            for (Product product : productList) {
                if (product instanceof Motorbike) {
                    showInfo(product);
                    System.out.println();
                }
            }
        }
        System.out.print("\n\n");
        draw();
    }

    public void showElectricMotorcycleListInfo() {
        setProductList();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH SẢN PHẨM XE ĐIỆN TRONG KHO", "");
        if (productList != null) {
            for (Product product : productList) {
                if (product instanceof ElectricMotorcycle) {
                    showInfo(product);
                    System.out.println();
                }
            }
        }
        System.out.print("\n\n");
        draw();
    }



    private void showInfo(Product product) {
        if (product instanceof Motorbike) {
            System.out.printf("%-10s" + PRODUCT_ID + ": %-15s, " + NAME + ": %-15s " + MFG + ": %-15s, " + COMPANY + ": %-15s, " + COLOR + ": %-15s, " + IMPORT_PRICE + ": %-15s " + PRICE + ": %-15s ", "(XE MÁY)", product.getProductId(), product.getName(), product.getMfg(), product.getCompany(), product.getColor(), product.getImportPrice() + "vnd", product.getPrice() + "vnd");
        } else if (product instanceof ElectricMotorcycle) {
            System.out.printf("%-10s" + PRODUCT_ID + ": %-15s, " + NAME + ": %-15s " + MFG + ": %-15s, " + COMPANY + ": %-15s, " + COLOR + ": %-15s, " + IMPORT_PRICE + ": %-15s " + PRICE + ": %-15s ", "(XE ĐIỆN)", product.getProductId(), product.getName(), product.getMfg(), product.getCompany(), product.getColor(), product.getImportPrice() + "vnd", product.getPrice() + "vnd");
        }
    }

    public void showSpecificationsList() {
        setProductList();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "THÔNG SỐ KĨ THUẬT CÁC SẢN PHẨM", "");
        if (productList != null) {
            for (Product product : productList) {
                if (product instanceof Motorbike) {
                    showSpecifications(product);
                    System.out.println();
                } else if (product instanceof ElectricMotorcycle) {
                    showSpecifications(product);
                    System.out.println();
                }
            }
        }
        System.out.print("\n\n");
        draw();
    }

    private void showSpecifications(Product product) {
        if (product instanceof Motorbike motorbike) {
            System.out.printf(NAME + ": %-10s " + CYLINDER_CAPACITY + ": %-5s, " + MAX_SPEED + ": %-10s, " + FUEL_TANK_VOLUME + ": %-5s, " + FUEL_CONSUMPTION + ": %-15s ", motorbike.getName(), motorbike.getCylinderCapacity() + "cc", motorbike.getMaxSpeed() + "km/h", motorbike.getFuelTankVolume() + "l", motorbike.getFuelConsumption() + "km/l");
        } else if (product instanceof ElectricMotorcycle electricMotorcycle) {
            System.out.printf(NAME + ": %-10s " + MAX_DISTANCE + ": %-5s, " + CHARGING_TIME + ": %-10s, " + WATTAGE + ": %-5s, " + BATTERY_TYPE + ": %-15s ", electricMotorcycle.getName(), electricMotorcycle.getMaxDistance() + "km", electricMotorcycle.getChargingTime() + "h", electricMotorcycle.getWattage() + "w", electricMotorcycle.getBatteryType() + "km/l");
        }
    }

    private void showInfoSpecifications(Product product) {
        if (product instanceof Motorbike motorbike) {
            System.out.printf(EDIT_PRINTF + "\n\n", "", "THÔNG TIN CƠ BẢN", "");
            System.out.printf(PRODUCT_ID + ": %-15s, " + NAME + ": %-10s " + MFG + ": %-15s, " + COMPANY + ": %-15s, " + COLOR + ": %-15s, " + IMPORT_PRICE + ": %-15s\n\n", product.getProductId(), product.getName(), product.getMfg(), product.getCompany(), product.getColor(), product.getImportPrice() + "vnd");
            System.out.printf(EDIT_PRINTF + "\n\n", "", "THÔNG SỐ KĨ THUẬT", "");
            System.out.printf(CYLINDER_CAPACITY + ": %-15s, " + MAX_SPEED + ": %-15s, " + FUEL_TANK_VOLUME + ": %-15s, " + FUEL_CONSUMPTION + ": %-15s ", motorbike.getCylinderCapacity() + "cc", motorbike.getMaxSpeed() + "km/h", motorbike.getFuelTankVolume() + "l", motorbike.getFuelConsumption() + "km/l");
        } else if (product instanceof ElectricMotorcycle electricMotorcycle) {
            System.out.printf(EDIT_PRINTF + "\n\n", "", "THÔNG TIN CƠ BẢN", "");
            System.out.printf(PRODUCT_ID + ": %-15s, " + NAME + ": %-15s " + MFG + ": %-15s, " + COMPANY + ": %-15s, " + COLOR + ": %-15s, " + IMPORT_PRICE + ": %-15s\n\n", product.getProductId(), product.getName(), product.getMfg(), product.getCompany(), product.getColor(), product.getImportPrice() + "vnd");
            System.out.printf(EDIT_PRINTF + "\n\n", "", "THÔNG SỐ KĨ THUẬT", "");
            System.out.printf(MAX_DISTANCE + ": %-15s, " + CHARGING_TIME + ": %-15, " + WATTAGE + ": %-15s, " + BATTERY_TYPE + ": %-15s ", electricMotorcycle.getMaxDistance() + "km", electricMotorcycle.getChargingTime() + "h", electricMotorcycle.getWattage() + "w", electricMotorcycle.getBatteryType() + "km/l");
        }
    }

    public void editInfoById() {
        System.out.print("Nhập mã sản phẩm cần sửa thông tin ");
        String id = sc.next();
        boolean check = false;
        draw();
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                editInfo(product);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Mã sản phẩm không tồn tại");
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    private void editInfo(Product product) {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            this.menu();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    System.out.println(MFG + EXAMPLE_MFG);
                    String mfg = checkInput(MFG_REGEX);
                    product.setMfg(mfg);
                    break;
                }
                case "2": {
                    System.out.println(NAME + EXAMPLE_NAME);
                    String name = checkInput(NAME_REGEX);
                    product.setMfg(name);
                    break;
                }
                case "3": {
                    System.out.println(COMPANY + EXAMPLE_COMPANY);
                    String company = checkInput(COMPANY_REGEX);
                    product.setCompany(company);
                    break;
                }
                case "4": {
                    System.out.println(COLOR + EXAMPLE_COLOR);
                    String color = checkInput(COLOR_REGEX);
                    product.setColor(color);
                    break;
                }
                case "5": {
                    System.out.println(IMPORT_PRICE + EXAMPLE_PRICE);
                    String price = checkInput(PRICE_REGEX);
                    product.setImportPrice(price);
                    break;
                }
                case "6": {
                    System.out.println(NAME + EXAMPLE_NAME);
                    String name = checkInput(NAME_REGEX);
                    product.setName(name);
                    System.out.println(MFG + EXAMPLE_MFG);
                    String mfg = checkInput(MFG_REGEX);
                    product.setMfg(mfg);
                    System.out.println(COMPANY + EXAMPLE_COMPANY);
                    String company = checkInput(COMPANY_REGEX);
                    product.setCompany(company);
                    System.out.println(COLOR + EXAMPLE_COLOR);
                    String color = checkInput(COLOR_REGEX);
                    product.setColor(color);
                    System.out.println(IMPORT_PRICE + EXAMPLE_PRICE);
                    String price = checkInput(PRICE_REGEX);
                    product.setImportPrice(price);
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.err.printf(EDIT_PRINTF, "", "Không có sự lựa chọn này", "");
                    break;
                }
            }
        } while (!choice.equals("0"));
    }

    public void editSpecificationsById() {
        System.out.print("Nhập mã sản phẩm cần sửa thông số kĩ thuật ");
        String id = sc.next();
        boolean check = false;
        draw();
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                if (product instanceof Motorbike) {
                    editMotorbikeSpecifications((Motorbike) product);
                } else if (product instanceof ElectricMotorcycle) {
                    editElectricMotorcycleSpecifications((ElectricMotorcycle) product);
                }
                check = true;
            }
        }
        if (!check) {
            System.err.println("Mã sản phẩm không tồn tại");
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    private void editMotorbikeSpecifications(Motorbike motorbike) {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            this.menuMotorbike();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    System.out.println(CYLINDER_CAPACITY + CC);
                    String cylinderCapacity = checkInput(CYLINDER_CAPACITY_REGEX);
                    motorbike.setCylinderCapacity(cylinderCapacity);
                    break;
                }
                case "2": {
                    System.out.println(MAX_SPEED + KM_H);
                    String maxSpeed = checkInput(MAX_SPEED_REGEX);
                    motorbike.setMaxSpeed(maxSpeed);
                    break;
                }
                case "3": {
                    System.out.println(FUEL_CONSUMPTION + KM_L);
                    String fuelConsumption = checkInput(FUEL_CONSUMPTION_REGEX);
                    motorbike.setFuelConsumption(fuelConsumption);
                }
                case "4": {
                    System.out.println(FUEL_TANK_VOLUME + L);
                    String fuelTankVolume = checkInput(FUEL_TANK_VOLUME_REGEX);
                    motorbike.setFuelTankVolume(fuelTankVolume);
                }
                case "0": {
                    break;
                }
                default: {
                    System.err.print("Không có sự lựa chọn này\n");
                    break;
                }
            }
        } while (!choice.equals("0"));

    }

    private void editElectricMotorcycleSpecifications(ElectricMotorcycle electricMotorcycle) {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            this.menuElectricMotorcylcle();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    System.out.println(MAX_DISTANCE);
                    String maxDistance = checkInput(MAX_DISTANCE_REGEX);
                    electricMotorcycle.setMaxDistance(maxDistance);
                    break;
                }
                case "2": {
                    System.out.println(CHARGING_TIME);
                    String chargingTime = checkInput(CHARGING_TIME_REGEX);
                    electricMotorcycle.setChargingTime(chargingTime);
                    break;
                }
                case "3": {
                    System.out.println(WATTAGE);
                    String wattage = checkInput(MAX_DISTANCE_REGEX);
                    electricMotorcycle.setChargingTime(wattage);
                    break;
                }
                case "4": {
                    System.out.println(BATTERY_TYPE);
                    String batteryType = checkInput(NAME_REGEX);
                    electricMotorcycle.setChargingTime(batteryType);
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.err.print("Không có sự lựa chọn này");
                    break;
                }
            }
        } while (!choice.equals("0"));

    }

    public void deleteInfo() {
        System.out.print("Nhập mã sản phẩm cần xóa ");
        String id = sc.next();
        draw();
        boolean check = false;
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                productList.remove(product);
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Mã sản phẩm không tồn tại");
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    public void findProductById() {
        System.out.println("Nhập mã sản phẩm cần tìm ");
        String id = checkInput(PRODUCT_ID_REGEX);
        boolean check = false;
        for (Product product : productList) {
            if (id.equals(product.getProductId())) {
                showInfoSpecifications(product);
                System.out.println();
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Không tồn tại mã sản phẩm này");
        }
    }

    public void sortInfoPriceMaxMin() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = productList.size() - 1; j > i; j--) {
                if (productList.get(i).getImportPrice().compareTo(productList.get(j).getImportPrice()) < 0) {
                    Collections.swap(productList, i, j);
                }
            }
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    public void sortInfoPriceMinMax() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = productList.size() - 1; j > i; j--) {
                if (productList.get(i).getImportPrice().compareTo(productList.get(j).getImportPrice()) > 0) {
                    Collections.swap(productList, i, j);
                }
            }
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
    }

    public void sortInfoMFG() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = productList.size() - 1; j > i; j--) {
                if (productList.get(i).getMfg().compareTo(productList.get(j).getMfg()) > 0) {
                    Collections.swap(productList, i, j);
                }
            }
        }
        readWriteDataFile.writeDataToFile(PATH, productList);
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

    public void menu() {
        draw();
        System.out.printf(EDIT_PRINTF + "\n", "", "1. Sửa năm sản xuất", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "2. Sửa tên sản phẩm", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "3. Sửa nhà sản xuất", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "4. Sửa màu sắc", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "5. Sửa giá bán", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "6. Sửa toàn bộ thông tin", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "0. Quay lại", "");
        draw();
    }

    public void menuElectricMotorcylcle() {
        System.out.printf(EDIT_PRINTF + "\n", "", "1. Sửa quãng đường di chuyển tối đa", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "2. Sửa thời gian sạc", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "3. Sửa công suất", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "4. Sửa loại pin", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "0. Thoát", "");
    }

    public void menuMotorbike() {
        draw();
        System.out.printf(EDIT_PRINTF + "\n", "", "1. Sửa dung tích xi lanh", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "2. Sửa tốc độ tối đa", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "3. Sửa mức tiêu thụ nhiên liệu", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "4. Sửa dung tích bình xăng", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "0. Thoát", "");
        draw();
    }

    public void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
