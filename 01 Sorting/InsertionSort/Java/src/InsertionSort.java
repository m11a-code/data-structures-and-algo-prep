import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(insertionsort(items)));
    }

    public static int[] insertionsort(int[] items) {
        for (int i = 0; i <= items.length - 1; i++) {
            int temp = items[i];
            int j = i - 1;
            while (j >= 0 && items[j] > temp) {
                items[j + 1] = items[j];
                --j;
            }
            items[j + 1] = temp;
        }

        return items;
    }
}
