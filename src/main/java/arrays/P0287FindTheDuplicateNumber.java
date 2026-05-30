package arrays;

/**
 * LeetCode 287 — Find the Duplicate Number
 * <p>
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and using only
 * constant extra space.
 * <p>
 * Constraints:
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer
 * which appears two or more times.
 * <p>
 * Follow up: How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 * <p>
 * Example 1:
 * Input:  nums = [1,3,4,2,2]
 * Output: 2
 * <p>
 * Example 2:
 * Input:  nums = [3,1,3,4,2]
 * Output: 3
 */
public class P0287FindTheDuplicateNumber {

    // Floyd's cycle detection (treat array as linked list). O(n) time, O(1) space.
    public int findDuplicate(int[] nums) {
        // TODO: implement
        return -1;
    }

    // Binary search on value range. O(n log n) time, O(1) space.
    public int findDuplicateBinarySearch(int[] nums) {
        // TODO: implement
        return -1;
    }

    public static void main(String[] args) {
        P0287FindTheDuplicateNumber solution = new P0287FindTheDuplicateNumber();

        System.out.println("Input:    [1,3,4,2,2]");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println();

        System.out.println("Input:    [3,1,3,4,2]");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println();

        System.out.println("Input:    [1,1] (smallest case)");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.findDuplicate(new int[]{1, 1}));
        System.out.println();

        System.out.println("Input:    [2,2,2,2,2] (all same)");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.findDuplicate(new int[]{2, 2, 2, 2, 2}));
        System.out.println();

        System.out.println("Input:    [1,4,4,2,4] (duplicate appears 3x)");
        System.out.println("Expected: 4");
        System.out.println("Actual:   " + solution.findDuplicate(new int[]{1, 4, 4, 2, 4}));
    }
}
