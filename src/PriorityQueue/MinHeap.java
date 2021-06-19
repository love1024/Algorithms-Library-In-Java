package PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Max Heap where a key in the heap will
 * always be less or equal than its children
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> {
    List<T> keys;

    MinHeap() {
        keys = new ArrayList<>();
    }

    MinHeap(int N) {
        keys = new ArrayList<>(N);
    }

    public void insert(T key) {
        keys.add(key);
        swim();
    }

    public T delMin() {
        if(keys.size() == 0) {
            throw new UnsupportedOperationException("Heap is empty");
        }

        // Exchange minimum element with the last and remove last
        T max = keys.get(0);
        exchange(0, keys.size()-1);
        keys.remove(keys.size()-1);

        // Now sink swapped element to its appropriate position
        sink();

        return max;
    }

    /**
     * Move the last element to its appropirate
     * position
     */
    private void swim() {
        int curPos = keys.size()-1;
        int parPos = (curPos-1)/2;
        // Keep exchanging while parent is greater than
        // the current element
        while(curPos > 0 && less(curPos, parPos)) {
            exchange(curPos, parPos);
            curPos = parPos;
            parPos = (curPos -1)/2;
        }
    }

    /**
     * Sink top element to its appropriate
     * position
     */
    private void sink() {
        int curPos = 0;
        while(2*curPos + 1 < keys.size()) {
            // First find the minimum of two children
            int minChild = 2*curPos + 1;
            if(minChild + 1 < keys.size() && less(minChild + 1, minChild))
                minChild++;

            // If parent is still minimum than the min child, break else exchange
            if(less(curPos, minChild))
                break;
            exchange(curPos, minChild);
            curPos = minChild;
        }
    }

    /**
     * Helper function to check whether ith element is less than jth
     * @param i ith element
     * @param j jth element
     * @return true if less otherwise false
     */
    private boolean less(int i, int j) {
        return keys.get(i).compareTo(keys.get(j)) < 0;
    }

    /**
     * Exchange ith and jth element
     * @param i ith element
     * @param j jth element
     */
    private void exchange(int i, int j) {
        T temp = keys.get(i);
        keys.set(i, keys.get(j));
        keys.set(j, temp);
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.insert(1);
        heap.insert(10);
        heap.insert(2);
        heap.insert(6);

        System.out.println(heap.delMin());
        System.out.println(heap.delMin());
        System.out.println(heap.delMin());
        System.out.println(heap.delMin());
    }
}
