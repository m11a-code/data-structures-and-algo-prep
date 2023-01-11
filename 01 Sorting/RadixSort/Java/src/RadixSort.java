import java.util.Arrays;
import java.util.Random;

public class RadixSort {

    static ACTIONS actions = ACTIONS.DEBUG;
    static INPUT input = INPUT.SIMPLE;

    static final int RandomizedArraySize = 1000000000;

    public static void main(String[] args) {
        int[] items;
        if (input == INPUT.SIMPLE) {
            items = new int[]{0, 1, 2, 3, 4, -1, -6, -6, 7, 8, 9, 10, -100, 23, -4235, 3, 53, 45, 522, 3234, 222,
                    4345, 7, 89, 45, 765, 68, 54, 465, 23, 42, 457, 8, 786, 574, 563, 45, 45, 65, 78};
//            items = new int[] {1364025793, -222222229, -1295119823, 2043400588, 594134920, 2080480370, 1858863360, 1972939638, 326661553, -222222223};
        } else {
            items = new int[RandomizedArraySize];

            Random random = new Random();
            for (int i = 0; i < items.length; ++i) {
                items[i] = random.nextInt();
            }
        }

        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Initial input array:");
            System.out.println(Arrays.toString(items));
        }

        int[] result;

        if (actions == ACTIONS.TIMER || actions == ACTIONS.BOTH) {
            System.out.println();
            System.out.println("Starting radixsort.");
            System.out.println();
            long start = System.currentTimeMillis();

            result = radixSort(items);

            long end = System.currentTimeMillis();
            System.out.println("Elapsed time in milliseconds: " + (end - start));
            System.out.println();
        } else {
            System.out.println();
            result = radixSort(items);
        }

        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Final output array:");
            System.out.println(Arrays.toString(result));
        }
    }

    private static void countingSort(int[] items, long place) {
        int[] count = new int[19];
        // [-9, -8, -7, ..., 7, 8, 9] -> size of 19.

        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Input array for " + String.valueOf(place).length() + " calculation:");
            System.out.println(Arrays.toString(items));
        }

        // Phase 1: Count
        // Calculate count of elements
        for (int item : items) {
            int digit = (int)((item / place) % 10) + 9;
            if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
                System.out.println("Examining digit " + (digit - 9) + " (at index " + digit + " in count array) from item " + item + " at place " + String.valueOf(place).length());
            }
            ++count[digit];
        }

        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Count array for " + String.valueOf(place).length() + " digit position:");
            System.out.println(Arrays.toString(count));
        }

        // Phase 2: Aggregate
        // Calculate cumulative frequency
        for (int i = 1; i < 19; ++i) {
            count[i] += count[i - 1];
        }

        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Aggregated count array for " + String.valueOf(place).length() + " position:");
            System.out.println(Arrays.toString(count));
        }

        // Phase 3: Write to target array
        // Place the elements in sorted order
        // NOTE: see CountingSort.java for more detailed comments on CountingSort code and how it works.
        // This code is only modified from original CountingSort code to isolate the single digit
        // we're examining and +9 to support negative numbers.
        int[] target = new int[items.length];
        for (int i = items.length - 1; i >= 0; --i) {
            int element = (int)((items[i] / place) % 10) + 9;
            target[count[element] - 1] = items[i];
            --count[element];
        }

        System.arraycopy(target, 0, items, 0, items.length);
        if (actions == ACTIONS.DEBUG || actions == ACTIONS.BOTH) {
            System.out.println("Updated array after " + String.valueOf(place).length() + " calculation:");
            System.out.println(Arrays.toString(items));
            System.out.println();
        }
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
        // A bit easier for debugging:
//        long place = 1;
//        while (max / place > 0) {
//            countingSort(items, place);
//            place *= 10;
//            System.out.println("max / place: " + (max / place));
//            System.out.println();
//        }
        for (long place = 1; max / place > 0; place *= 10) {
            countingSort(items, place);
        }

        return items;
    }

    enum ACTIONS {
        NONE, TIMER, DEBUG, BOTH
    }

    enum INPUT {
        SIMPLE, RANDOMIZED
    }
}