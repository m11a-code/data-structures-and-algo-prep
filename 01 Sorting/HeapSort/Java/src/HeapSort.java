import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] items = {1, 4, 3, -6, 6, 4, 3, 7, 9, 87, 234, 56, 3};
        System.out.println("Input array: " + Arrays.toString(items));
        System.out.println("Output array:" + Arrays.toString(heapsort(items, ImplementationVersion.OMKAR)));
    }

    public static int[] heapsort(int[] items, ImplementationVersion version) {

        if (version == ImplementationVersion.OMKAR) {
            int n = items.length;
            for (int i = n / 2 - 1; i >= 0; --i) {
                heapifyDown(items, n, i);
            }

            for (int i = n - 1; i >= 0; --i) {
                swap(items, 0, i);
                heapifyDown(items, i, 0);
            }

            return items;
        }

        // Build heap (rearrange the array)
        // Start at rightmost array position that has a child node.
        // This is always at n/2, because it's a complete binary tree, and the -1 is because n was set to the length of
        // the array, but we want the index, so -1 necessary.
        int n = items.length;
        for (int i = n / 2 - 1; i >= 0; --i) {
            maxHeapify(items, n, i);
        }

        // One by one, extract the root / max element from the heap.
        // We just swap the root with the end element and heapify the tree not including the end element.
        // This allows us to build the sorted array in place.
        for (int i = n - 1; i > 0; --i) {
            // Move the current root node to the end.
            swap(items, i, 0);
            // Heapify the reduced heap
            // Pass in *SIZE* of new array so that index of last element can be calculated from
            // that and as a result, ignore the newly swapped element so that everything is done
            // in place and so that they sorted elements aren't considered in new heap.
            maxHeapify(items, i, 0);
        }

        return items;

    }

    static int largerChildIndex(int[] arr, int newSize, int i) {
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        // 0 children
        if (leftIndex > newSize - 1) {
            return -1;
        }

        // Check if right child exists, and if so, check if it's bigger.
        if (rightIndex <= newSize - 1 && arr[rightIndex] > arr[leftIndex]) {
            return rightIndex;
        }
        // Otherwise, left child
        return leftIndex;
    }

    public static void maxHeapify(int[] items, int sizeOfHeap, int index) {
        int largestIndex = index;    // Largest value initialized as the root
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        // Check the left index value and see if it's larger than the root
        if (leftIndex < sizeOfHeap && items[leftIndex] > items[largestIndex]) {
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

    static void heapifyDown(int[] items, int sizeOfHeap, int i) {
        int curr = i;
        // while curr has a child & there is a violation of the heap property with the children,
        // swap val in curr node with the value in the larger child
        int largerIndex = largerChildIndex(items, sizeOfHeap, curr);
        while (largerIndex != -1 && items[curr] < items[largerIndex]) {
            swap(items, curr, largerIndex);

            curr = largerIndex;
            largerIndex = largerChildIndex(items, sizeOfHeap, curr);
        }
    }

    // Swap without needing temp variable.
    static void swap(int[] items, int first, int second) {

        // Necessary if using ArrayLists.
        // Also, a simple enough optimization.
        if (first == second) {
            return;
        }

        // Swapping logic:
        // NOTE: TESTED WITH VALUES THAT OVERFLOW AND STILL WORKS
        //
        // FIRST = 25, SECOND = 23
        //
        // FIRST = FIRST + SECOND
        // 25 + 23 = 48
        //
        // SECOND = FIRST - SECOND
        // 48 - 23 = 25
        //
        // FIRST = FIRST - SECOND
        // 48 - 25 = 23
        //
        // NOTE: Deleted one line version because it only works with arrays so best to
        // just use three line version since it's more consistent.
        //
        // ArrayList:
        // items.set(first, items.get(first) + items.get(second));
        // items.set(second, items.get(first) - items.get(second));
        // items.set(first, items.get(first) - items.get(second));
        //
        // Array:
        items[first] = items[first] + items[second];
        items[second] = items[first] - items[second];
        items[first] = items[first] - items[second];
    }

    enum ImplementationVersion {
        OMKAR, MATT
    }

    // Swap with temp variable.
    // private static void swap(int[] items, int first, int second) {
    //     int temp = items[first];
    //     items[first] = items[second];
    //     items[second] = temp;
    // }
}
