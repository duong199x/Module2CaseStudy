package Account;

import cart.Cart;
import Model.Camera;
import Validate.Validate;
import View.Menu;
import cart.CartManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAccount {
    ManagerAccount managerAccount = new ManagerAccount();
    public final int roleUser = 2;
    Scanner inputString = new Scanner(System.in);
    CartManager cartManager = new CartManager();
    Menu menu = new Menu(cartManager);

    public void MenuLogin() {
        int choice;
        do {
            System.out.println("|______Menu Đăng Nhập______|\n|1.Đăng nhập_______________|\n|2.Đăng kí_________________|\n|0.thoát___________________|\n|__________________________|");
            System.out.println("Nhập lựa chọn của bạn:");
            choice = Validate.checkInt();
            switch (choice) {
                case 1:
                    showMenuLogin();
                    break;
                case 2:
                    showMenuRegister();
                    break;
            }

        } while (choice != 0);
    }

    private void showMenuRegister() {
        System.out.println("____Menu Đăng Kí____");
        System.out.println("Nhập email:");
        String email = Validate.inputEmailAccount();
        System.out.println("Nhập mật khẩu:");
        String password = Validate.inputPassWord();
        System.out.println("Nhập tên người dùng:");
        String userName = inputString.nextLine();
        System.out.println("Nhập số điện thoại:");
        String phoneNumber = inputString.nextLine();
        System.out.println("Nhập địa chỉ:");
        String address = inputString.nextLine();
        Account account = new Account(email, password, userName, phoneNumber, address, roleUser);
        managerAccount.register(account);
        Cart cart = new Cart(email);
        cartManager.addToCart(cart);
        System.out.println("===> đăng kí thành công");
    }

    private void showMenuLogin() {
        System.out.println("____Menu Đăng nhập____");
        System.out.println("Nhập email:");
        String email = Validate.inputEmailAccount();
        System.out.println("Nhập mật khẩu:");
        String password = Validate.inputPassWord();
        if (managerAccount.checkAdmin(email, password)) {
            menu.MainMenu(ManagerAccount.currentUser);
        } else if (managerAccount.checkUser(email, password)) {
            menu.MenuUser(ManagerAccount.currentUser);
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu! Bạn hãy đăng nhập lại");
        }
    }
}
