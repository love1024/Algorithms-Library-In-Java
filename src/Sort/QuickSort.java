package Sort;

import Shuffle.KnuthShuffle;

public class QuickSort {
    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Partition the array so that the first element in the
     * given range is at its appropriate position
     * @param arr Array to partition
     * @param lo lowest element for current recursion
     * @param hi highest element for current recursion
     * @return
     */
    public static int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi+1;  // Increment by 1, so that we can use --j later
        while(true) {
            while(arr[++i] < arr[lo]) {
                if(i == hi) break;
            }

            // break check is not required, because it will automatically
            // break at equal element when (j == lo)
            while(arr[--j] > arr[lo]) { }

            if(i >= j)
                break;

            // Exchange out of place elements where ith element is
            // greater than lo and jth element is smaller than lo
            exchange(arr, i, j);
        }

        // Exchange the first element with its appropriate position found
        exchange(arr, lo, j);
        return j;
    }

    public static void sort(int[] arr, int lo, int hi) {
        if(lo >= hi)
            return;

        int k = partition(arr, lo, hi);
        sort(arr, lo, k-1);
        sort(arr, k+1, hi);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};

        // Shuffle First so that average complexity will be nlogn
        KnuthShuffle.shuffle(arr, arr.length);

        sort(arr, 0, arr.length-1);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
