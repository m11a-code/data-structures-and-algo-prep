import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array:");
        System.out.println(Arrays.toString(items));

        int[] result = quicksort(items);

        System.out.println("Output array:");
        System.out.println(Arrays.toString(result));
    }

    private static void helper(int[] items, int start, int end) {
        // Leaf node work / base case
        if (start >= end) {
            return;
        }

        // Internal node work
        // Choose the pivot
        int pivotIndex = choosePivot(start, end);
        // Swap pivot
        swap(items, start, pivotIndex);

        // Partition
        int placedPivot = partition(items, start, end, PartitioningScheme.HOARE);

        // Go left
        helper(items, start, placedPivot - 1);
        // Go right
        helper(items, placedPivot + 1, end);
    }

    private static int partition(int[] items, int start, int end, PartitioningScheme scheme) {
        if (scheme == PartitioningScheme.LOMUTO) {
            // Lomuto's partitioning
            int smaller = start;
            for (int bigger = start + 1; bigger <= end; bigger++) {
                if (items[bigger] < items[start]) {
                    ++smaller;
                    swap(items, smaller, bigger);
                }
            }
            swap(items, start, smaller);

            return smaller;
        }
        // if (scheme == PartitioningScheme.HOARE)
        // Hoare's partitioning
        int smaller = start + 1, bigger = end;

        while (smaller <= bigger) {
            if (items[smaller] < items[start]) {
                ++smaller;
            } else if (items[bigger] > items[start]) {
                --bigger;
            } else {
                swap(items, smaller, bigger);
                ++smaller;
                --bigger;
            }
        }

        swap(items, start, bigger);

        return bigger;
    }

    private static int choosePivot(int start, int end) {
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

    private enum PartitioningScheme {
        HOARE, LOMUTO
    }

}
