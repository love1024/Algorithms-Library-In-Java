package Sort;

public class HeapSort {
    /**
     * Sink top element to its appropriate
     * position
     */
    private static void sink(int[] arr, int i, int n) {
        int curPos = i;
        while(2*curPos + 1 < n) {
            // First find the maximum of two children
            int maxChild = 2*curPos + 1;
            if(maxChild + 1 < n && arr[maxChild + 1] > arr[maxChild])
                maxChild++;

            // If parent is still maximum than the max child, break else exchange
            if(arr[curPos] > arr[maxChild])
                break;
            exchange(arr, curPos, maxChild);
            curPos = maxChild;
        }
    }

    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int n) {
        // First heap ordered all elements in the array
        // We only heapify first half, as rest of the half
        // are nodes without any children
        for(int i=n/2-1; i >= 0; i--)
            sink(arr, i, n);

        // Now extract out the max element, put it at the end
        // and reduce the array size
        while(n > 0) {
            exchange(arr, 0, n-1);
            sink(arr, 0, --n);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,2,5,2};

        sort(arr, arr.length);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
