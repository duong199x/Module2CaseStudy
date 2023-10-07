package View;

import Account.Account;
import cart.Cart;
import cart.CartManager;
import Manager.CameraManager;
import Model.Body;
import Model.Camera;
import Model.Lens;
import Validate.Validate;
import dto.ReadAnfWriteBill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner inputString = new Scanner(System.in);
    CameraManager cameraManager = new CameraManager();
    CartManager cartManager;
    Account currentAccount;
    ReadAnfWriteBill readAnfWriteBill;

    public Menu(CartManager cartManager) {
        this.cartManager = cartManager;
        readAnfWriteBill = new ReadAnfWriteBill();
    }

    public void MainMenu(Account account) {
        currentAccount = account;
        System.out.println(currentAccount);
        int choice;
        do {
            System.out.println("|_______________Menu Admin__________________|\n|1.Thêm thông tin máy ảnh___________________|\n|2.Sửa thông tin máy ảnh____________________|\n|3.Xóa thông tin mấy ảnh____________________|\n|4.Tìm kiếm máy ảnh theo tên________________|\n|5.Tìm kiếm máy ảnh theo công ty____________|\n|6.Hiển thị thông tin máy ảnh hoặc ống kinh_|\n|7.Hiển thị các loại máy ảnh trong cửa hàng_|\n|8.Hiển thị các loại ống kính trong cửa hàng|\n|9.Hiển thị thông tin mua hàng______________|\n|0.thoát____________________________________|\n|___________________________________________|");
            System.out.println("Nhập lựa chọn của bạn:");
            choice = Validate.checkInt();
            switch (choice) {
                case 1:
                    showMenuAddCamera();
                    break;
                case 2:
                    showMenuEditCamera();
                    break;
                case 3:
                    showMenuDeleteCamera();
                    break;
                case 4:
                    showMenuSearchByName();
                    break;
                case 5:
                    showMenuSearchByCompany();
                    break;
                case 6:
                    showAllProduct();
                    break;
                case 7:
                    showAllBody();
                    break;
                case 8:
                    showAllLens();
                    break;
                case 9:
                    checkBill();
                    break;
            }

        } while (choice != 0);
    }

    private void checkBill() {
        System.out.println(readAnfWriteBill.ReadBill());

    }

    public void MenuUser(Account account) {
        currentAccount = account;
        System.out.println(currentAccount);
        int choice;
        do {
            System.out.println("|________________Menu User__________________|\n|1.Tìm kiếm máy ảnh theo tên________________|\n|2.Tìm kiếm máy ảnh theo công ty____________|\n|3.Hiển thị thông tin máy ảnh hoặc ống kinh_|\n|4.Hiển thị các loại máy ảnh trong cửa hàng_|\n|5.Hiển thị các loại ống kính trong cửa hàng|\n|6.Thêm sản phẩm vào giỏ hàng_______________|\n|7.Xóa sản phẩm trong giỏ hàng______________|\n|8.Kiểm tra sản phẩm trong giỏ hàng_________|\n|9.Buy______________________________________|\n|0.thoát____________________________________|\n|___________________________________________|");
            System.out.println("Nhập lựa chọn của bạn:");
            choice = Validate.checkInt();
            switch (choice) {
                case 1:
                    showMenuSearchByName();
                    break;
                case 2:
                    showMenuSearchByCompany();
                    break;
                case 3:
                    showAllProduct();
                    break;
                case 4:
                    showAllBody();
                    break;
                case 5:
                    showAllLens();
                    break;
                case 6:
                    addProductToListCart(currentAccount);
                    break;
                case 7:
                    deleteProductInCart(currentAccount);
                    break;
                case 8:
                    showAllCartProduct(currentAccount);
                    break;
                case 9:
                    buy(currentAccount, cartManager.showCart(account.getEmail()));
                    break;
            }

        } while (choice != 0);
    }

    private void buy(Account currentAccount, Cart cart) {
        readAnfWriteBill.writeFile(currentAccount, cart);
        System.out.println("===> Mua thành công");
    }


    private void deleteProductInCart(Account account) {
        System.out.println("Nhập id bạn muốn  xóa trong giỏ hàng");
        int idDeleteCart;
        do {
            System.out.println("Nhập id");
            idDeleteCart = Validate.checkInt();
            if (cameraManager.checkIdInList(idDeleteCart)) {
                break;
            } else {
                System.out.println("Id không tồn tại, vui lòng nhập lại");
            }
        } while (true);
        cartManager.deleteProductInCard(idDeleteCart, account.getEmail());
        System.out.println("===> xóa sản phẩm trong giỏ hàng thành công");
    }

    private void showAllCartProduct(Account account) {
        //String emailAccount = account.getEmail();
        Cart cart = cartManager.showCart(account.getEmail());
        System.out.println(cart);
    }

    private void addProductToListCart(Account account) {
        String emailAccount = account.getEmail();
        System.out.println("Nhập id sản phẩm muốn thêm vào giỏ hàng");
        int id;
        do {
            System.out.println("Nhập id");
            id = Validate.checkInt();
            if (cameraManager.checkIdInList(id)) {
                break;
            } else {
                System.out.println("Id không tồn tại, vui lòng nhập lại");
            }
        } while (true);
        Camera product = cameraManager.searchProductById(id);
        cartManager.addProductToCard(emailAccount, product);
        System.out.println("===> Thêm vào giỏ hàng thành công");
    }

    private void showMenuAddCamera() {
        System.out.println("|_______Menu Add_______|");
        int id;
        do {
            System.out.println("Nhập id");
            id = Validate.checkInt();
            if (cameraManager.checkIdInList(id)) {
                System.out.println("Id đã tồn tại vui lòng nhập id khác");
            } else {
                break;
            }
        } while (true);
        System.out.println("Nhập tên");
        String name = inputString.nextLine();
        System.out.println("Nhập ngày ra mắt");
        Date date = Validate.checkDate();
        System.out.println("Nhập công ty sản xuất");
        String companyProduction = inputString.nextLine();
        System.out.println("Nhập giá sản phẩm");
        int price = Validate.checkInt();
        System.out.println("---chọn 1 nếu nhập thông tin thân máy\n---chọn 2 nếu nhập thông tin ống kính");
        int choice = Validate.checkInt();
        switch (choice) {
            case 1:
                System.out.println("Nhập loại sensor (Crop hoặc FF)");
                String sensorType = inputString.nextLine();
                System.out.println("Nhập loại Thân máy(DSLR hoặc Miroless):");
                String bodyType = inputString.nextLine();
                Body body = new Body(id, name, date, companyProduction, price, choice, sensorType, bodyType);
                cameraManager.add(body);
                break;
            case 2:
                System.out.println("Nhập Loại ống kính:");
                String lensMount = inputString.nextLine();
                System.out.println("Nhập kiểu lấy nét (AF hoặc MF):");
                String focus = inputString.nextLine();
                Lens lens = new Lens(id, name, date, companyProduction, price, choice, lensMount, focus);
                cameraManager.add(lens);
                break;
        }
        System.out.println("===> Thêm thành công");
    }

    private void showMenuEditCamera() {
        System.out.println("|_______Menu Edit_______|");
        int id;
        do {
            System.out.println("Nhập id");
            id = Validate.checkInt();
            if (cameraManager.checkIdInList(id)) {
                break;
            } else {
                System.out.println("Id không tồn tại, vui lòng nhập lại");
            }
        } while (true);
        System.out.println("Nhập tên");
        String name = inputString.nextLine();
        System.out.println("Nhập ngày ra mắt");
        Date date = Validate.checkDate();
        System.out.println("Nhập công ty sản xuất");
        String companyProduction = inputString.nextLine();
        System.out.println("Nhập giá sản phẩm");
        int price = Validate.checkInt();
        System.out.println("---chọn 1 nếu sửa thông tin thân máy\n---chọn 2 nếu sửa thông tin ống kính");
        int choice = Validate.checkInt();
        switch (choice) {
            case 1:
                System.out.println("Nhập loại sensor (Crop hoặc FF)");
                String sensorType = inputString.nextLine();
                System.out.println("Nhập loại Thân máy(DSLR hoặc Miroless):");
                String bodyType = inputString.nextLine();
                Body body = new Body(id, name, date, companyProduction, price, choice, sensorType, bodyType);
                cameraManager.edit(id, body);
                break;
            case 2:
                System.out.println("Nhập Loại ống kính:");
                String lensMount = inputString.nextLine();
                System.out.println("Nhập kiểu lấy nét (AF hoặc MF):");
                String focus = inputString.nextLine();
                Lens lens = new Lens(id, name, date, companyProduction, price, choice, lensMount, focus);
                cameraManager.edit(id, lens);
                break;
        }
        System.out.println("===> Sửa thành công");
    }

    private void showMenuDeleteCamera() {
        System.out.println("|_______Menu Delete_______|");
        int idDelete;
        do {
            System.out.println("Nhập id của sản phẩm muốn xóa:");
            idDelete = Validate.checkInt();
            if (cameraManager.checkIdInList(idDelete)) {
                break;
            } else {
                System.out.println("Id không tồn tại, vui lòng nhập lại");
            }
        } while (true);
        cameraManager.delete(idDelete);
        System.out.println("===> Xóa thành công");
    }

    private void showMenuSearchByName() {
        System.out.println("Nhập tên sản phẩm cần tìm:");
        String name = inputString.nextLine();
        List<Camera> showList = cameraManager.searchByName(name);
        for (Camera camera : showList
        ) {
            System.out.println(camera);
        }
    }

    private void showMenuSearchByCompany() {
        System.out.println("Nhập tên công ty cần tìm:");
        String companyName = inputString.nextLine();
        List<Camera> showList = cameraManager.searchByCompany(companyName);
        for (Camera camera : showList
        ) {
            System.out.println(camera);
        }
    }

    private void showAllProduct() {
        List<Camera> showList = cameraManager.showAll();
        for (Camera camera : showList
        ) {
            System.out.println(camera);
        }
    }

    private void showAllBody() {
        List<Camera> showList = cameraManager.showAllBody();
        for (Camera camera : showList
        ) {
            System.out.println(camera);
        }
    }

    private void showAllLens() {
        List<Camera> showList = cameraManager.showAllLens();
        for (Camera camera : showList
        ) {
            System.out.println(camera);
        }
    }


}
