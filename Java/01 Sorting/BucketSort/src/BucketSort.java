import java.util.Arrays;

public class BucketSort {

    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(bucketsort(items)));
    }

    private static int[] bucketsort(int[] items) {
        int max = Arrays.stream(items).max().orElseThrow();

        // Bucket Sort
        int[] bucket = new int[max + 1];
        int[] sorted = new int[items.length];

        // Count everything and group into buckets.
        for (int item : items) {
            bucket[item]++;
        }


        int sortedIndex = 0;
        // Go through each bucket.
        for (int i = 0; i < bucket.length; i++) {
            // For each bucket, add that number to the sorted array.
            for (int j = 0; j < bucket[i]; j++) {
                sorted[sortedIndex++] = i;
            }
        }

        return sorted;
    }
}
