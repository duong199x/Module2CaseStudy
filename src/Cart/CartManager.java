package Cart;

import Model.Camera;

import java.util.ArrayList;
import java.util.List;

public class CartManager implements ICartManager<Cart> {
    List<Cart> listCameraCart = new ArrayList<>();

    public void addToCart(Cart cart) {
        listCameraCart.add(cart);
    }

    public void deleteInCart(int id) {
        int index = searchIndexById(id);
        listCameraCart.remove(index);
    }

    public int searchIndexById(int id) {
        for (int i = 0; i < listCameraCart.size(); i++) {
            if (id == listCameraCart.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public List<Cart> showAllProductInCart(String string) {
        List<Cart> listCart = new ArrayList<>();
        for (Cart cart : listCameraCart
        ) {
            if (cart.getUserNameUser().equals(string)) {
                listCart.add(cart);
            }
        }
        return listCart;
    }
}
