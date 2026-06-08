package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 75 — Sort Colors
 * <p>
 * Given an array nums with n objects colored red, white, or blue, sort them
 * in-place so that objects of the same color are adjacent, in the order red,
 * white, blue. We use 0 = red, 1 = white, 2 = blue.
 * <p>
 * You must solve this WITHOUT using the library's sort function.
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * <p>
 * Follow-up: a ONE-PASS algorithm using only O(1) extra space.
 * <p>
 * Example 1:
 * Input:  nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Example 2:
 * Input:  nums = [2,0,1]
 * Output: [0,1,2]
 */
public class P0075SortColors {

    /**
     * Sort the array of 0s, 1s, and 2s in-place. Note the return type is void —
     * you mutate nums directly, you don't return a new array.
     * <p>
     * TWO approaches worth knowing:
     * <p>
     * 1) COUNTING SORT (two passes) — easiest. Since there are only THREE possible
     * values, count how many 0s, 1s, and 2s there are in one pass, then overwrite
     * nums in a second pass: that many 0s, then that many 1s, then that many 2s.
     * O(n) time, O(1) space (just three counters). Get this working first.
     * <p>
     * 2) DUTCH NATIONAL FLAG (one pass) — the follow-up. Three pointers partition the
     * array into four regions as you sweep:
     * [0 .. low-1]   = all 0s   (settled)
     * [low .. mid-1] = all 1s   (settled)
     * [mid .. high]  = UNKNOWN  (still to inspect)
     * [high+1 .. n-1]= all 2s   (settled)
     * You advance `mid` through the unknown region:
     * - see a 0 → it belongs on the left: swap it into the low region.
     * - see a 1 → it's already in the right place: just move past it.
     * - see a 2 → it belongs on the right: swap it into the high region.
     * THE SUBTLE PART (this is the #1 bug): after swapping with `high`, do you
     * advance `mid`? The value you just pulled in from `high` is UNKNOWN — you
     * haven't looked at it yet. After swapping with `low`, the value you pulled in
     * is already known to be a 1. Think carefully about which swaps let you
     * advance `mid` and which don't.
     */
    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            nums[i] = 0;
        }

        int i = 0;
        for (int j = 0; j < map.getOrDefault(0, 0); j++, i++) {
            nums[i] = 0;
        }

        for (int j = 0; j < map.getOrDefault(1, 0); i++, j++) {
            nums[i] = 1;
        }

        for (int j = 0; j < map.getOrDefault(2, 0); i++, j++) {
            nums[i] = 2;
        }
    }

    public static void main(String[] args) {
        P0075SortColors solution = new P0075SortColors();

        test(solution, new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
        test(solution, new int[]{2, 0, 1}, new int[]{0, 1, 2});
        test(solution, new int[]{0}, new int[]{0});                          // single 0
        test(solution, new int[]{2}, new int[]{2});                          // single 2
        test(solution, new int[]{1, 1, 1}, new int[]{1, 1, 1});              // all same
        test(solution, new int[]{2, 2, 1, 1, 0, 0}, new int[]{0, 0, 1, 1, 2, 2}); // reverse
        test(solution, new int[]{0, 1, 2, 0, 1, 2}, new int[]{0, 0, 1, 1, 2, 2});
        test(solution, new int[]{1, 0}, new int[]{0, 1});                    // tiny swap
        test(solution, new int[]{2, 0, 0, 1, 2, 1, 0}, new int[]{0, 0, 0, 1, 1, 2, 2});
    }

    private static void test(P0075SortColors solution, int[] input, int[] expected) {
        int[] original = Arrays.copyOf(input, input.length);

        solution.sortColors(input);   // mutates in place, returns void

        boolean pass = Arrays.equals(input, expected);
        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual:   " + Arrays.toString(input));
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
