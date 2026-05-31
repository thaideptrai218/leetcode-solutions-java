package arrays;

import java.util.Arrays;

/**
 * LeetCode 26 — Remove Duplicates from Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once. The
 * relative order of the elements should be kept the same. Then return the
 * number of unique elements k.
 * <p>
 * To get accepted:
 *   - Change nums so that the first k elements contain the unique elements in the
 *     order they originally appeared. The rest of nums (and its size) don't matter.
 *   - Return k.
 * <p>
 * Custom Judge: unlike problem 27, ORDER MATTERS here. The judge compares the
 * first k elements positionally against the expected answer — no re-sorting.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 * <p>
 * Example 1:
 * Input:  nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * <p>
 * Example 2:
 * Input:  nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 */
public class P0026RemoveDuplicatesFromSortedArray {

    /**
     * Remove duplicates in-place from a SORTED array; return the count of uniques.
     * Target: O(n) time, O(1) extra space.
     * <p>
     * Hint: it's the same slow/fast WRITE pointer from problem 27 — one twist.
     *   - In 27 you kept an element when it differed from a fixed `val`.
     *   - Here you keep an element when it differs from the LAST element you kept,
     *     i.e. compare nums[read] against nums[write - 1].
     *   - This works ONLY because the array is sorted: duplicates are adjacent, so
     *     "different from the previous kept value" == "a brand-new unique value".
     * <p>
     * Edge to think about: what is nums[write - 1] when write == 0? The first
     * element is always unique (nothing precedes it), so you can sidestep that by
     * starting write at 1 (and read at 1).
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == nums[slow]) continue;
            slow++;
            nums[slow] = nums[fast];
        }


        return slow + 1;
    }

    public static void main(String[] args) {
        P0026RemoveDuplicatesFromSortedArray solution = new P0026RemoveDuplicatesFromSortedArray();

        test(solution, new int[]{1, 1, 2}, 2, new int[]{1, 2});
        test(solution, new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5, new int[]{0, 1, 2, 3, 4});
        test(solution, new int[]{1}, 1, new int[]{1});                            // single element
        test(solution, new int[]{1, 1, 1, 1}, 1, new int[]{1});                   // all duplicates
        test(solution, new int[]{1, 2, 3, 4}, 4, new int[]{1, 2, 3, 4});          // no duplicates
        test(solution, new int[]{-100, -100, 0, 0, 100}, 3, new int[]{-100, 0, 100}); // negatives
        test(solution, new int[]{1, 1, 2, 2}, 2, new int[]{1, 2});                // pairs
    }

    /**
     * Mirrors LeetCode's judge for THIS problem: checks k, then compares the first
     * k elements in EXACT order (no sorting — relative order must be preserved).
     */
    private static void test(P0026RemoveDuplicatesFromSortedArray solution, int[] nums,
                             int expectedK, int[] expectedUniques) {
        int[] original = Arrays.copyOf(nums, nums.length);

        int k = solution.removeDuplicates(nums);

        int[] firstK = Arrays.copyOf(nums, k);    // exact order, NOT sorted
        boolean pass = (k == expectedK) && Arrays.equals(firstK, expectedUniques);

        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: k=" + expectedK + ", first k=" + Arrays.toString(expectedUniques));
        System.out.println("Actual:   k=" + k + ", first k=" + Arrays.toString(firstK));
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
