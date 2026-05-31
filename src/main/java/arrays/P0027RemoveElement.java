package arrays;

import java.util.Arrays;

/**
 * LeetCode 27 — Remove Element
 * <p>
 * Given an integer array nums and an integer val, remove all occurrences of val
 * in nums in-place. The order of the elements may be changed. Then return the
 * number of elements in nums which are not equal to val.
 * <p>
 * Consider the number of elements in nums which are not equal to val to be k.
 * To get accepted, you need to do the following:
 *   - Change nums so that the first k elements contain the elements not equal to val.
 *     The remaining elements (and the size of nums) are not important.
 *   - Return k.
 * <p>
 * Custom Judge: the order of the first k elements does NOT matter, and whatever
 * is left after index k is ignored. So [2,2] and any arrangement of the kept
 * elements are both accepted.
 * <p>
 * Constraints:
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 * <p>
 * Example 1:
 * Input:  nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * <p>
 * Example 2:
 * Input:  nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 */
public class P0027RemoveElement {

    /**
     * Remove all occurrences of val in-place; return the count of kept elements.
     * Target: O(n) time, O(1) extra space.
     * <p>
     * Hint: two pointers walking the same array.
     *   - One pointer (call it the WRITE index) marks where the next kept element goes.
     *   - The other (the READ index) scans every element left to right.
     *   - When nums[read] should be KEPT (it is not equal to val), copy it to
     *     nums[write] and advance write. When it equals val, just skip it (advance
     *     read only).
     *   - At the end, write IS the count k — every slot before it holds a kept value.
     * <p>
     * Why this is correct: write never gets ahead of read, so you only ever
     * overwrite slots you've already read.
     */
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int read = 0;
        int write = 0;

        while (write < n && nums[write] != val) {
            write++;
            read++;
        }

        while (read < n) {
            while (read < n && nums[read] == val) {
                read++;
            }
            if (read < n && write < read) {
                nums[write] = nums[read];
                nums[read] = -999;
                write++;
            }
            read++;

        }
        return write;
    }

    public static void main(String[] args) {
        P0027RemoveElement solution = new P0027RemoveElement();

        test(solution, new int[]{3, 2, 2, 3}, 3, 2, new int[]{2, 2});
        test(solution, new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5, new int[]{0, 0, 1, 3, 4});
        test(solution, new int[]{}, 0, 0, new int[]{});                       // empty array
        test(solution, new int[]{1}, 1, 0, new int[]{});                      // single element, removed
        test(solution, new int[]{1}, 2, 1, new int[]{1});                     // single element, kept
        test(solution, new int[]{4, 4, 4, 4}, 4, 0, new int[]{});             // all removed
        test(solution, new int[]{1, 2, 3}, 5, 3, new int[]{1, 2, 3});         // nothing removed
    }

    /**
     * Mirrors LeetCode's custom judge: checks the returned k, then sorts the first
     * k elements and compares against the expected set (because order is free).
     */
    private static void test(P0027RemoveElement solution, int[] nums, int val,
                             int expectedK, int[] expectedRemaining) {
        int[] original = Arrays.copyOf(nums, nums.length);

        int k = solution.removeElement(nums, val);

        int[] firstK = Arrays.copyOf(nums, k);
        Arrays.sort(firstK);                      // order of kept elements doesn't matter
        int[] expectedSorted = Arrays.copyOf(expectedRemaining, expectedRemaining.length);
        Arrays.sort(expectedSorted);

        boolean pass = (k == expectedK) && Arrays.equals(firstK, expectedSorted);

        System.out.println("Input:    nums=" + Arrays.toString(original) + ", val=" + val);
        System.out.println("Expected: k=" + expectedK + ", first k (sorted)=" + Arrays.toString(expectedSorted));
        System.out.println("Actual:   k=" + k + ", first k (sorted)=" + Arrays.toString(firstK));
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
