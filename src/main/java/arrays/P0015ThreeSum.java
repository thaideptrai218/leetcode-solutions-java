package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * LeetCode 15 — 3Sum
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Constraints:
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * Example 1:
 * Input:  nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation: The distinct triplets are [-1,0,1] and [-1,-1,2].
 * <p>
 * Example 2:
 * Input:  nums = [0,1,1]
 * Output: []
 * <p>
 * Example 3:
 * Input:  nums = [0,0,0]
 * Output: [[0,0,0]]
 */
public class P0015ThreeSum {

    /**
     * Sort + two pointers. O(n^2) time, O(1) extra space (ignoring output).
     * Hint: sort first. Fix one element nums[i], then run a two-pointer scan
     * on the rest to find pairs summing to -nums[i].
     * The hard part is SKIPPING DUPLICATES — both for the fixed element
     * and for the two moving pointers after finding a match.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int complement = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            int prevLeft = -999;

            while (left < right) {
                while (left < nums.length && nums[left] == prevLeft) left++;
                if (left >= right) break;
                if (nums[left] + nums[right] == complement) {
                    res.add(new ArrayList<>(List.of(nums[i], nums[left], nums[right])));
                    prevLeft = nums[left];
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > complement) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P0015ThreeSum solution = new P0015ThreeSum();

        System.out.println("Input:    [-1,0,1,2,-1,-4]");
        System.out.println("Expected: [[-1,-1,2],[-1,0,1]]");
        System.out.println("Actual:   " + solution.threeSum(new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4}));
        System.out.println();

        System.out.println("Input:    [0,1,1]");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + solution.threeSum(new int[]{0, 1, 1}));
        System.out.println();

        System.out.println("Input:    [0,0,0]");
        System.out.println("Expected: [[0,0,0]]");
        System.out.println("Actual:   " + solution.threeSum(new int[]{0, 0, 0}));
        System.out.println();

        System.out.println("Input:    [0,0,0,0] (extra zeros, no dup triplets)");
        System.out.println("Expected: [[0,0,0]]");
        System.out.println("Actual:   " + solution.threeSum(new int[]{0, 0, 0, 0}));
        System.out.println();

        System.out.println("Input:    [-2,0,1,1,2] (multiple triplets)");
        System.out.println("Expected: [[-2,0,2],[-2,1,1]]");
        System.out.println("Actual:   " + solution.threeSum(new int[]{-2, 0, 1, 1, 2}));
        System.out.println();

        System.out.println("Input:    [-1,-1,-1] (no solution)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + solution.threeSum(new int[]{-1, -1, -1}));
    }
}
