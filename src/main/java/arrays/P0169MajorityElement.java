package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 169 — Majority Element
 * <p>
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * Follow-up: Could you solve it in O(n) time and O(1) space?
 * <p>
 * Example 1:
 * Input:  nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input:  nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
public class P0169MajorityElement {

    /**
     * Return the element that appears more than n/2 times (guaranteed to exist).
     * <p>
     * There are THREE approaches worth knowing — pick one to implement first,
     * then come back and try the others by swapping the body of this method:
     * <p>
     * 1) HashMap counting — O(n) time, O(n) space.
     * Count how many times each value appears (same pattern as problem 347).
     * Return the first value whose count exceeds n/2.
     * <p>
     * 2) Sorting — O(n log n) time, O(1) extra space.
     * Sort the array, then return nums[n / 2]. Worth pausing on: WHY is the
     * middle element always the majority? (Hint: if one value fills more than
     * half the slots, it must cover the center no matter where it sits.)
     * <p>
     * 3) Boyer-Moore Voting — O(n) time, O(1) space (the follow-up answer).
     * Intuition: imagine cancelling out each pair of DIFFERENT elements. Since
     * the majority appears more than half the time, it is the last one left
     * standing after all cancellations. Try to turn that intuition into a single
     * "candidate" value plus a running "count". This one is non-obvious — derive
     * it from the cancellation idea rather than memorizing it.
     */
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count++;
                continue;
            }

            if (candidate != num) {
                count--;
            } else {
                count++;
            }

        }

        return candidate;
    }

    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value > n / 2) return entry.getKey();
        }
        return 0;
    }

    public static void main(String[] args) {
        P0169MajorityElement solution = new P0169MajorityElement();

        test(solution, new int[]{3, 2, 3}, 3);
        test(solution, new int[]{2, 2, 1, 1, 1, 2, 2}, 2);
        test(solution, new int[]{1}, 1);                          // single element
        test(solution, new int[]{1, 2, 2}, 2);                    // majority right at the boundary
        test(solution, new int[]{7, 7, 7, 7}, 7);                 // all the same
        test(solution, new int[]{-1, -1, -1, 2, 3}, -1);          // negatives
        test(solution, new int[]{5, 4, 5, 4, 5}, 5);              // interleaved
    }

    private static void test(P0169MajorityElement solution, int[] nums, int expected) {
        int[] original = Arrays.copyOf(nums, nums.length);

        int actual = solution.majorityElement(nums);

        boolean pass = (actual == expected);
        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
