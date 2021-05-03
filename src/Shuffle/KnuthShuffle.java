package Shuffle;

public class KnuthShuffle {
    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shuffle(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            int r = (int)Math.floor(Math.random() * (i+1));
            exchange(arr, i, r);
        }
    }

    public static void main(String[] args) {
        int[] arr =new int[]{1,2,3,4,5};

        shuffle(arr, 5);

        for (int i = 0; i < 5; i++)
            System.out.print(arr[i] + " ");
    }
}
