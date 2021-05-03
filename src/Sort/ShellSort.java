package Sort;

public class ShellSort {
    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void sort(int[] arr, int n) {
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while(h >= 1) {
            for (int i = h; i < n; i++) {
                int j = i;
                while (j >= h && arr[j] < arr[j-h]) {
                    exchange(arr, j, j-h);
                    j -= h;
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};

        sort(arr, 5);

        for (int i = 0; i < 5; i++)
            System.out.print(arr[i] + " ");
    }
}
