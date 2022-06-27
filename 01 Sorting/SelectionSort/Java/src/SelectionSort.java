import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(selectionSort(items)));
    }

    private static int[] selectionSort(int[] items) {
        int minIndex;
        for (int i = 0; i <= items.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j <= items.length - 1; j++) {
                if (items[j] < items[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(items, i, minIndex);
            }
        }
        return items;
    }

    private static void swap(int[] items, int first, int second) {
        int temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}
