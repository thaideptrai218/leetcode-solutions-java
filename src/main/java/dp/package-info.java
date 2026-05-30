/**
 * Dynamic Programming.
 *
 * <p>Workflow:
 * <ol>
 *   <li>Define state — what does {@code dp[i]} or {@code dp[i][j]} mean? Be precise.</li>
 *   <li>Recurrence — how does state at i depend on smaller states?</li>
 *   <li>Base case — what's {@code dp[0]} or the empty case?</li>
 *   <li>Order — bottom-up loop direction; topological order of state dependencies.</li>
 *   <li>Optimize space — often only last row/column needed → drop a dimension.</li>
 * </ol>
 *
 * <p>Common categories:
 * <ul>
 *   <li>1D linear — climb stairs, house robber, decode ways.</li>
 *   <li>2D grid — unique paths, min path sum, edit distance, LCS.</li>
 *   <li>Knapsack — subset sum, coin change, partition equal sum.</li>
 *   <li>Interval — palindrome partitioning, burst balloons, matrix chain.</li>
 *   <li>State machine — best time to buy/sell stock variants.</li>
 * </ul>
 *
 * <p>Tip: write the recursive + memo version first, convert to bottom-up only if needed.
 *
 * <p>Classic problems: 5, 53, 62, 70, 72, 91, 121, 139, 198, 213, 300, 322, 416, 518, 1143.
 */
package dp;
