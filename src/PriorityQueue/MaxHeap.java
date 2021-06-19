package PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Max Heap where a key in the heap will
 * always be greater or equal than its children
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> {
    List<T> keys;

    MaxHeap() {
        keys = new ArrayList<>();
    }

    MaxHeap(int N) {
        keys = new ArrayList<>(N);
    }

    public void insert(T key) {
        keys.add(key);
        swim();
    }

    public T delMax() {
        if(keys.size() == 0) {
            throw new UnsupportedOperationException("Heap is empty");
        }

        // Exchange maximum element with the last and remove last
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
        // Keep exchanging while parent is less than
        // the current element
        while(curPos > 0 && greater(curPos, parPos)) {
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
            // First find the maximum of two children
            int maxChild = 2*curPos + 1;
            if(maxChild + 1 < keys.size() && greater(maxChild + 1, maxChild))
                maxChild++;

            // If parent is still maximum than the max child, break else exchange
            if(greater(curPos, maxChild))
                break;
            exchange(curPos, maxChild);
            curPos = maxChild;
        }
    }

    /**
     * Helper function to check whether ith element is greater than jth
     * @param i ith element
     * @param j jth element
     * @return true if greater otherwise false
     */
    private boolean greater(int i, int j) {
        return keys.get(i).compareTo(keys.get(j)) > 0;
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
        MaxHeap heap = new MaxHeap();
        heap.insert(1);
        heap.insert(10);
        heap.insert(2);
        heap.insert(6);

        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
    }
}
