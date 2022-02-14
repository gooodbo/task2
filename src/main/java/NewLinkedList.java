import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class NewLinkedList<T> implements MyLinkedList<T>, Iterable<T> {
    // First node
    private Node<T> first;
    // Last node
    private Node<T> last;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> fNode = first;

            @Override
            public boolean hasNext() {
                if (fNode == null) return false;
                return true;
            }

            @Override
            public T next() {
                T value = fNode.value;
                fNode = fNode.next;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

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
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
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

    public boolean addAll(NewLinkedList<T> c) {
        for(T t : c){
            addLast(t);
        }
        return true;
    }

    public boolean addAll(int index, NewLinkedList<T> c) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
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



    @Override
    public boolean addAll(int index, Collection<T> c) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
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
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
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
        throw new IndexOutOfBoundsException();
    }

    //Return first element in the list
    @Override
    public T getFirst() {
        Node<T> f = first;
        if (f != null) {
            return f.value;
        }
        throw new IndexOutOfBoundsException();
    }

    //Return last element in the list
    @Override
    public T getLast() {
        Node<T> l = last;
        if (l != null) {
            return l.value;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean remove(Object o) {
        Node<T> f = first;
        Node<T> l = null;

        while (f != null) {
            if (o.equals(f.value)) {
                if (l == null) {
                    first = first.next;
                } else if (f.next == null) {
                    l.next = null;
                    f = null;
                } else {
                    l.next = f.next;
                    f.next.previous = l;
                }
                size--;
                return true;
            }
            l = f;
            f = f.next;
        }
        return false;
    }

    public T remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        T returnable;
        if (index < (size-1)/2 ) {
            Node<T> f = first;
            for (int i = 0; i < index; i++){
                f = f.next;
            }
            returnable = f.value;
            f.previous.next = f.next;
        }
        else {
            Node<T> f = last;
            for (int i = size - 1; i < index; i--){
                f = f.next;
            }
            returnable = f.value;
            f.previous.next = f.next;
        }

        return returnable;
    }

    @Override
    public T removeFirst() {
        T returnable = first.value;
        first = first.next;
        first.previous = null;
        size--;
        return returnable;
    }

    @Override
    public T removeLast() {
        T returnable = last.value;
        last.previous.next = null;
        last = last.previous;
        size--;
        return  returnable;
    }

    @Override
    public T set(int index, T o) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
        if (index < (size - 1)/2) {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.value = o;
        }
        else {
            Node<T> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
            node.value = o;
        }
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
