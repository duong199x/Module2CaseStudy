package cart;

import Model.Camera;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private String userNameUser;
    private List<Camera> listProductInCart;
    private static int idIncrement = 1;

    public Cart(String userNameUser) {
        this.id = idIncrement;
        this.userNameUser = userNameUser;
        this.listProductInCart = new ArrayList<>();
        idIncrement++;
    }

    public Cart(int id, String userNameUser, List<Camera> listProductInCart) {
        this.id = idIncrement;
        this.userNameUser = userNameUser;
        this.listProductInCart = listProductInCart;
        idIncrement++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNameUser() {
        return userNameUser;
    }

    public void setUserNameUser(String userNameUser) {
        this.userNameUser = userNameUser;
    }

    public List<Camera> getListProductInCart() {
        return listProductInCart;
    }

    public void setListProductInCart(List<Camera> listProductInCart) {
        this.listProductInCart = listProductInCart;
    }

    public double Sum() {
        double sum = 0;
        for (int i = 0; i < listProductInCart.size(); i++) {
            sum += listProductInCart.get(i).getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userNameUser='" + userNameUser + '\'' +
                ", listProductInCart=" + listProductInCart +
                '}';
    }

    public int searchIndexByIdInCart(int id) {
        for (int i = 0; i < listProductInCart.size(); i++) {
            if (listProductInCart.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
