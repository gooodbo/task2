import java.util.Collection;
import java.util.Comparator;

public interface MyLinkedList<T> {

    boolean add(T t);

    void add(int index, T t);

    boolean addAll(Collection<T> c);

    boolean addAll(int index, Collection<T> c);

    void addFirst(T t);

    void addLast(T t);

    T get(int index);

    T getFirst();

    T getlast();

    boolean remove(Object o);

    T remove(int index);

    T removeFirst();

    T removelast();

    T set(int index, Object o);

    int size();

    void sort(Comparator<T> comparator);

    void clear();
}
