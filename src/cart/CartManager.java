package cart;

import Account.Account;
import Manager.CameraManager;
import Model.Camera;
import dto.ReadAndWriteCart;

import java.util.ArrayList;
import java.util.List;

public class CartManager implements ICartManager<Cart> {
    List<Cart> listCameraCart;
    ReadAndWriteCart readAndWriteCart;

    public CartManager() {
        readAndWriteCart = new ReadAndWriteCart();
//        if (readAndWriteCart.ReadFile() != null) {
        listCameraCart = readAndWriteCart.ReadFile();
//        } else {
//            listCameraCart = new ArrayList<>();
//        }
    }

    public void addToCart(Cart cart) {
        listCameraCart.add(cart);
        readAndWriteCart.writeFile(listCameraCart);
    }

    public void addProductToCard(String string, Camera camera) {
        for (Cart cart : listCameraCart
        ) {
            if (cart.getUserNameUser().equals(string)) {
                cart.getListProductInCart().add(camera);
            }
        }
        readAndWriteCart.writeFile(listCameraCart);
    }

    public void deleteInCart(int id) {
        int index = searchIndexById(id);
        listCameraCart.remove(index);
    }

    public void deleteProductInCard(int id, String string) {
        int index = -1;
        for (int i = 0; i < listCameraCart.size(); i++) {
            if (listCameraCart.get(i).getUserNameUser().equals(string)) {
                for (int j = 0; j < listCameraCart.get(i).getListProductInCart().size(); j++) {
                    if (listCameraCart.get(i).getListProductInCart().get(j).getId() == id) {
                        index = listCameraCart.get(i).searchIndexByIdInCart(id);
                    }
                }
                listCameraCart.get(i).getListProductInCart().remove(index);
            }
        }
        readAndWriteCart.writeFile(listCameraCart);
    }

    public int searchIndexById(int id) {
        for (int i = 0; i < listCameraCart.size(); i++) {
            if (listCameraCart.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Cart showCart(String string) {
        readAndWriteCart.ReadFile();
        for (Cart cart : listCameraCart) {
            if (cart.getUserNameUser().equals(string)) {
                return cart;
            }
        }
        return null;
    }
}
