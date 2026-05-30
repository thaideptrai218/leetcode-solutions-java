package arrays;

import java.util.Arrays;

public class P0200NumberOfIslands {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        boolean visited[][] = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(i, j, visited, grid);
                }
            }
        }
        dfs(0, 0, visited, grid);

        return count;
    }

    private void dfs(int row, int col, boolean[][] visited, char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0' || visited[row][col]) return;
        if (grid[row][col] == '1') visited[row][col] = true;

        dfs(row, col - 1, visited, grid);
        dfs(row, col + 1, visited, grid);
        dfs(row - 1, col, visited, grid);
        dfs(row + 1, col, visited, grid);
    }

    public static void main(String[] args) {
        P0200NumberOfIslands solution = new P0200NumberOfIslands();

        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Input:    grid with single connected island");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.numIslands(grid1));
        System.out.println();

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Input:    grid with three islands");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.numIslands(grid2));
        System.out.println();

        char[][] grid3 = {
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        System.out.println("Input:    grid with all water");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.numIslands(grid3));
        System.out.println();

        char[][] grid4 = {{'1'}};
        System.out.println("Input:    single-cell land grid");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.numIslands(grid4));
    }

}
