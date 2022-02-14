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
        if ( index < size/2) {
            int i = 0;
            Node<T> node = first;
            while (i != index) {
                node = node.next;
                i++;
            }
            Node<T> newNode = new Node<>(t, node.next, node);
            node.next = newNode;
            newNode.next.previous = newNode;
            size++;
        }
        else {
            int i = size - 1;
            Node<T> node = last;
            while (i != index) {
                node = node.previous;
                i--;
            }
            Node<T> newNode = new Node<>(t, node, node.previous);
            node.previous = newNode;
            newNode.previous.next = newNode;
            size++;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> start = first;
        while (start.next != null){
            sb.append(start.value.toString() + ", ");
            start = start.next;
        }
        sb.append(start.value.toString() + "]");
        return sb.toString();
    }


    @Override
    public boolean addAll(Collection<T> c) {
        for(T t : c){
            addLast(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<T> c) {
        if (index > size) throw new NullPointerException();
        Node<T> f = first;
        for (int i = 0; i < index - 1; i++){
            f = f.next;
        }
        Node<T> after = f.next;
        for (T t : c){
            Node<T> newNode = new Node<>(t, null, f);
            f.next = newNode;
            f = newNode;
        }
        f.next = after;
        after.previous = f;
        size += c.size();
        return true;
    }


    // Adds element in head of list
    @Override
    public void addFirst(T t) {
        Node<T> newNode = new Node<>(t, null, null);
        Node<T> f = first;
        if (first == null) {
            this.last = newNode;
            this.first = newNode;
        }
        else {
            newNode.next = f;
            first.previous = newNode;
            first = newNode;
        }
        size++;
    }

    // Adds element in end of list
    @Override
    public void addLast(T t) {
        Node<T> l = last;
        Node<T> newNode = new Node(t, null, null);
        if (first == null) {
            last = newNode;
            first = newNode;
        }
        else {
            newNode.previous = l;
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
        throw new NullPointerException();
    }

    //Return last element in the list
    @Override
    public T getLast() {
        Node<T> l = last;
        if (l != null) {
            return l.value;
        }
        throw new NullPointerException();
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
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
    public T set(int index, T o) {
        Node<T> node = first;
        for (int i = 0; i < index; i++ ){
            node = node.next;
        }
        node.value = o;
        return o;
    }

    // Return size
    @Override
    public int size() {
        return size;
    }


    //Соритировка пузырьком
    @Override
    public void sort(Comparator<T> comparator) {
        boolean a = false;
        while (a == false){
            a = true;
            Node<T> f = first;
            for (int i = 0; i < size - 1; i++){
                int compare = comparator.compare(f.value, f.next.value);
                if( compare == 1) {
                    T tmp = f.value;
                    f.value = f.next.value;
                    f.next.value = tmp;
                    a = false;
                }
                f = f.next;
            }
        }
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
