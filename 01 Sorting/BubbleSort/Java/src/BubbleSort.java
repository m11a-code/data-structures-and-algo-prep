import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(bubbleSort(items)));
    }

    private static int[] bubbleSort(int[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = items.length - 1; j >= i + 1; j--) {
                if (items[j - 1] > items[j]) {
                    swap(items, j, j - 1);
                }
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
