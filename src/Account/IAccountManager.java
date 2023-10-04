package Account;

import java.util.List;

public interface IAccountManager<E> {

    public void register(E e);
    public List<E> showAllUser();
}
