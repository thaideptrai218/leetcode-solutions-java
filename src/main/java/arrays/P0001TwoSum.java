package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 — Two Sum
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input has exactly one solution,
 * and you may not use the same element twice.
 */
public class P0001TwoSum {

    // Brute force: try every pair. O(n^2) time, O(1) space.
    public int[] bruteforce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // Two-pass hashmap: store all values then look up complements. O(n) time, O(n) space.
    public int[] twoPassHashmap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{-1, -1};
    }

    // One-pass hashmap: check complement before inserting. O(n) time, O(n) space.
    public int[] onePassHashmap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public int[] mySolution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return new int[]{map.get(nums[i]), i};
            map.put(target - nums[i], i);
        }

        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        P0001TwoSum solution = new P0001TwoSum();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Input:    nums=[2,7,11,15], target=9");
        System.out.println("Expected: [0, 1]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.onePassHashmap(nums1, target1)));
        System.out.println();

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Input:    nums=[3,2,4], target=6");
        System.out.println("Expected: [1, 2]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.onePassHashmap(nums2, target2)));
        System.out.println();

        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Input:    nums=[3,3], target=6");
        System.out.println("Expected: [0, 1]");
        System.out.println("Actual:   " + java.util.Arrays.toString(solution.onePassHashmap(nums3, target3)));
    }
}
