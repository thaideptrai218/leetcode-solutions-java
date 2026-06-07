package arrays;

import java.util.Arrays;

/**
 * LeetCode 912 — Sort an Array
 * <p>
 * Given an array of integers nums, sort the array in ascending order and return it.
 * <p>
 * You must solve it WITHOUT using any built-in sort, in O(n log n) time, and with
 * the smallest space complexity you can manage.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 * <p>
 * Example 1:
 * Input:  nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * <p>
 * Example 2:
 * Input:  nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,2]
 */
public class P0912SortAnArray {

    /**
     * Sort nums ascending and return it. Must be O(n log n).
     * <p>
     * WHY the usual easy sorts won't pass: bubble / insertion / selection sort are
     * O(n^2). With n up to 50,000 that's ~2.5 billion operations → Time Limit
     * Exceeded. You need a divide-and-conquer sort.
     * <p>
     * RECOMMENDED: MERGE SORT (it leans on the recursion + two-pointer merge you
     * already know). The recursive shape:
     * 1. Base case: a range of length 0 or 1 is already sorted — return.
     * 2. Divide:    split the range in half (mid = left + (right - left) / 2).
     * 3. Conquer:   recursively sort the left half and the right half.
     * 4. Combine:   MERGE the two now-sorted halves into one sorted range.
     * <p>
     * The merge step IS the algorithm — it's literally LeetCode 21 (Merge Two Sorted
     * Lists) on arrays: walk both halves with two pointers, repeatedly copy the
     * smaller front value into a temp array, then copy the temp back into nums.
     * <p>
     * Complexity: O(n log n) time (log n split levels × O(n) merge work each level),
     * O(n) extra space for the temp array used during merging.
     * <p>
     * Other O(n log n) options (for later): quick sort (in-place, O(1) extra space,
     * but O(n^2) worst case and a trickier partition) and heap sort (needs a heap —
     * skip until you've learned heaps).
     */
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        mergeSortRecursion(nums, left, right);
        return nums;
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] l = new int[mid - left + 1];
        int[] r = new int[right - mid];

        for (int i = 0; i < l.length; i++) {
            l[i] = nums[i + left];
        }

        for (int i = 0; i < r.length; i++) {
            r[i] = nums[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                nums[k] = l[i];
                i++;
            } else {
                nums[k] = r[j];
                j++;
            }
            k++;
        }

        while (i < l.length) {
            nums[k] = l[i];
            i++;
            k++;
        }

        while (j < r.length) {
            nums[k] = r[j];
            j++;
            k++;
        }
    }

    public void mergeSortRecursion(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortRecursion(nums, left, mid);
        mergeSortRecursion(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }


    public static void main(String[] args) {
        P0912SortAnArray solution = new P0912SortAnArray();

        test(solution, new int[]{5, 2, 3, 1}, new int[]{1, 2, 3, 5});
        test(solution, new int[]{5, 1, 1, 2, 0, 0}, new int[]{0, 0, 1, 1, 2, 2});
        test(solution, new int[]{1}, new int[]{1});                              // single element
        test(solution, new int[]{2, 1}, new int[]{1, 2});                        // two elements
        test(solution, new int[]{3, 3, 3}, new int[]{3, 3, 3});                  // all duplicates
        test(solution, new int[]{-5, -10, 0, 3, -1}, new int[]{-10, -5, -1, 0, 3}); // negatives
        test(solution, new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5});      // reverse sorted
        test(solution, new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5});      // already sorted

        // Stress: large reversed array — verifies it actually sorts AND won't blow
        // the recursion stack. (A correct merge sort handles 50k fine.)
        int n = 50_000;
        int[] big = new int[n];
        for (int i = 0; i < n; i++) big[i] = n - i;   // n, n-1, ..., 1
        int[] sortedBig = solution.sortArray(big);
        boolean bigOk = true;
        for (int i = 0; i < n; i++) if (sortedBig[i] != i + 1) bigOk = false;
        System.out.println("Stress 50k reversed -> sorted: " + (bigOk ? "PASS" : "FAIL"));
    }

    private static void test(P0912SortAnArray solution, int[] input, int[] expected) {
        int[] original = Arrays.copyOf(input, input.length);

        int[] actual = solution.sortArray(input);

        boolean pass = Arrays.equals(actual, expected);
        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
