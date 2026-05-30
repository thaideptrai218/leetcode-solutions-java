package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1365 — How Many Numbers Are Smaller Than the Current Number
 * Given the array nums, for each nums[i] find out how many numbers in the array
 * are smaller than it. That is, for each nums[i] you have to count the number
 * of valid j's such that j != i and nums[j] < nums[i].
 * Return the answer in an array.
 * <p>
 * Constraints:
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class P1365HowManyNumbersAreSmallerThanTheCurrentNumber {

    // Brute force: for each element, scan the rest. O(n^2) time, O(1) extra space.
    public int[] bruteforce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (nums[j] < nums[i]) result[i] += 1;
            }
        }
        return result;
    }

    // Sorting approach: sort a copy, first occurrence index = count of smaller. O(n log n) time.
    public int[] sorting(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        // Map value -> first index in sorted array (= count of strictly smaller numbers).
        Map<Integer, Integer> firstIndex = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            firstIndex.putIfAbsent(sorted[i], i);
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = firstIndex.get(nums[i]);
        }
        return result;
    }

    // Counting sort / bucket: leverage value range [0, 100]. O(n + k) time, O(k) space.
    public int[] countingSort(int[] nums) {
        // Step 1: bucket[v] = how many times v appears.
        int[] bucket = new int[9];
        for (int num : nums) {
            bucket[num] += 1;
        }

        // Step 2: prefix sum -> bucket[v] = count of numbers <= v.
        for (int v = 1; v < bucket.length; v++) {
            bucket[v] += bucket[v - 1];
        }

        // Step 3: count of numbers strictly less than nums[i] is bucket[nums[i] - 1].
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] == 0 ? 0 : bucket[nums[i] - 1];
        }
        return result;
    }

    public static void main(String[] args) {
        P1365HowManyNumbersAreSmallerThanTheCurrentNumber solution =
                new P1365HowManyNumbersAreSmallerThanTheCurrentNumber();

        int[] nums1 = {8, 1, 2, 2, 3};
        System.out.println("Input:    [8,1,2,2,3]");
        System.out.println("Expected: [4, 0, 1, 1, 3]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.countingSort(nums1)));
        System.out.println();

        int[] nums2 = {6, 5, 4, 8};
        System.out.println("Input:    [6,5,4,8]");
        System.out.println("Expected: [2, 1, 0, 3]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.bruteforce(nums2)));
        System.out.println();

        int[] nums3 = {7, 7, 7, 7};
        System.out.println("Input:    [7,7,7,7]");
        System.out.println("Expected: [0, 0, 0, 0]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.bruteforce(nums3)));
    }
}
