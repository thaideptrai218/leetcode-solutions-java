package arrays;

import java.util.Arrays;

/**
 * LeetCode 268 — Missing Number
 * Given an array nums containing n distinct numbers in [0, n],
 * return the only number in the range that is missing.
 */
public class P0268MissingNumber {

    // Brute force: scan full range, check membership. O(n^2) time, O(n) space.
    public int bruteForce(int[] nums) {
        int n = nums.length;
        int[] fullArray = new int[n + 1];
        for (int i = 0; i <= n; i++) fullArray[i] = i;

        for (int compare : fullArray) {
            boolean found = false;
            for (int num : nums) {
                if (compare == num) { found = true; break; }
            }
            if (!found) return compare;
        }
        return -1;
    }

    // Sort then index-compare. O(n log n) time, O(n) space.
    public int sorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }

    // Marker array. O(n) time, O(n) space.
    public int markerArray(int[] nums) {
        int n = nums.length;
        int[] compare = new int[n + 1];
        Arrays.fill(compare, -1);
        for (int num : nums) compare[num] = num;
        for (int i = 0; i < compare.length; i++) {
            if (compare[i] == -1) return i;
        }
        return -1;
    }

    // XOR trick: a^a = 0, so XOR of [0..n] with all nums leaves the missing one. O(n) time, O(1) space.
    public int xor(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }
}
