package arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 347 — Top K Frequent Elements
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array]
 * It is guaranteed that the answer is unique.
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 * <p>
 * Example 1:
 * Input:  nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input:  nums = [1], k = 1
 * Output: [1]
 */
public class P0347TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // TODO: implement
        PriorityQueue<Integer> queue = new PriorityQueue<>();



        return new int[0];
    }

    public static void main(String[] args) {
        P0347TopKFrequentElements solution = new P0347TopKFrequentElements();

        System.out.println("Input:    nums=[1,1,1,2,2,3], k=2");
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println();

        System.out.println("Input:    nums=[1], k=1");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + Arrays.toString(solution.topKFrequent(new int[]{1}, 1)));
        System.out.println();

        System.out.println("Input:    nums=[1,2], k=2");
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.topKFrequent(new int[]{1, 2}, 2)));
        System.out.println();

        System.out.println("Input:    nums=[4,1,-1,2,-1,2,3], k=2");
        System.out.println("Expected: [-1, 2]");
        System.out.println("Actual:   " + Arrays.toString(solution.topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
    }
}
