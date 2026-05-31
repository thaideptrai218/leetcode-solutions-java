package arrays;

import java.util.Arrays;

/**
 * LeetCode 1929 — Concatenation of Array
 * <p>
 * Given an integer array nums of length n, you want to create an array ans of
 * length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for
 * 0 <= i < n (0-indexed).
 * <p>
 * Specifically, ans is the concatenation of two nums arrays.
 * <p>
 * Return the array ans.
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 1000
 * <p>
 * Example 1:
 * Input:  nums = [1,2,1]
 * Output: [1,2,1,1,2,1]
 * Explanation: ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 * <p>
 * Example 2:
 * Input:  nums = [1,3,2,1]
 * Output: [1,3,2,1,1,3,2,1]
 * Explanation: ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 */
public class P1929ConcatenationOfArray {

    /**
     * Build the length-2n answer. O(n) time, O(n) space (the output array).
     * <p>
     * Hint: allocate the result with the correct size FIRST (how big must it be?),
     * then think about where each nums[i] lands. Each original value shows up at
     * two positions in the result — what are those two index formulas?
     * <p>
     * There is more than one valid approach (a single index loop, two separate
     * copy loops, or a library bulk-copy). Pick whichever you find clearest.
     */
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[n * 2];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i];
            res[i + n] = nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        P1929ConcatenationOfArray solution = new P1929ConcatenationOfArray();

        System.out.println("Input:    [1,2,1]");
        System.out.println("Expected: [1, 2, 1, 1, 2, 1]");
        System.out.println("Actual:   " + Arrays.toString(solution.getConcatenation(new int[]{1, 2, 1})));
        System.out.println();

        System.out.println("Input:    [1,3,2,1]");
        System.out.println("Expected: [1, 3, 2, 1, 1, 3, 2, 1]");
        System.out.println("Actual:   " + Arrays.toString(solution.getConcatenation(new int[]{1, 3, 2, 1})));
        System.out.println();

        System.out.println("Input:    [1] (single element)");
        System.out.println("Expected: [1, 1]");
        System.out.println("Actual:   " + Arrays.toString(solution.getConcatenation(new int[]{1})));
        System.out.println();

        System.out.println("Input:    [5,9] (two elements)");
        System.out.println("Expected: [5, 9, 5, 9]");
        System.out.println("Actual:   " + Arrays.toString(solution.getConcatenation(new int[]{5, 9})));
        System.out.println();

        System.out.println("Input:    [7,7,7] (duplicates)");
        System.out.println("Expected: [7, 7, 7, 7, 7, 7]");
        System.out.println("Actual:   " + Arrays.toString(solution.getConcatenation(new int[]{7, 7, 7})));
    }
}
