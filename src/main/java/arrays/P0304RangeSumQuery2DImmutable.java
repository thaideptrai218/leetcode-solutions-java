package arrays;

/**
 * LeetCode 304 — Range Sum Query 2D (Immutable)
 * <p>
 * Given a 2D matrix, answer many queries of the form: "sum of all elements inside
 * the rectangle from upper-left (row1, col1) to lower-right (row2, col2)."
 * <p>
 * Implement NumMatrix:
 * - NumMatrix(int[][] matrix)                          initializes with the matrix
 * - int sumRegion(int row1, int col1, int row2, int col2)   returns the rectangle sum
 * <p>
 * sumRegion MUST run in O(1) time.
 * <p>
 * Constraints:
 * 1 <= m, n <= 200
 * -10^4 <= matrix[i][j] <= 10^4
 * 0 <= row1 <= row2 < m,  0 <= col1 <= col2 < n
 * At most 10^4 calls to sumRegion.
 * <p>
 * Example matrix:
 * [3, 0, 1, 4, 2]
 * [5, 6, 3, 2, 1]
 * [1, 2, 0, 1, 5]
 * [4, 1, 0, 1, 7]
 * [1, 0, 3, 0, 5]
 * sumRegion(2,1,4,3) = 8,  sumRegion(1,1,2,2) = 11,  sumRegion(1,2,2,4) = 12
 */
public class P0304RangeSumQuery2DImmutable {
    static class NumMatrix {
        int[][] matrix;
        int[][] prefix;

        /**
         * Hint (kept light on purpose):
         * <p>
         * The O(1)-per-query rule + up to 10^4 queries means you canNOT add up the
         * rectangle on each call — that's O(m*n) per query. All the heavy lifting has
         * to happen ONCE, here in the constructor. Precompute something now so each
         * sumRegion is just a little arithmetic.
         * <p>
         * Think back to the 1D prefix sum (problems 238 / 303): you stored "sum of
         * everything up to index i", then a range was one subtraction. The 2D version
         * stores, for each cell, the sum of the whole rectangle from the origin (0,0)
         * to that cell. Build that table here.
         * <p>
         * (Tip: padding the table with an extra zero row and zero column on the top/
         * left makes the index math at the edges much cleaner — optional, but it
         * spares you from special-casing row1==0 / col1==0.)
         */
        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            prefix = new int[matrix.length][matrix[0].length];

            for (int row = 0; row < matrix.length; row++) {
                prefix[row][0] = matrix[row][0];
                for (int col = 1; col < matrix[0].length; col++) {
                    prefix[row][col] = prefix[row][col - 1] + matrix[row][col];
                }
            }

            for (int col = 0; col < matrix[0].length; col++) {
                for (int row = 1; row < matrix.length; row++) {
                    prefix[row][col] += prefix[row - 1][col];
                }
            }
        }

        /**
         * Hint: once you have each cell's "sum from the origin to here", your query
         * rectangle can be assembled from a few of those corner values — take the big
         * from-origin rectangle and remove the parts you don't want. Watch for one
         * region that ends up removed TWICE; you'll need to put it back. That's the
         * whole trick. Derive the exact corners yourself.
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = prefix[row2][col2];
            int top = row1 - 1 < 0 ? 0 : prefix[row1 - 1][col2];
            int left = col1 - 1 < 0 ? 0 : prefix[row2][col1 - 1];
            int corner = row1 - 1 < 0 || col1 - 1 < 0 ? 0 : prefix[row1 - 1][col1 - 1];
            return sum - top - left + corner;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix nm = new NumMatrix(matrix);

        check("sumRegion(2,1,4,3)", nm.sumRegion(2, 1, 4, 3), 8);
        check("sumRegion(1,1,2,2)", nm.sumRegion(1, 1, 2, 2), 11);
        check("sumRegion(1,2,2,4)", nm.sumRegion(1, 2, 2, 4), 12);
        check("single cell (0,0,0,0)", nm.sumRegion(0, 0, 0, 0), 3);
        check("single cell (3,4,3,4)", nm.sumRegion(3, 4, 3, 4), 7);
        check("full matrix (0,0,4,4)", nm.sumRegion(0, 0, 4, 4), 58);
        check("top row (0,0,0,4)", nm.sumRegion(0, 0, 0, 4), 10);
        check("left col (0,0,4,0)", nm.sumRegion(0, 0, 4, 0), 14);

        // Second matrix with negatives + a 1x1 edge case.
        int[][] neg = {{-1, 2}, {3, -4}};
        NumMatrix nm2 = new NumMatrix(neg);
        check("neg full (0,0,1,1)", nm2.sumRegion(0, 0, 1, 1), 0);
        check("neg top row (0,0,0,1)", nm2.sumRegion(0, 0, 0, 1), 1);
        check("neg bottom-right (1,1,1,1)", nm2.sumRegion(1, 1, 1, 1), -4);

        int[][] one = {{42}};
        NumMatrix nm3 = new NumMatrix(one);
        check("1x1 matrix (0,0,0,0)", nm3.sumRegion(0, 0, 0, 0), 42);
    }

    private static void check(String label, int actual, int expected) {
        boolean pass = actual == expected;
        System.out.printf("%-28s expected=%-5d actual=%-5d %s%n",
                label, expected, actual, pass ? "PASS" : "FAIL");
    }
}
