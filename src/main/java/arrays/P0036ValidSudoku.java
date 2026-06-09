package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 36 — Valid Sudoku
 * <p>
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.
 * <p>
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 * - Empty cells are denoted by the character '.'.
 * <p>
 * Constraints:
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 * <p>
 * Example 1:
 * Input: a valid board → Output: true
 * Example 2:
 * Input: same board but with two 8s in the top-left box/column → Output: false
 */
public class P0036ValidSudoku {

    /**
     * Return true if the board breaks none of the three rules.
     * <p>
     * KEY INSIGHT: you only need to detect a DUPLICATE within any row, column, or
     * box. The natural tool for "have I seen this before?" is a Set.
     * <p>
     * ONE-PASS approach (the clean one): sweep every cell once. For each filled
     * cell, build three string keys that are unique to the constraint it lives in:
     * - row key:  "r" + i + value
     * - col key:  "c" + j + value
     * - box key:  "b" + (i/3) + (j/3) + value
     * The box index trick is the part worth pausing on: integer division i/3 and
     * j/3 maps any cell to its 3 x 3 box (0..2, 0..2). Drop all three keys into a
     * single Set as you go — if any key is already present, you found a duplicate,
     * so return false. Skip '.' cells entirely. O(81) time, O(81) space.
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int m = 0; m < board.length; m += 3) {
            for (int n = 0; n < board.length; n += 3) {
                for (int i = m; i < m + 3; i++) {
                    for (int j = n; j < n + 3; j++) {
                        char value = board[i][j];
                        if (value == '.') continue;
                        String row = "r" + i + value;
                        String col = "c" + j + value;
                        String box = "b" + (i / 3) + (j / 3) + value;

                        if (seen.contains(row) || seen.contains(col) || seen.contains(box)) {
                            return false;
                        }

                        seen.add(row);
                        seen.add(col);
                        seen.add(box);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        P0036ValidSudoku solution = new P0036ValidSudoku();

        char[][] valid = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        test(solution, valid, true);

        // Same board but the top-left 5 becomes an 8 → two 8s in column 0 and the
        // top-left box.
        char[][] invalid = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        test(solution, invalid, false);

        // Empty board → vacuously valid.
        char[][] empty = new char[9][9];
        for (char[] row : empty) java.util.Arrays.fill(row, '.');
        test(solution, empty, true);

        // Duplicate within a single row.
        char[][] rowDup = new char[9][9];
        for (char[] row : rowDup) java.util.Arrays.fill(row, '.');
        rowDup[0][0] = '1';
        rowDup[0][5] = '1';
        test(solution, rowDup, false);
    }

    private static void test(P0036ValidSudoku solution, char[][] board, boolean expected) {
        boolean actual = solution.isValidSudoku(board);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println(actual == expected ? "PASS" : "FAIL");
        System.out.println();
    }
}
