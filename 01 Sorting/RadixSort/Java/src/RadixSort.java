import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] items = {0, 1, 2, 3, 4, -1, -6, -6, 7, 8, 9, 10, -100, 23, -4235, 3, 53, 45, 522, 3234, 222, 4345, 7,
                89, 45, 765, 68, 54, 465, 23, 42, 457, 8, 786, 574, 563, 45, 45, 65, 78};
        System.out.println("Initial input array:");
        System.out.println(Arrays.toString(items));
        System.out.println();

        int[] result = radixSort(items);

        System.out.println("Final output array:");
        System.out.println(Arrays.toString(result));
    }

    private static void countingSort(int[] items, int place) {
        int[] count = new int[19];
        // [-9, -8, -7, ..., 7, 8, 9] -> size of 19.

        System.out.println("Input array for " + place + " calculation:");
        System.out.println(Arrays.toString(items));

        // Phase 1: Count
        // Calculate count of elements
        for (int item : items) {
            int digit = ((item / place) % 10) + 9;
            ++count[digit];
        }

        System.out.println("Count array for " + place + " position:");
        System.out.println(Arrays.toString(count));

        // Phase 2: Aggregate
        // Calculate cumulative frequency
        for (int i = 1; i < 19; ++i) {
            count[i] += count[i - 1];
        }

        System.out.println("Aggregated count array for " + place + " position:");
        System.out.println(Arrays.toString(count));

        // Phase 3: Write to target array
        // Place the elements in sorted order
        // NOTE: see CountingSort.java for more detailed comments on CountingSort code and how it works.
        // This code is only modified from original CountingSort code to isolate the single digit
        // we're examining and +9 to support negative numbers.
        int[] target = new int[items.length];
        for (int i = items.length - 1; i >= 0; --i) {
            int element = ((items[i] / place) % 10) + 9;
            target[count[element] - 1] = items[i];
            --count[element];
        }

        System.arraycopy(target, 0, items, 0, items.length);
        System.out.println("Updated array after " + place + " calculation:");
        System.out.println(Arrays.toString(items));
        System.out.println();
    }

    // Function to implement radix sort
    private static int[] radixSort(int[] items) {

        if (items == null) {
            return null;
        }
        if (items.length <= 1) {
            return items;
        }

        // Get the max element in the array.
        int max = Arrays.stream(items).max().orElseThrow();

        // Apply a slightly modified counting sort to sort elements based on the significant digit that is currently
        // being examined while still being able to handle negative numbers.
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(items, place);
        }

        return items;
    }
}