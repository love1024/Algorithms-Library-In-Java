package Search;
// Learning Source
//https://www.topcoder.com/thrive/articles/Binary%20Search

public class BinarySearch {
    public static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = low + (high - low)/2;
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * Find lower bound for a given key, so that
     * all elements after that index will be greater or
     * equal to the given key
     * @param a, Given array
     * @param key, Key to compare
     * @return Index of the lower bound or -1 if not found
     */
    public static int lowerBound(int [] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while(low < high) {
            int mid = low + (high - low)/2;
            if(a[mid] >= key) {
                high = mid; //mid can be the lowest element, so not doing mid-1
            } else {
                low = mid+1;
            }
        }

        if(a[low] >= key)
            return low;
        else
            return -1;
    }

    /**
     * Find upper bound for a given key, so that
     * all elements before that index will be less or
     * equal to the given key
     * @param a Given array
     * @param key Key to compare
     * @return Index of the upper bound or -1 if not found
     */
    public static int upperBound(int [] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while(low < high) {
            // + 1, to keep track of case [true, false] where true is when a[mid] <= key, so it will stay
            // at 0 otherwise
            int mid = low + (high - low + 1)/2;
            if(a[mid] <= key) {
                low = mid; //mid can be the upper element, so not doing mid+1
            } else {
                high = mid-1;
            }
        }

        if(a[low] <= key)
            return low;
        else
            return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7, 9};

        int idx = binarySearch(a, 5);
        System.out.println(idx);
    }
}
