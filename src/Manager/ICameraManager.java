package Manager;

import java.util.List;

public interface ICameraManager<E> {
    public void add(E e);

    public void edit(int id, E e);

    public void delete(int id);

    public int searchIndexById(int id);

    public List<E> searchByName(String name);

    public List<E> searchByCompany(String companyName);

    public List<E> showAll();
    public List<E> showAllBody();
    public List<E> showAllLens();
    public E searchProductById(int id);

}
