import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[2];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(items.length * 2);

        items[size] = item;
        size += 1;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniformInt(size), last = size - 1;
        Item item = items[index];
        if (index != last) {
            items[index] = items[last];
        }
        items[last] = null;
        size -= 1;
        if (size > 0 && size == items.length / 4) resize(items.length / 2);

        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniformInt(size);
        Item item = items[index];

        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i += 1) {
            copy[i] = items[i];
        }

        items = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorItems;
        private int currentSize = size;

        public RandomizedQueueIterator() {
            iteratorItems = (Item[]) new Object[size];

            for (int i = 0; i < size; i += 1) {
                iteratorItems[i] = items[i];
            }
        }

        public boolean hasNext() {
            return currentSize > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int index = StdRandom.uniformInt(currentSize), last = currentSize - 1;
            Item item = iteratorItems[index];
            if (index != last) {
                iteratorItems[index] = iteratorItems[last];
            }
            iteratorItems[last] = null;
            currentSize -= 1;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        StdOut.println("Is empty: " + queue.isEmpty());
        StdOut.println("Size: " + queue.size());
        StdOut.println();

        StdOut.println("Enqueue: " + 2);
        queue.enqueue(2);
        StdOut.println("Enqueue: " + 3);
        queue.enqueue(3);
        StdOut.println("Enqueue: " + 1);
        queue.enqueue(1);
        StdOut.println("Enqueue: " + 4);
        queue.enqueue(4);
        StdOut.println();

        StdOut.println("Is empty: " + queue.isEmpty());
        StdOut.println("Size: " + queue.size());
        StdOut.println();

        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println();

        StdOut.println("Dequeue: " + queue.dequeue());
        StdOut.println("Dequeue: " + queue.dequeue());
        StdOut.println();

        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println("Sample: " + queue.sample());
        StdOut.println();

        StdOut.println("Is empty: " + queue.isEmpty());
        StdOut.println("Size: " + queue.size());
        StdOut.println();

        for (int item : queue) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }
}
