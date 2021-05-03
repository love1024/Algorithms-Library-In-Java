package Sort;

public class InsertionSort {

    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int n) {

        for (int i = 1; i < n; i++) {
            int j = i-1;
            while (j >= 0 && arr[i] < arr[j]) {
                exchange(arr, i, j);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};

        sort(arr, 5);

        for (int i = 0; i < 5; i++)
            System.out.print(arr[i] + " ");
    }
}
