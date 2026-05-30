package arrays;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 54 — Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class P0054SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;
        List<Integer> answer = new ArrayList<>();
        int maxIndex = m * n - 1;
        int curIndex = 0;

        int top = 0;
        int bot = n - 1;
        int left = 0;
        int right = m - 1;

        int direction = 0;

        while (curIndex <= maxIndex) {
            switch (direction % 4) {
                case 0: {
                    for (int i = left; i <= right; i++) {
                        answer.add(matrix[top][i]);
                        curIndex++;
                    }
                    top += 1;
                    direction++;
                    break;
                }
                case 1: {
                    for (int i = top; i <= bot; i++) {
                        answer.add(matrix[i][right]);
                        curIndex++;
                    }
                    right -= 1;
                    direction++;
                    break;
                }
                case 2: {
                    for (int i = right; i >= left; i--) {
                        answer.add(matrix[bot][i]);
                        curIndex++;
                    }
                    bot -= 1;
                    direction++;
                    break;
                }
                case 3: {
                    for (int i = bot; i >= top; i--) {
                        answer.add(matrix[i][left]);
                        curIndex++;
                    }
                    left += 1;
                    direction++;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 2, 3}, {4, 5, 6}, { 7, 8, 9, 10}};
        P0054SpiralMatrix sol = new P0054SpiralMatrix();
        System.out.println(sol.spiralOrder(input));
    }
}
