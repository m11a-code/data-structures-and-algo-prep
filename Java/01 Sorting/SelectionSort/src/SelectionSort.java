import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(selectionsort(items)));
    }

    private static int[] selectionsort(int[] items) {
        for (int i = 0; i <= items.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= items.length - 1; j++) {
                if (items[j] < items[minIndex]) {
                    minIndex = j;
                }
            }
            swap(items, minIndex, i);
        }
        return items;
    }

    private static void swap(int[] items, int first, int second) {
        int temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}
