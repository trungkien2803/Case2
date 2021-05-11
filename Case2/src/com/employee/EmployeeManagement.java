package com.employee;

import com.company.ReadWriteFile;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class EmployeeManagement {
    private static final String ID = "Mã nhân viên";
    private static final String FULL_NAME = "Tên nhân viên";
    private static final String AGE = "Tuổi";
    private static final String ADDRESS = "Địa chỉ";
    private static final String PHONE_NUMBER = "Số điện thoại";
    private static final String SALARY = "Lương cứng";
    private static final String BONUS = "Tiền thưởng";
    private static final String FINE = "Tiền phạt";
    private static final String REAL_WAGES = "Lương thực lĩnh";
    private static final String ID_REGEX = "^[A-Z]{2}\\d{2}";
    private static final String FULL_NAME_REGEX = "\\w*";
    private static final String AGE_REGEX = "\\d{1,3}";
    private static final String ADDRESS_REGEX = "(.*)";
    private static final String PHONE_NUMBER_REGEX = "\\d{10,11}";
    private static final String SALARY_REGEX = "\\d*";
    private static final String EDIT_PRINTF = "%-70s%-30s%s";
    private static final String EXAMPLE_ID = "(VD:AA11)";
    private static final String EXAMPLE_FULL_NAME = "(VD:TranTrungKien)";
    private static final String EXAMPLE_AGE = "(VD:21)";
    private static final String EXAMPLE_ADDRESS = "(VD:HaTinh)";
    private static final String EXAMPLE_PHONE_NUMBER = "(VD:1234567890)";
    private static final String EXAMPLE_SALARY = "(VD:20000000)";
    private static final String LIST_EMPLOYEE_TXT = "listEmployee.txt";
    private static List<Employee> employeeList = new ArrayList<>();
    private static final ReadWriteFile<Employee> readWriteDataFile = new ReadWriteFile<>();
    private static final Scanner sc = new Scanner(System.in);


    public EmployeeManagement() {

    }

    public ReadWriteFile<Employee> getReadWriteDataFile() {
        return readWriteDataFile;
    }

    public String getListEmployeeTxt() {
        return LIST_EMPLOYEE_TXT;
    }



    public void setEmployees() {
        employeeList = readWriteDataFile.readDataFromFile(LIST_EMPLOYEE_TXT);
    }

//    public String getACCOUNT_REGEX() {
//        return ACCOUNT_REGEX;
//    }
//
//    public String getPASSWORD_REGEX() {
//        return PASSWORD_REGEX;
//    }

    public String getPath() {
        return LIST_EMPLOYEE_TXT;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }


    public void addInfo(Employee employee) {
        enterInfo(employee);
        employeeList.add(employee);
        readWriteDataFile.writeDataToFile(LIST_EMPLOYEE_TXT, employeeList);
    }

    public void enterInfo(Employee employee) {
        draw();
        System.out.println(ID + EXAMPLE_ID);
        employee.setId(checkInput(ID_REGEX));
        checkEnteredId(employee);
        System.out.println(FULL_NAME + EXAMPLE_FULL_NAME);
        employee.setFullName(checkInput(FULL_NAME_REGEX));
        System.out.println(AGE + EXAMPLE_AGE);
        employee.setAge(checkInput(AGE_REGEX));
        System.out.println(ADDRESS + EXAMPLE_ADDRESS);
        employee.setAddress(checkInput(ADDRESS_REGEX));
        System.out.println(PHONE_NUMBER + EXAMPLE_PHONE_NUMBER);
        employee.setPhoneNumber(checkInput(PHONE_NUMBER_REGEX));
        System.out.println(SALARY + EXAMPLE_SALARY);
        employee.setSalary(checkInput(SALARY_REGEX));
        System.out.println(BONUS + EXAMPLE_SALARY);
        employee.setBonus(checkInput(SALARY_REGEX));
        System.out.println(FINE + EXAMPLE_SALARY);
        employee.setFine(checkInput(SALARY_REGEX));
    }

    private void checkEnteredId(Employee employee) {
        while (true) {
            int index = 0;
            if (employeeList != null){
                for (Employee value : employeeList) {
                    if (employee.getId().equals(value.getId())) {
                        System.err.println("Mã nhân viên đã tồn tại");
                        employee.setId(checkInput(ID_REGEX));
                        index++;
                    }
                }
            }
            if (index == 0) {
                break;
            }
        }
    }

    public void showList() {
        setEmployees();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "DANH SÁCH NHÂN VIÊN", "");
        if (checkFile(LIST_EMPLOYEE_TXT).length() > 0) {
            for (Employee employee : employeeList) {
                showInfo(employee);
                System.out.println();
            }
        } else {
            System.out.print("");
        }

        System.out.print("\n\n");
        draw();
    }

    public void showInfo(Employee employee) {
        if (employee instanceof Manager) {
            System.out.printf(ID + ": %-5s, " + FULL_NAME + ": %-15s " + AGE + ": %-4s, " + ADDRESS + ": %-10s, " + PHONE_NUMBER + ": %-10s, " + REAL_WAGES + ":%-15s" + "QUẢN LÍ", employee.getId(), employee.getFullName(), employee.getAge(), employee.getAddress(), employee.getPhoneNumber(), employee.getRealWages() + "(vnd)");
        } else if (employee instanceof Accountant) {
            System.out.printf(ID + ": %-5s, " + FULL_NAME + ": %-15s " + AGE + ": %-4s, " + ADDRESS + ": %-10s, " + PHONE_NUMBER + ": %-10s, " + REAL_WAGES + ":%-15s" + "KẾ TOÁN", employee.getId(), employee.getFullName(), employee.getAge(), employee.getAddress(), employee.getPhoneNumber(), employee.getRealWages() + "(vnd)");
        } else if (employee instanceof Salesman) {
            System.out.printf(ID + ": %-5s, " + FULL_NAME + ": %-15s " + AGE + ": %-4s, " + ADDRESS + ": %-10s, " + PHONE_NUMBER + ": %-10s, " + REAL_WAGES + ":%-15s" + "NHÂN VIÊN BÁN HÀNG", employee.getId(), employee.getFullName(), employee.getAge(), employee.getAddress(), employee.getPhoneNumber(), employee.getRealWages() + "(vnd)");
        } else if (employee instanceof Technicians) {
            System.out.printf(ID + ": %-5s, " + FULL_NAME + ": %-15s " + AGE + ": %-4s, " + ADDRESS + ": %-10s, " + PHONE_NUMBER + ": %-10s, " + REAL_WAGES + ":%-15s" + "KĨ THUẬT VIÊN", employee.getId(), employee.getFullName(), employee.getAge(), employee.getAddress(), employee.getPhoneNumber(), employee.getRealWages() + "(vnd)");
        } else if (employee instanceof Security) {
            System.out.printf(ID + ": %-5s, " + FULL_NAME + ": %-15s " + AGE + ": %-4s, " + ADDRESS + ": %-10s, " + PHONE_NUMBER + ": %-10s, " + REAL_WAGES + ":%-15s" + "BẢO VỆ", employee.getId(), employee.getFullName(), employee.getAge(), employee.getAddress(), employee.getPhoneNumber(), employee.getRealWages() + "(vnd)");
        }
    }
    public void showSalesPerformance() {
        setEmployees();
        draw();
        System.out.printf("%-70s%-30s%s\n\n\n", "", "THÀNH TÍCH BÁN HÀNG", "");
        if (checkFile(LIST_EMPLOYEE_TXT).length() > 0) {
            for (Employee employee : employeeList) {
                if (employee instanceof Salesman){
                    System.out.printf("%50s%30s%30s\n",employee.getId(),employee.getFullName(),((Salesman) employee).getSalesPerformance());
                }
            }
        } else {
            System.out.print("");
        }
        System.out.print("\n\n");
        draw();
    }

    public void editInfoById() {
        System.out.print("Nhập mã nhân viên cần sửa ");
        boolean check = false;
        String id = sc.next();
        draw();
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                editInfo(employee);
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Mã nhân viên không tồn tại");
        }
        readWriteDataFile.writeDataToFile(LIST_EMPLOYEE_TXT, employeeList);
    }

    public void editInfo(Employee employee) {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            this.menuEditInfo();
            choice = sc.next();
            switch (choice) {
                case "1": {
                    System.out.println(FULL_NAME + EXAMPLE_FULL_NAME);
                    String fullName = checkInput(FULL_NAME_REGEX);
                    employee.setFullName(fullName);
                    break;
                }
                case "2": {
                    System.out.println(AGE + EXAMPLE_AGE);
                    String age = checkInput(AGE_REGEX);
                    employee.setAge(age);
                    break;
                }
                case "3": {
                    System.out.println(ADDRESS + EXAMPLE_ADDRESS);
                    String address = checkInput(ADDRESS_REGEX);
                    employee.setAddress(address);
                    break;
                }
                case "4": {
                    System.out.println(PHONE_NUMBER + EXAMPLE_PHONE_NUMBER);
                    String phoneNumber = checkInput(PHONE_NUMBER_REGEX);
                    employee.setPhoneNumber(phoneNumber);
                    break;
                }
                case "5": {
                    System.out.println(SALARY + EXAMPLE_SALARY);
                    String salary = checkInput(SALARY_REGEX);
                    employee.setSalary(salary);
                    break;
                }
                case "6": {
                    System.out.println(BONUS + EXAMPLE_SALARY);
                    String bonus = checkInput(SALARY_REGEX);
                    employee.setBonus(bonus);
                    break;
                }
                case "7": {
                    System.out.println(FINE + EXAMPLE_SALARY);
                    String fine = checkInput(SALARY_REGEX);
                    employee.setFine(fine);
                    break;
                }
                case "8": {
                    System.out.println(FULL_NAME + EXAMPLE_FULL_NAME);
                    String fullName = checkInput(FULL_NAME_REGEX);
                    employee.setFullName(fullName);
                    System.out.println(AGE + EXAMPLE_AGE);
                    String age = checkInput(AGE_REGEX);
                    employee.setAge(age);
                    System.out.println(ADDRESS + EXAMPLE_ADDRESS);
                    String address = checkInput(ADDRESS_REGEX);
                    employee.setAddress(address);
                    System.out.println(PHONE_NUMBER + EXAMPLE_PHONE_NUMBER);
                    String phoneNumber = checkInput(PHONE_NUMBER_REGEX);
                    employee.setPhoneNumber(phoneNumber);
                    System.out.println(SALARY + EXAMPLE_SALARY);
                    String salary = checkInput(SALARY_REGEX);
                    employee.setSalary(salary);
                    System.out.println(BONUS + EXAMPLE_SALARY);
                    String bonus = checkInput(SALARY_REGEX);
                    employee.setBonus(bonus);
                    System.out.println(FINE + EXAMPLE_SALARY);
                    String fine = checkInput(SALARY_REGEX);
                    employee.setFine(fine);
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
            readWriteDataFile.writeDataToFile(getListEmployeeTxt(),employeeList);
        } while (!choice.equals("0"));
    }

    public void deleteInfo() {
        System.out.print("Nhập mã nhân viên cần xóa ");
        String id = sc.next();
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                employeeList.remove(employee);
                break;
            }
        }
        readWriteDataFile.writeDataToFile(LIST_EMPLOYEE_TXT, employeeList);
    }

    public void sortSalary() {
        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = employeeList.size() - 1; j > i; j--) {
                if (employeeList.get(i).getRealWages() < (employeeList.get(j).getRealWages())) {
                    Collections.swap(employeeList, i, j);
                }
            }
        }
        readWriteDataFile.writeDataToFile(LIST_EMPLOYEE_TXT, employeeList);
    }

    public Double getTotalSalrary() {
        double sum = 0;
        setEmployees();
        if (employeeList != null){
            for (Employee employee : employeeList) {
                sum += employee.getRealWages();
            }
        }
        return sum;
    }

    public void findEmployeeById() {
        System.out.println("Nhập mã nhân viên cần tìm ");
        String id = checkInput(ID_REGEX);
        boolean check = false;
        for (Employee employee : employeeList) {
            if (id.equals(employee.getId())) {
                showInfo(employee);
                System.out.println();
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Không tồn tại mã id này");
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

    public File checkFile(String path) {
        return new File(path);
    }

    public void menuEditInfo() {
        draw();
        System.out.printf(EDIT_PRINTF + "\n", "", "1. Sửa tên", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "2. Sửa tuổi", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "3. Sửa địa chỉ", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "4. Sửa số điện thoại", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "5. Sửa tiền lương", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "6. Sửa tiền thưởng", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "7. Sửa tiền phạt", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "8. Sửa toàn bộ thông tin", "");
        System.out.printf(EDIT_PRINTF + "\n", "", "0. Quay lại", "");
        draw();
    }

    public void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
