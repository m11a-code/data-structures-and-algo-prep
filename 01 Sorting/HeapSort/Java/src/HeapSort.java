import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] items = {1, 4, 3, 6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(heapsort(items)));
    }

    public static int[] heapsort(int[] items) {

        // Build heap (rearrange the array)
        // Start at rightmost array position that has a child node.
        // This is always at n/2, because it's a complete binary tree, and the -1 is because n was set to the length of
        // the array, but we want the index, so -1 necessary.
        int n = items.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(items, n, i);
        }

        // One by one, extract the root / max element from the heap.
        // We just swap the root with the end element and heapify the tree not including the end element.
        // This allows us to build the sorted array in place.
        for (int i = n - 1; i > 0; i--) {
            // Move the current root node to the end.
            swap(items, i, 0);
            // Heapify the reduced heap
            maxHeapify(items, i - 1, 0);
        }

        return items;
    }

    public static void maxHeapify(int[] items, int sizeOfHeap, int index) {
        int largestIndex = index;    // Largest value initialized as the root
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        // Check the left index value and see if it's larger than the root
        if (leftIndex  < sizeOfHeap && items[leftIndex] > items[largestIndex]) {
            largestIndex = leftIndex;
        }
        // Check the right index value and check if it's larger that the current largest value (left or root)
        if (rightIndex < sizeOfHeap && items[rightIndex] > items[largestIndex]) {
            largestIndex = rightIndex;
        }
        // If the largest is not still the root value
        if (largestIndex != index) {
            // Swap the largest value with the root and check on the subtree
            swap(items, index, largestIndex);
            // Check on the subtree to make sure the swap didn't violate heap property there, and if it did, heapify it.
            maxHeapify(items, sizeOfHeap, largestIndex);
        }
    }

    private static void swap(int[] items, int first, int second) {
        int temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}
