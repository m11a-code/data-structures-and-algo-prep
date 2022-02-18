import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

/**
 * <h2>739. Daily Temperatures</h2>
 * <p>
 * Given an array of integers `temperatures` represents the daily temperatures, return _an array `answer` such that
 * `answer[i]` is the number of days you have to wait after the i<sup>th</sup> day to get a warmer temperature._
 * <p>
 * If there is no future day for which this is possible, keep `answer[i] == 0` instead.
 * <p>
 * **Example 1:**
 * ```
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        // TODO: ADD TEST CASES CODE
        int[] testcase1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] testcase2 = {30, 40, 50, 60};
        int[] testcase3 = {30, 60, 90};
        System.out.println("Test case 1: " + Arrays.toString(testcase1));
        System.out.println(Arrays.toString(getDaysUntilWarmerTemperature(testcase1)));
        System.out.println("Test case 2: " + Arrays.toString(testcase2));
        System.out.println(Arrays.toString(getDaysUntilWarmerTemperature(testcase2)));
        System.out.println("Test case 3: " + Arrays.toString(testcase3));
        System.out.println(Arrays.toString(getDaysUntilWarmerTemperature(testcase3)));
    }


    private static int[] getDaysUntilWarmerTemperature(int[] temperatures) {
        // Use stack to go through data
        Stack<Map.Entry<Integer, Integer>> tempIndexStack = new Stack<>();

        // Check if there are any provided values.
        if (temperatures.length == 0) {
            return null;
        }

        int[] result = new int[temperatures.length];

        int index = 0;
        for (int temp : temperatures) {
            while (!tempIndexStack.isEmpty() && tempIndexStack.peek().getKey() < temp) {
                // Stack item
                Map.Entry<Integer, Integer> pair = tempIndexStack.pop();
                result[pair.getValue()] = index - pair.getValue();
            }
            tempIndexStack.push(new AbstractMap.SimpleEntry<Integer, Integer>(temp, index));
            ++index;
        }

        return result;
    }
}
