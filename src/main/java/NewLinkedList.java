import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

public class NewLinkedList<T> implements MyLinkedList<T> {
    // First node
    private Node<T> first;
    // Last node
    private Node<T> last;
    private int size = 0;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(T value, Node<T> next, Node<T> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

    }

    //Adds element in the end of list
    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public void add(int index, T t) {

    }

    @Override
    public boolean addAll(Collection<T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<T> c) {
        return false;
    }


    // Adds element in head of list
    @Override
    public void addFirst(T t) {
        Node<T> newNode = new Node<>(t, null, null);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
        size++;
    }

    // Adds element in end of list
    @Override
    public void addLast(T t) {
        Node<T> newNode = new Node(t, null, null);
        if (last == null) {
            last = newNode;
            first = newNode;
        } else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    // Return element from list by index
    @Override
    public T get(int index) {
        int i = -1;
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        Node<T> f = first;
        while (f != null) {
            i++;
            if (i == index) {
                return f.value;
            }
            f = f.next;
        }
        return null;
    }

    //Return first element in the list
    @Override
    public T getFirst() {
        Node<T> f = first;
        if (f != null) {
            return f.value;
        }
        return null;
    }

    //Return last element in the list
    @Override
    public T getLast() {
        Node<T> l = last;
        if (l != null) {
            return l.value;
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    public T remove(int index) {
        return null;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T set(int index, Object o) {
        return null;
    }

    // Return size
    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort(Comparator<T> comparator) {

    }

    //Removes all elements from list
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    // Return true if List is empty and false if it doesn't empty
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

}
