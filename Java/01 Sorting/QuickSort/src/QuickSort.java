import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] items = {1,4,3,6,6,4,3,7,9,87,234,56,3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(quicksort(items)));
    }

    private static void helper(int[] items, int start, int end) {
        // Leaf node work / base case
        if (start >= end) {
            return;
        }

        // Internal node work
        // Choose the pivot
        int pivotIndex = choosePivot(items, start, end);
        // Swap pivot
        swap(items, start, pivotIndex);

        // Partition
        // Lomuto's partitioning
        int smaller = start;
        for (int bigger = start + 1; bigger <= end; bigger++) {
            if (items[bigger] < items[start]) {
                ++smaller;
                swap(items, smaller, bigger);
            }
        }
        swap(items, start, smaller);

        // Go left
        helper(items, start, smaller - 1);
        // Go right
        helper(items, smaller + 1, end);
    }

    private static int choosePivot(int[] items, int start, int end) {
        return (int) ((Math.random() * (end - start)) + start);
    }

    private static void swap(int[] items, int first, int second) {
        int temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

    private static int[] quicksort(int[] items) {
        helper(items, 0, items.length - 1);
        return items;
    }

}
