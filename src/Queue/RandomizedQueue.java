package Queue;

import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;

    // Point only to last
    private int last;

    // Iterator
    private class QueueIterator implements Iterator<Item> {
        private int current = 0;
        private List<Integer> order;

        public QueueIterator() {
            // Create random order of items
            order  = new ArrayList<>();
            for(int i = 1; i <= size(); i++) {
                order.add(i);
            }
            Collections.shuffle(order);
        }

        public boolean hasNext() {
            return current <= last;
        }

        public Item next() {
            if (current >= order.size()) {
                throw new NoSuchElementException();
            }

            return queue[order.get(current++)];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[2];
        this.last = -1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return (this.last + 1);
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.last + 1 == this.queue.length) {
            this.resize(2 * this.size());
        }

        this.queue[++last] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size() <= 0) {
            throw new NoSuchElementException();
        }
        int idx = (new Random()).nextInt(this.size());

        // Replace idx with last item so that we can remove it
        Item lastItem = this.queue[this.last];
        this.queue[this.last] = this.queue[idx];
        this.queue[idx] = lastItem;

        // Get last item and remove it
        Item item = this.queue[this.last--];

        // Resize array to half, if one 3/4 is empty
        if (this.size() > 0 && this.size() == this.queue.length / 4) {
            this.resize(this.queue.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.size() <= 0) {
            throw new NoSuchElementException();
        }

        int idx = (new Random()).nextInt(this.size());
        return this.queue[idx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    // Resize array to a given size
    private void resize(int size) {
        Item[] newArray = (Item[]) new Object[size];
        for (int i = 0; i < this.size(); i++) {
            newArray[i] = this.queue[i];
        }

        this.queue = newArray;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println(queue.sample());

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.size());
    }
}
