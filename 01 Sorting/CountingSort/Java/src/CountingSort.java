import java.util.Arrays;

public class CountingSort {

    // Input from medium and Geeks4Geeks articles:
    // I preferred the commenting from the medium article along with the stable component of the code in the medium
    // article.
    // From the G4G article, I took the part where it was able to work with negative numbers.
    // https://medium.com/javarevisited/counting-sort-algorithm-implementation-in-java-an-analysis-of-stability-parallelizability-and-48ac7e43bcf1
    // https://www.geeksforgeeks.org/counting-sort/
    public static void main(String[] args) {
//        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 2, 1, 3, -1, -3, -3, -2, 4, -1, -2, -1, 3, 5, 5, 0, 1};
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 2, 1, 3, 1, 3, 3, 2, 4, 1, 2, 1, 3, 5, 5, 0, -1};
        System.out.println("Input array:");
        System.out.println(Arrays.toString(items));
        int[] result = countingSort(items);
        System.out.println("Output array:");
        System.out.println(Arrays.toString(result));
    }

    public static int[] countingSort(int[] items) {
        // For simplicity of this implementation, just throw if array is empty.
        int maxValue = Arrays.stream(items).max().orElseThrow();
        int minValue = Arrays.stream(items).min().orElseThrow();
        int range = maxValue - minValue + 1;
        int[] counts = new int[range];
        // Phase 1: Count
        System.out.println("Min value: " + minValue);
        for (int item : items) {
            // Move values so that regardless of the size of the smallest value, be it negative or positive, it will
            // start at zero in the count array.
            ++counts[item - minValue];
        }
        System.out.println("Counts:");
        System.out.println(Arrays.toString(counts));

        // Phase 2: Aggregate
        // Get cumulative sum and store in counts array, adding value from previous count.
        // This will get the array index that the value should be stored in the new array.
        for (int i = 1; i < counts.length; ++i) {
            counts[i] += counts[i - 1];
        }
        // Index value represents a value in the items array.
        // The difference between count at i and count i - 1 is how often that value occurred.
        System.out.println("Aggregated counts:");
        System.out.println(Arrays.toString(counts));
        // Phase 3: Write to target array
        // Working from end of array to beginning, therefore, this is a stable implementation.
        // Subtract min value to move back to original values.
        int[] target = new int[items.length];
        for (int i = items.length - 1; i >= 0; --i) {
            // Readjust original array value,
            // Find that in counts array for getting index of where value should be placed in target array
            int adjustedOriginalArrayValueGivesIndexInCountsArray = items[i] - minValue;
            // counts[adjustedOriginalArrayValueGivesIndexInCountsArray] - 1 gets the index of where the value should
            // be placed in the target array
            int indexInTargetArrayToPlaceOriginalValue = counts[adjustedOriginalArrayValueGivesIndexInCountsArray] - 1;
            target[indexInTargetArrayToPlaceOriginalValue] = items[i];
            // Subtract one from the index in counts array so that if there are any more occurrences of this number,
            // they can be inserted to the left of this one and as a result, keep the implementation stable.
            --counts[adjustedOriginalArrayValueGivesIndexInCountsArray];
        }

        Hash
        /*
            Example that helps with visualization.
            Input array:
            [1, 4, 3, 6, 6, 4, 3, 7, 9, 2, 1, 3, 1, 3, 3, 2, 4, 1, 2, 1, 3, 5, 5, 0, -1]
            Min value: -1
            Counts:
            [1, 1, 5, 3, 6, 3, 2, 2, 1, 0, 1]
            One occurrence of -1,
            one occurrence of 0,
            five occurrences of 1,
            etc...
            Aggregated counts:
            [1, 2, 7, 10, 16, 19, 21, 23, 24, 24, 25]
            Output array:
            [-1, 0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 9]
         */
        // Copy target back to input array
        System.arraycopy(target, 0, items, 0, items.length);

        return items;
    }
}
