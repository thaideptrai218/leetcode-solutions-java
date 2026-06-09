package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128 — Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * Example 1:
 * Input:  nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive sequence is [1, 2, 3, 4]. Its length is 4.
 * <p>
 * Example 2:
 * Input:  nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class P0128LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int result = 0;
        int count;

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i : set) {
            int value = i;
            if (set.contains(value - 1)) continue;
            count = 1;
            while (set.contains(value + 1)) {
                count++;
                value++;
            }
            result = Math.max(count, result);
        }
        return result;
    }

    //O(nlogn) time complexity
    public int longestConsecutiveSorting(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        int left = 0;
        int prev = Integer.MIN_VALUE;
        int count = 1;

        while (left < nums.length) {
            int cur = nums[left];

            if (cur == prev) {
                left++;
                continue;
            } else if (prev + 1 == cur) {
                count++;
            } else {
                count = 1;
            }
            result = Math.max(count, result);
            prev = cur;
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        P0128LongestConsecutiveSequence solution = new P0128LongestConsecutiveSequence();

        test(solution, new int[]{100, 4, 200, 1, 3, 2}, 4);
        test(solution, new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9);
        test(solution, new int[]{}, 0);                        // empty
        test(solution, new int[]{5}, 1);                       // single element
        test(solution, new int[]{1, 2, 0, 1}, 3);              // duplicates: [0,1,2]
        test(solution, new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}, 7); // [3..9]
        test(solution, new int[]{-3, -2, -1, 0}, 4);           // negatives
        test(solution, new int[]{10, 30, 20, 40}, 1);          // no consecutive run
    }

    private static void test(P0128LongestConsecutiveSequence solution, int[] nums, int expected) {
        int[] original = Arrays.copyOf(nums, nums.length);

        int actual = solution.longestConsecutive(nums);

        boolean pass = (actual == expected);
        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
