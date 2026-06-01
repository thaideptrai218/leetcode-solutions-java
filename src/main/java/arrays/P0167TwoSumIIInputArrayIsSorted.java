package arrays;

import java.util.Arrays;

/**
 * LeetCode 167 — Two Sum II - Input Array Is Sorted
 * <p>
 * Given a 1-indexed array of integers numbers that is already sorted in
 * non-decreasing order, find two numbers such that they add up to a specific
 * target number. Return the indices of the two numbers (1-indexed) as an
 * integer array [index1, index2] where 1 <= index1 < index2 <= numbers.length.
 * <p>
 * You must use only constant extra space.
 * <p>
 * Constraints:
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 * <p>
 * Example 1:
 * Input:  numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input:  numbers = [2,3,4], target = 6
 * Output: [1,3]
 * <p>
 * Example 3:
 * Input:  numbers = [-1,0], target = -1
 * Output: [1,2]
 */
public class P0167TwoSumIIInputArrayIsSorted {

    // Brute force: try every pair. O(n^2) time, O(1) space.
    public int[] bruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) return new int[]{i + 1, j + 1};
            }
        }
        return new int[0];
    }

    // Binary search: for each element, binary-search its complement. O(n log n) time, O(1) space.
    public int[] binarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            int complement = target - current;

            int left = i + 1;
            int right = numbers.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (complement == numbers[mid]) {
                    return new int[]{i + 1, mid + 1};
                } else if (complement < numbers[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[0];
    }

    // Two pointers: shrink window using sorted order. O(n) time, O(1) space.
    public int[] twoPointers(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length -1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) return new int[]{left + 1, right + 1};
            else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        P0167TwoSumIIInputArrayIsSorted solution = new P0167TwoSumIIInputArrayIsSorted();

        int[] nums1 = {2, 7, 11, 15};
        System.out.println("Input:    numbers=[2,7,11,15], target=9");
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.twoPointers(nums1, 9)));
        System.out.println();

        int[] nums2 = {2, 3, 4};
        System.out.println("Input:    numbers=[2,3,4], target=6");
        System.out.println("Expected: [1, 3]");
        System.out.println("Actual:   " + Arrays.toString(solution.twoPointers(nums2, 6)));
        System.out.println();

        int[] nums3 = {-1, 0};
        System.out.println("Input:    numbers=[-1,0], target=-1");
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.twoPointers(nums3, -1)));
        System.out.println();

        int[] nums4 = {1, 2, 3, 4, 4, 9, 56, 90};
        System.out.println("Input:    numbers=[1,2,3,4,4,9,56,90], target=8");
        System.out.println("Expected: [4, 5]");
        System.out.println("Actual:   " + Arrays.toString(solution.twoPointers(nums4, 8)));
    }
}
