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
        for (Cart cart : listCameraCart
        ) {
            if (cart.getUserNameUser().equals(string)) {
                for (Camera camera : cart.getListProductInCart()
                ) {
                    if (camera.getId() == id) {
                        for (int i = 0; i < cart.getListProductInCart().size(); i++) {
                            if (id == cart.getListProductInCart().get(i).getId()) {
                                index = i;
                            }
                        }
                    }
                }
            }
            cart.getListProductInCart().remove(index);
        }

    }

    public int searchIndexById(int id) {
        for (int i = 0; i < listCameraCart.size(); i++) {
            if (id == listCameraCart.get(i).getId()) {
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
