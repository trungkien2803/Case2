package com.account;

import com.company.ReadWriteFile;
import com.employee.EmployeeManagement;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account extends Thread{
    private static final String EDIT_PRINTF = "%-70s%-30s%s";
    private static final String ENTER_ACCOUNT = "Nhập tài khoản";
    private static final String ENTER_PASSWORD = "Nhập mật khẩu";
    private static final String ACCOUNT_MANAGER_TXT = "accountManager.txt";
    private static final String ACCOUNT_ACCOUNTANT_TXT = "accountAccountant.txt";
    private static final String ACCOUNT_REGEX = "\\w*";
    private static final String PASSWORD_REGEX = "[A-Z]*[a-z]*\\d*";
    private final ReadWriteFile<String> readWriteAccountFile = new ReadWriteFile<>();
    private Map<String, String> accountManagerList = new HashMap<>();
    private Map<String, String> accountAccountantList = new HashMap<>();

    public Account() {
        File fileManager = new File(ACCOUNT_MANAGER_TXT);
        File fileAccountant = new File(ACCOUNT_ACCOUNTANT_TXT);
        if (fileManager.length() == 0) {
            accountManagerList.put("kien2803", "Kien2803");
            readWriteAccountFile.writeMapToFile(ACCOUNT_MANAGER_TXT, accountManagerList);
        }
        if (fileAccountant.length() == 0) {
            accountAccountantList.put("tk2803", "Tk2803");
            readWriteAccountFile.writeMapToFile(ACCOUNT_ACCOUNTANT_TXT, accountAccountantList);
        }
        if (fileManager.length() > 0) {
            accountManagerList = readWriteAccountFile.readMapFromFile(ACCOUNT_MANAGER_TXT);
        }
        if (fileAccountant.length() > 0) {
            accountAccountantList = readWriteAccountFile.readMapFromFile(ACCOUNT_ACCOUNTANT_TXT);
        }
    }
    public void setPasswordManager(String account, String newPassword) {
        accountManagerList.replace(account, newPassword);
        readWriteAccountFile.writeMapToFile(ACCOUNT_MANAGER_TXT, accountManagerList);
    }

    public void setPasswordAccountant(String account, String newPassword) {
        accountAccountantList.replace(account, newPassword);
        readWriteAccountFile.writeMapToFile(ACCOUNT_ACCOUNTANT_TXT, accountAccountantList);
    }

    public void addAccountManager(String account, String password) {
        accountManagerList.put(account, password);
        readWriteAccountFile.writeMapToFile(ACCOUNT_MANAGER_TXT, accountManagerList);
    }

    public Map<String, String> getAccountManagerList() {
        return accountManagerList;
    }

    public Map<String, String> getAccountAccountantList() {
        return accountAccountantList;
    }
    public void addAccountAccountant(String account, String password) {
        accountAccountantList.put(account, password);
        readWriteAccountFile.writeMapToFile(ACCOUNT_ACCOUNTANT_TXT, accountAccountantList);
    }

    public String login() {
        Scanner sc = new Scanner(System.in);
        String account;
        String password;
        int count = 5;
        while (true) {
            draw();
            System.out.printf(EDIT_PRINTF + "\n", "", "ĐĂNG NHẬP", "");
            System.out.printf(EDIT_PRINTF, "", "Nhập tài khoản", "");
            account = sc.next();
            System.out.printf(EDIT_PRINTF, "", "Nhập mật khẩu", "");
            password = sc.next();
            boolean checkManager = accountManagerList.containsKey(account) && accountManagerList.get(account).equals(password);
            boolean checkAccountant = accountAccountantList.containsKey(account) && accountAccountantList.get(account).equals(password);
            if (checkManager) {
                return "Manager";
            } else if (checkAccountant) {
                return "Accountant";
            } else {
                if (!accountManagerList.containsKey(account) && !accountAccountantList.containsKey(account)) {
                    System.err.printf(EDIT_PRINTF + "\n", "", "Tài khoản không tồn tại", "");
                } else if (!accountManagerList.get(account).equals(password) || !accountAccountantList.get(account).equals(password)) {
                    System.err.printf(EDIT_PRINTF + "\n", "", "Mật khẩu không đúng", "");
                    count--;
                    System.err.printf(EDIT_PRINTF + "\n", "", "Bạn còn " + count + " lần nhập", "");
                }
                if (count == 0) {
                    try {
                        System.err.printf(EDIT_PRINTF+"\n","","Bạn đã nhập sai quá nhiều lần, vui lòng đợi để thử lại","");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "false";
                }
            }
            draw();
        }
    }

    public void signUpManagerAccount() {
        System.out.printf(EDIT_PRINTF, "", ENTER_ACCOUNT, "");
        String newAccount = checkInput(ACCOUNT_REGEX);
        checkEnteredId(accountManagerList,ACCOUNT_MANAGER_TXT,newAccount);
        System.out.printf(EDIT_PRINTF, "", ENTER_PASSWORD, "");
        String newPassword = checkInput(PASSWORD_REGEX);
        this.addAccountManager(newAccount, newPassword);
    }

    public void changePassword(EmployeeManagement employeeManagement, Account account, Scanner sc, String login) {
        while (true) {
            System.out.printf(EDIT_PRINTF + "\n", "", "Nhập tài khoản cần thay đổi", "");
            String oldAccount = checkInput(ACCOUNT_REGEX);
            System.out.printf(EDIT_PRINTF, "", "Mật khẩu cũ", "");
            String oldPassword = sc.next();
            if (oldPassword.equals(account.getAccountManagerList().get(oldAccount))) {
                System.out.printf(EDIT_PRINTF + "\n", "", "Mật khẩu mới (Có chữ hoa, chữ thường và số)", "");
                String newPassword = checkInput(PASSWORD_REGEX);
                account.setPasswordManager(login, newPassword);
                break;
            } else if (oldPassword.equals(account.getAccountAccountantList().get(oldAccount))) {
                System.out.printf(EDIT_PRINTF + "\n", "", "Mật khẩu mới (Có chữ hoa, chữ thường và số)", "");
                String newPassword = employeeManagement.checkInput(PASSWORD_REGEX);
                account.setPasswordAccountant(login, newPassword);
                break;
            }
        }
    }

    public void signUpAccountAccountant() {
        System.out.printf(EDIT_PRINTF, "", ENTER_ACCOUNT, "");
        String newAccount = checkInput(ACCOUNT_REGEX);
        checkEnteredId(accountAccountantList,ACCOUNT_ACCOUNTANT_TXT,newAccount);
        System.out.printf(EDIT_PRINTF, "", ENTER_PASSWORD, "");
        String newPassword = checkInput(PASSWORD_REGEX);
        this.addAccountAccountant(newAccount, newPassword);
    }
    private void checkEnteredId(Map<String,String> map,String path, String account) {
        while (true) {
            int index = 0;
            map = readWriteAccountFile.readMapFromFile(path);
            if (map.containsKey(account)){
                System.err.printf(EDIT_PRINTF,"","Tài khoản đã tồn tại","");
                account = checkInput(ACCOUNT_REGEX);
                index++;
            }
            if (index == 0){
                break;
            }

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

    public void draw() {
        for (int i = 0; i < 170; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
