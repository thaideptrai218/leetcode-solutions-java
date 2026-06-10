package arrays;

import java.util.Arrays;

/**
 * LeetCode 122 — Best Time to Buy and Sell Stock II
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the i-th day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at
 * most ONE share of the stock at any time. However, you can buy it then
 * immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 * <p>
 * Constraints:
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 * <p>
 * Example 1:
 * Input:  prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 3.
 *              Total profit = 4 + 3 = 7.
 * <p>
 * Example 2:
 * Input:  prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 and sell on day 5, profit = 4.
 * <p>
 * Example 3:
 * Input:  prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy.
 */
public class P0122BestTimeToBuyAndSellStockII {

    /**
     * Return the maximum profit from any number of buy/sell transactions.
     * <p>
     * KEY INSIGHT: unlimited transactions means you don't need to find the single
     * best buy/sell pair. Any upward run from low to high can be captured as the
     * sum of its daily steps: buying low and selling high across a rising stretch
     * gives the SAME total as grabbing every consecutive gain along the way.
     * <p>
     * GREEDY one-pass: sweep the array and whenever today's price is higher than
     * yesterday's, add the difference (prices[i] - prices[i-1]) to your profit.
     * Negative steps you simply skip. O(n) time, O(1) space.
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        int buy = 0;

        for (int sell = 1; sell < prices.length; sell++) {
            if (prices[sell] > prices[buy]) {
                max += prices[sell] - prices[buy];
            }
            buy = sell;
        }
        return max;
    }

    public static void main(String[] args) {
        P0122BestTimeToBuyAndSellStockII solution = new P0122BestTimeToBuyAndSellStockII();

        test(solution, new int[]{7, 1, 5, 3, 6, 4}, 7);
        test(solution, new int[]{1, 2, 3, 4, 5}, 4);
        test(solution, new int[]{7, 6, 4, 3, 1}, 0);   // strictly decreasing → no trade
        test(solution, new int[]{1}, 0);               // single day
        test(solution, new int[]{3, 3, 3}, 0);          // flat → no profit
        test(solution, new int[]{1, 5}, 4);             // one rise
        test(solution, new int[]{2, 1, 2, 1, 2}, 2);    // zig-zag, two gains of 1
    }

    private static void test(P0122BestTimeToBuyAndSellStockII solution, int[] prices, int expected) {
        int[] original = Arrays.copyOf(prices, prices.length);

        int actual = solution.maxProfit(prices);

        boolean pass = (actual == expected);
        System.out.println("Input:    " + Arrays.toString(original));
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println();
    }
}
