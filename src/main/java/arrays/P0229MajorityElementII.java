package arrays;

import java.util.*;

/**
 * LeetCode 229 — Majority Element II
 * <p>
 * Given an integer array of size n, find all elements that appear more than
 * ⌊n / 3⌋ times.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * <p>
 * Example 1:
 * Input:  nums = [3,2,3]
 * Output: [3]
 * <p>
 * Example 2:
 * Input:  nums = [1]
 * Output: [1]
 * <p>
 * Example 3:
 * Input:  nums = [1,2]
 * Output: [1,2]
 */
public class P0229MajorityElementII {

    /**
     * Return every element appearing more than n/3 times.
     * <p>
     * KEY INSIGHT: there can be AT MOST TWO such elements (three values each over
     * n/3 would exceed n total). That cap is what makes the follow-up possible.
     * <p>
     * 1) HashMap counting — O(n) time, O(n) space. Count everything, collect keys
     *    whose count > n/3. Easiest; get this working first.
     * <p>
     * 2) Boyer-Moore Voting, extended to two candidates — O(n) time, O(1) space
     *    (the follow-up). Track two candidates and two counts. For each value:
     *    if it matches a candidate, bump that count; else if a count is 0, adopt
     *    the value as that candidate; else decrement BOTH counts. CRITICAL second
     *    pass: the voting only proposes candidates — it does NOT prove they qualify.
     *    Re-count the two finalists over the array and keep only those that truly
     *    exceed n/3.
     */
    public List<Integer> majorityElement(int[] nums) {
        
    }

    public List<Integer> majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value > n / 3) res.add(entry.getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        P0229MajorityElementII solution = new P0229MajorityElementII();
        test(solution, new int[]{3, 2, 3}, Arrays.asList(3));
        test(solution, new int[]{1}, Arrays.asList(1));
        test(solution, new int[]{1, 2}, Arrays.asList(1, 2));
        test(solution, new int[]{2, 2, 1, 3}, Arrays.asList(2));          // only 2 exceeds 4/3
        test(solution, new int[]{1, 1, 1, 2, 3, 4, 5}, Arrays.asList(1)); // 1 appears 3 > 7/3
        test(solution, new int[]{1, 2, 3, 4}, Arrays.asList());           // none exceeds 4/3
        test(solution, new int[]{0, 0, 0}, Arrays.asList(0));             // all same
    }

    private static void test(P0229MajorityElementII solution, int[] nums, List<Integer> expected) {
        int[] original = Arrays.copyOf(nums, nums.length);

        List<Integer> actual = solution.majorityElement(nums);

        // Order-independent comparison.
        List<Integer> a = new ArrayList<>(actual);
        List<Integer> e = new ArrayList<>(expected);
        a.sort(null);
        e.sort(null);
        boolean pass = a.equals(e);

        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
