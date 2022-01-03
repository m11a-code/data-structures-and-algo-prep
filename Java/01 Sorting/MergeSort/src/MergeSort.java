import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] input = { 4,3,2,6,4,8,1,1,3 };
        System.out.println("Input array: " + Arrays.toString(input));
        System.out.println("Sorted output: " + Arrays.toString(mergeSort(input)));
    }

    private static void helper(int[] items, int[] aux, int start, int end) {
        // Leaf worker tasks
        if (start == end) {
            return;
        }

        // Internal node workers
        int mid = start + (end - start) / 2;    // Avoids overflow

        helper(items, aux, start, mid);
        helper(items, aux, mid + 1, end);

        // Merge the two sorted halves
        merge(items, aux, start, mid, end);
    }

    private static void merge(int[] items, int[] aux, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int auxPtr = start;

        // Merge the two sorted halves
        while (i <= mid && j <= end) {
            if (items[i] <= items[j]) {
                aux[auxPtr] = items[i];
                ++i;
            } else {
                aux[auxPtr] = items[j];
                ++j;
            }
            ++auxPtr;
        }

        // Gather phase
        while (i <= mid) {
            aux[auxPtr] = items[i];
            ++i;
            ++auxPtr;
        }

        while (j <= end) {
            aux[auxPtr] = items[j];
            ++j;
            ++auxPtr;
        }

        System.arraycopy(aux, start, items, start, end - start + 1);
    }

    public static int[] mergeSort(int[] items) {
        helper(items, new int[items.length], 0, items.length - 1);
        return items;
    }
}
