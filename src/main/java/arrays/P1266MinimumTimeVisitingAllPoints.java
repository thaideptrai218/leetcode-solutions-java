package arrays;

/**
 * LeetCode 1266 — Minimum Time Visiting All Points
 * On a 2D plane there are n points with integer coordinates points[i] = [xi, yi].
 * Return the minimum time in seconds to visit all the points in the order given.
 * <p>
 * Movement rules (1 second each):
 * - Move vertically by 1 unit.
 * - Move horizontally by 1 unit.
 * - Move diagonally by sqrt(2) units (still 1 second — both x and y change by 1).
 * You must visit the points in the same order as they appear in the input.
 * <p>
 * Constraints:
 * points.length == n
 * 1 <= n <= 100
 * points[i].length == 2
 * -1000 <= points[i][0], points[i][1] <= 1000
 */
public class P1266MinimumTimeVisitingAllPoints {

    // Approach: time between two points = Chebyshev distance = max(|dx|, |dy|).
    // Sum it across consecutive pairs.
    public int minTimeToVisitAllPoints(int[][] points) {
        int[] startPoint = points[0];
        int sum = 0;
        for (int i = 1; i < points.length; i++) {
            int[] targetPoint = points[i]; // [3,4]

            while (startPoint[0] != targetPoint[0] || startPoint[1] != targetPoint[1]) {
                int diffX = Math.abs(targetPoint[0] - startPoint[0]);
                int signX = targetPoint[0] - startPoint[0];

                int diffY = Math.abs(targetPoint[1] - startPoint[1]);
                int signY = targetPoint[1] - startPoint[1];


                if (diffX == 0) {
                    startPoint[1] += signY >= 0 ? 1 : -1;
                } else if (diffY == 0) {
                    startPoint[0] += signX >= 0 ? 1 : -1;
                } else {
                    startPoint[0] += signX >= 0 ? 1 : -1;
                    startPoint[1] += signY >= 0 ? 1 : -1;
                }
                sum += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        P1266MinimumTimeVisitingAllPoints solution = new P1266MinimumTimeVisitingAllPoints();

        int[][] points1 = {{1, 1}, {3, 4}, {-1, 0}};
        System.out.println("Input:    [[1,1],[3,4],[-1,0]]");
        System.out.println("Expected: 7");
        System.out.println("Actual:   " + solution.minTimeToVisitAllPoints(points1));
        System.out.println();

        int[][] points2 = {{3, 2}, {-2, 2}};
        System.out.println("Input:    [[3,2],[-2,2]]");
        System.out.println("Expected: 5");
        System.out.println("Actual:   " + solution.minTimeToVisitAllPoints(points2));
        System.out.println();

        int[][] points3 = {{0, 0}};
        System.out.println("Input:    [[0,0]]");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.minTimeToVisitAllPoints(points3));
        System.out.println();

        int[][] points4 = {
                {559, 511}, {932, 618}, {-623, -443}, {431, 91}, {838, -127},
                {773, -917}, {-500, -910}, {830, -417}, {-870, 73}, {-864, -600},
                {450, 535}, {-479, -370}, {856, 573}, {-549, 369}, {529, -462},
                {-839, -856}, {-515, -447}, {652, 197}, {-83, 345}, {-69, 423},
                {310, -737}, {78, -201}, {443, 958}, {-311, 988}, {-477, 30},
                {-376, -153}, {-272, 451}, {322, -125}, {-114, -214}, {495, 33},
                {371, -533}, {-393, -224}, {-405, -633}, {-693, 297}, {504, 210},
                {-427, -231}, {315, 27}, {991, 322}, {811, -746}, {252, 373},
                {-737, -867}, {-137, 130}, {507, 380}, {100, -638}, {-296, 700},
                {341, 671}, {-944, 982}, {937, -440}, {40, -929}, {-334, 60},
                {-722, -92}, {-35, -852}, {25, -495}, {185, 671}, {149, -452}
        };
        System.out.println("Input:    (55-point stress test)");
        System.out.println("Expected: 49088");
        long t0 = System.nanoTime();
        int actual4 = solution.minTimeToVisitAllPoints(points4);
        long elapsedMs = (System.nanoTime() - t0) / 1_000_000;
        System.out.println("Actual:   " + actual4 + "  (took " + elapsedMs + " ms)");
    }
}
