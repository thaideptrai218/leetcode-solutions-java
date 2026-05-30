package arrays;

import java.util.Arrays;

/**
 * LeetCode 238 — Product of Array Except Self
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal
 * to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 * <p>
 * Example 1:
 * Input:  nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * Input:  nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class P0238ProductOfArrayExceptSelf {

    /**
     * Prefix * suffix products. O(n) time, O(1) extra space (output excluded), no division.
     * Hint: answer[i] = (product of everything LEFT of i) * (product of everything RIGHT of i).
     * First pass left-to-right: fill answer[i] with the prefix product.
     * Second pass right-to-left: multiply each answer[i] by the running suffix product.
     * You only need ONE running variable for the suffix — no second array.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);

        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        P0238ProductOfArrayExceptSelf solution = new P0238ProductOfArrayExceptSelf();

        System.out.println("Input:    [1,2,3,4]");
        System.out.println("Expected: [24, 12, 8, 6]");
        System.out.println("Actual:   " + Arrays.toString(solution.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println();

        System.out.println("Input:    [-1,1,0,-3,3]");
        System.out.println("Expected: [0, 0, 9, 0, 0]");
        System.out.println("Actual:   " + Arrays.toString(solution.productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println();

        System.out.println("Input:    [2,3] (smallest case)");
        System.out.println("Expected: [3, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.productExceptSelf(new int[]{2, 3})));
        System.out.println();

        System.out.println("Input:    [0,0] (two zeros)");
        System.out.println("Expected: [0, 0]");
        System.out.println("Actual:   " + Arrays.toString(solution.productExceptSelf(new int[]{0, 0})));
        System.out.println();

        System.out.println("Input:    [1,2,3,4,5] (odd length)");
        System.out.println("Expected: [120, 60, 40, 30, 24]");
        System.out.println("Actual:   " + Arrays.toString(solution.productExceptSelf(new int[]{1, 2, 3, 4, 5})));
    }
}
