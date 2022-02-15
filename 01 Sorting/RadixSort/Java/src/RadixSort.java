import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] items = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 23, 4235, 3, 53, 45, 522, 3234, 222, 4345, 7, 89, 45,
                765, 68, 54, 465, 23, 42, 457, 8, 786, 574, 563, 45, 45, 65, 78};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(radixsort(items)));
    }

    private static void countingSort(int[] items, int place) {
        int[] count = new int[10];

        // Phase 1: Count
        // Calculate count of elements
        for (int item : items) {
            ++count[(item / place) % 10];
        }

        // Phase 2: Aggregate
        // Calculate cumulative frequency
        for (int i = 1; i < 10; ++i) {
            count[i] += count[i - 1];
        }

        // Phase 3: Write to target array
        // Place the elements in sorted order
        int[] target = new int[items.length];
        for (int i = items.length - 1; i >= 0; --i) {
            int element = (items[i] / place) % 10;
            target[count[element] - 1] = items[i];
            --count[element];
        }

        System.arraycopy(target, 0, items, 0, items.length);
    }

    // Function to implement radix sort
    private static int[] radixsort(int[] items) {

        // Get the max element in the array.
        int max = Arrays.stream(items).max().orElseThrow();

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(items, place);
        }

        return items;
    }
}
