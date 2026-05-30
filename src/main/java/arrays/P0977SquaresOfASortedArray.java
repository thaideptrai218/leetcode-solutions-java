package arrays;

import java.util.Arrays;

/**
 * LeetCode 977 — Squares of a Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, return an array
 * of the squares of each number sorted in non-decreasing order.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 * <p>
 * Follow up: Squaring each element and sorting the array is O(n log n).
 * Can you find an O(n) solution using a different approach?
 * <p>
 * Example 1:
 * Input:  nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * <p>
 * Example 2:
 * Input:  nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class P0977SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int count = n - 1;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            if (Math.pow(nums[left], 2) >= Math.pow(nums[right], 2)) {
                result[count] = (int) Math.pow(nums[left], 2);
                left++;
            } else {
                result[count] = (int) Math.pow(nums[right], 2);
                right--;
            }
            count--;
        }
        return result;
    }

    public int[] sortedSquares2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int cur = n - 1;

        int pivot = cur;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                pivot = i;
                break;
            };
        }
        int l = 0, r = n - 1;
        while (l < pivot && r >= pivot) {
            int leftValue = nums[l] * nums[l];
            int rightValue = nums[r] * nums[r];


            if (leftValue > rightValue) {
                result[cur] = leftValue;
                l++;
            } else {
                result[cur] = rightValue;
                r--;
            }
            cur--;
        }

        while (l < pivot) {
            int leftValue = nums[l] * nums[l];
            int rightValue = nums[r] * nums[r];
            result[cur] = leftValue;
            l++;
            cur--;
        }

        while (r > pivot) {
            int leftValue = nums[l] * nums[l];
            int rightValue = nums[r] * nums[r];
            result[cur] = rightValue;
            r--;
            cur--;
        }

        return result;

    }

    public static void main(String[] args) {
        P0977SquaresOfASortedArray solution = new P0977SquaresOfASortedArray();

        int[] nums1 = {-4, -1, 0, 3, 10};
        System.out.println("Input:    nums=[-4,-1,0,3,10]");
        System.out.println("Expected: [0, 1, 9, 16, 100]");
        System.out.println("Actual:   " + Arrays.toString(solution.sortedSquares2(nums1)));
        System.out.println();

        int[] nums2 = {-7, -3, 2, 3, 11};
        System.out.println("Input:    nums=[-7,-3,2,3,11]");
        System.out.println("Expected: [4, 9, 9, 49, 121]");
        System.out.println("Actual:   " + Arrays.toString(solution.sortedSquares2(nums2)));
        System.out.println();

        int[] nums3 = {-5, -3, -2, -1};
        System.out.println("Input:    nums=[-5,-3,-2,-1] (all negative)");
        System.out.println("Expected: [1, 4, 9, 25]");
        System.out.println("Actual:   " + Arrays.toString(solution.sortedSquares2(nums3)));
        System.out.println();

        int[] nums4 = {1, 2, 3, 4};
        System.out.println("Input:    nums=[1,2,3,4] (all positive)");
        System.out.println("Expected: [1, 4, 9, 16]");
        System.out.println("Actual:   " + Arrays.toString(solution.sortedSquares(nums4)));
        System.out.println();

        int[] nums5 = {0};
        System.out.println("Input:    nums=[0] (single element)");
        System.out.println("Expected: [0]");
        System.out.println("Actual:   " + Arrays.toString(solution.sortedSquares(nums5)));
    }
}
