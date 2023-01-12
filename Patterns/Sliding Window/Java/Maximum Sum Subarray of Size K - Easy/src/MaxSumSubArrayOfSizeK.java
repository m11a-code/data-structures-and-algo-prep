import java.util.ArrayList;

/*
Given an array of positive numbers and a positive number ‘k,’
find the maximum sum of any contiguous subarray of size ‘k’.

Example 1:

Input: [2, 1, 5, 1, 3, 2], k=3
Output: 9
Explanation: Subarray with maximum sum is [5, 1, 3].
Example 2:

Input: [2, 3, 4, 1, 5], k=2
Output: 7
Explanation: Subarray with maximum sum is [3, 4].

 */
public class MaxSumSubArrayOfSizeK {
    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
    }

    public static int findMaxSumSubArray(int k, int[] arr) {
        int windowSum = 0, windowStart = 0, maxSum = 0;
        ArrayList<Integer> maxSubArray = new ArrayList<>();
        ArrayList<Integer> currentSubArray = new ArrayList<>();
        for (int windowEnd = 0; windowEnd < arr.length; ++windowEnd) {
            windowSum += arr[windowEnd];
            currentSubArray.add(arr[windowEnd]);

            if (windowEnd >= k - 1) {
                if (maxSum < windowSum) {
                    maxSum = windowSum;
                    maxSubArray.clear();
                    maxSubArray.addAll(currentSubArray);
                }
                windowSum -= arr[windowStart];
                currentSubArray.remove(0);
                ++windowStart;
            }
        }

        System.out.println(maxSubArray);
        return maxSum;
    }
}
