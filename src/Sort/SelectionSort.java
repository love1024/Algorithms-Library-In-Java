package Sort;

public class SelectionSort {

    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i])
                    exchange(arr, i, j);
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
