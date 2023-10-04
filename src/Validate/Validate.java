package Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validate {
    static Scanner input = new Scanner(System.in);

    public static String inputEmailAccount() {
        String regex = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
        String account;
        do {
            account = input.nextLine();
            if (account.matches(regex)) {
                break;
            } else {
                System.out.println("sai định dạng email");
            }
        } while (true);
        return account;
    }

    public static String inputPassWord() {
        String regex = "^[a-zA-Z0-9!@#$%^&*()_+-={}|,.<>/?]{6,}$";
        String password;
        do {
            password = input.nextLine();
            if (password.matches(regex)) {
                break;
            } else {
                System.out.println("mật khẩu tối thiểu 6 kí tự");
            }
        } while (true);
        return password;
    }

    public static int checkInt() {
        int inputInterger;
        do {
            try {
                inputInterger = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai vui lòng nhập lại kiểu số:");
            }
        } while (true);
        return inputInterger;
    }

    public static Date checkDate() {
        Date inputDate;
        do {
            try {
                inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Nhập sai vui lòng nhập lại ngay tháng:");
            }
        } while (true);
        return inputDate;
    }
}
