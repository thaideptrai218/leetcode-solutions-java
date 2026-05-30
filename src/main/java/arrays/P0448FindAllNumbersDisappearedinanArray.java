package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Leetcode 448
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
* */
public class P0448FindAllNumbersDisappearedinanArray {
    // TLE case
    public List<Integer> bruteforce(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int n = nums.length;
        int[] fill = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fill[i] = i;
        }

        for (int i = 1; i < fill.length; i++) {
            boolean flag = false;
            for (var num : nums) {
                if (num == fill[i]) {
                    flag = true;
                    break;
                }
            }

            if (!flag) answer.add(fill[i]);

        }

        return answer;
    }

    public List<Integer> hashmap(int[] nums) {
        for(int i = 0 ; i < nums.length ; i++){
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] = -nums[index];
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P0448FindAllNumbersDisappearedinanArray solution = new P0448FindAllNumbersDisappearedinanArray();

        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Input:    [4,3,2,7,8,2,3,1]");
        System.out.println("Expected: [5, 6]");
        System.out.println("Actual:   " + solution.hashmap(nums1));
        System.out.println();

        int[] nums2 = {1, 1};
        System.out.println("Input:    [1,1]");
        System.out.println("Expected: [2]");
        System.out.println("Actual:   " + solution.hashmap(nums2));
        System.out.println();

        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Input:    [1,2,3,4,5]");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + solution.hashmap(nums3));
    }
}
