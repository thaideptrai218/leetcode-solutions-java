package arrays;

/**
 * LeetCode 121 — Best Time to Buy and Sell Stock
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock
 * on the i-th day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 * <p>
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 * <p>
 * Example 1:
 * Input:  prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1), sell on day 5 (price = 6), profit = 6-1 = 5.
 * <p>
 * Example 2:
 * Input:  prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: No transaction yields a profit.
 */
public class P0121BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int left = prices[0];
        int right;
        int n = prices.length;
        int maxProfit = 0;

        for (right = 1; right < n; right++) {
            int curPrice = prices[right];
            int curProfit = curPrice - left;
            if (curPrice < left) {
                left = curPrice;
            }
            maxProfit = Math.max(maxProfit, curProfit);
        }


        return maxProfit;
    }

    public static void main(String[] args) {
        P0121BestTimeToBuyAndSellStock solution = new P0121BestTimeToBuyAndSellStock();

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Input:    prices=[7,1,5,3,6,4]");
        System.out.println("Expected: 5");
        System.out.println("Actual:   " + solution.maxProfit(prices1));
        System.out.println();

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Input:    prices=[7,6,4,3,1]");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.maxProfit(prices2));
        System.out.println();

        int[] prices3 = {2, 4, 1};
        System.out.println("Input:    prices=[2,4,1]");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.maxProfit(prices3));
        System.out.println();

        int[] prices4 = {1};
        System.out.println("Input:    prices=[1]");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.maxProfit(prices4));
    }
}
