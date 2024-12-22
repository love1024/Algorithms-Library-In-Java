package Array;

public class ResizeableArray<T> {
    private T[] array;
    private int N = 0;

    public ResizeableArray() {
        this.array = (T[]) new Object[2];
    }

    public ResizeableArray(int N) {
        this.array = (T[]) new Object[N];
    }

    // get ith element
    public T get(int i) {
        if(i < 0 || i >= N) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.array[i];
    }

    // Add element to the array
    public void push(T el) {
        // If array is full, double it
        if(this.N == this.array.length) {
            this.resize(2 * N);
        }

        array[this.N++] = el;
    }

    // Remove last element from the array
    public T pop() {
        if(N < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T item = this.array[--this.N];
        // Resize array to half, if one 3/4 is empty
        if(this.N > 0 && this.N == this.array.length / 4) {
            this.resize(this.array.length / 2);
        }

        return item;
    }

    // Resize array to a given size
    private void resize(int size) {
        T[] newArray = (T[]) new Object[size];
        for(int i = 0; i < N; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    public static void main(String[] args) {
        ResizeableArray<Integer> arr = new ResizeableArray<>();
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
