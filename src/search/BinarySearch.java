package search;

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

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7, 9};

        int idx = binarySearch(a, 5);
        System.out.println(idx);
    }
}
