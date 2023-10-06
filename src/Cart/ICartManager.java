package Cart;

import java.util.List;

public interface ICartManager<E> {
    public void addToCart(E e);

    public void deleteInCart(int id);

    public int searchIndexById(int id);
    public List<E> showAllProductInCart(String string);

}
