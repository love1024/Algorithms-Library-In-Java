package array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ResizeableArray {
    private int[] array;
    private int N = 0;

    public ResizeableArray() {
        this.array = new int[2];
    }

    public ResizeableArray(int N) {
        this.array = new int[2];
    }

    // get ith element
    public int get(int i) {
        if(i < 0 || i >= N) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.array[i];
    }

    // Add element to the array
    public void push(int el) {
        // If array is full, double it
        if(this.N == this.array.length) {
            this.resize(2 * N);
        }

        array[this.N++] = el;
    }

    // Remove last element from the array
    public int pop() {
        if(N < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int item = this.array[--this.N];
        // Resize array to half, if one 3/4 is empty
        if(this.N > 0 && this.N == this.array.length / 4) {
            this.resize(this.array.length / 2);
        }

        return item;
    }

    // Resize array to a given size
    private void resize(int size) {
        int[] newArray = new int[size];
        for(int i = 0; i < N; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    public static void main(String[] args) {
        ResizeableArray arr = new ResizeableArray();
        arr.push(1);
        arr.push(2);
        arr.push(3);

        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
        System.out.println(arr.get(2));

        arr.pop();
        System.out.println(arr.get(1));
    }
}
