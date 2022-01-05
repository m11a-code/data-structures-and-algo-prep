import java.util.Arrays;

public class CountingSort {

    // Input from medium and Geeks4Geeks articles:
    // I preferred the commenting from the medium article along with the stable component of the code in the medium
    // article.
    // From the G4G article, I took the part where it was able to work with negative numbers.
    // https://medium.com/javarevisited/counting-sort-algorithm-implementation-in-java-an-analysis-of-stability-parallelizability-and-48ac7e43bcf1
    // https://www.geeksforgeeks.org/counting-sort/
    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 2, 1, 3, -1, -3, -3, -2, 4, -1, -2, -1, 3, 5, 5, 0, 1};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(countingsort(items)));
    }

    public static int[] countingsort(int[] items) {
        // For simplicity of this implementation, just throw if array is empty.
        int maxValue = Arrays.stream(items).max().orElseThrow();
        int minValue = Arrays.stream(items).min().orElseThrow();
        int range = maxValue - minValue + 1;
        int[] counts = new int[range];
        // Phase 1: Count
        for (int item : items) {
            // Move values so that regardless of the size of the smallest value, be it negative or positive, it will
            // start at zero in the count array.
            counts[item - minValue]++;
        }
        // Phase 2: Aggregate
        // Get cumulative sum and store in counts array, adding value from previous count.
        // This will get the array index that the value should be stored in the new array.
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // Phase 3: Write to target array
        // Working from end of array to beginning, therefore, this is a stable implementation.
        // Subtract min value to move back to original values.
        int[] target = new int[items.length];
        for (int i = items.length - 1; i >= 0; i--) {
            int element = items[i] - minValue;
            target[counts[element] - 1] = items[i];
            counts[element]--;
        }
        // Copy target back to input array
        System.arraycopy(target, 0, items, 0, items.length);

        return items;
    }
}
