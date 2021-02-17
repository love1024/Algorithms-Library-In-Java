package Stack;

import java.util.Iterator;

public class StackCustom<T> implements Iterable<T>{
    // Reference to first element in stack
    private Node first;

    // Node of stack
    private class Node {
        T item;
        Node next;
    }

    // Iterator of stack
    private class StackIterator implements Iterator<T>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public void push(T item) {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
    }

    public T pop() {
        T item = this.first.item;
        this.first = this.first.next;

        return item;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        StackCustom<Integer> stack = new StackCustom<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        for (Integer s: stack) {
            System.out.println(s);;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
