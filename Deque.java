import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head = null, tail = null;
    private int size = 0;

    private class Node {
        private final Item item;
        private Node prev = null, next = null;

        public Node(Item item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);
        node.next = head;
        if (isEmpty()) {
            tail = node;
        }
        else {
            head.prev = node;
        }
        head = node;
        size += 1;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);
        node.prev = tail;
        if (isEmpty()) {
            head = node;
        }
        else {
            tail.next = node;
        }
        tail = node;
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = head.item;
        head = head.next;
        size -= 1;
        if (isEmpty()) {
            tail = null;
        }
        else {
            head.prev = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = tail.item;
        tail = tail.prev;
        size -= 1;
        if (isEmpty()) {
            head = null;
        }
        else {
            tail.next = null;
        }

        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        StdOut.println("Is empty: " + deque.isEmpty());
        StdOut.println("Size: " + deque.size());
        StdOut.println();

        StdOut.println("Add first " + 2);
        deque.addFirst(2);
        StdOut.println("Add last " + 3);
        deque.addLast(3);
        StdOut.println("Add first " + 1);
        deque.addFirst(1);
        StdOut.println("Add last " + 4);
        deque.addLast(4);
        StdOut.println();

        StdOut.println("Is empty: " + deque.isEmpty());
        StdOut.println("Size: " + deque.size());
        StdOut.println();

        StdOut.println("Remove first: " + deque.removeFirst());
        StdOut.println("Remove last: " + deque.removeLast());
        StdOut.println();

        StdOut.println("Is empty: " + deque.isEmpty());
        StdOut.println("Size: " + deque.size());
        StdOut.println();

        for (int item : deque) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }
}
