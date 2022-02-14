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

    T getLast();

    boolean remove(Object o);

    T remove(int index);

    T removeFirst();

    T removeLast();

    T set(int index, T o);

    int size();

    void sort(Comparator<T> comparator);

    void clear();

    boolean isEmpty();
}
