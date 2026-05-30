/**
 * Graphs.
 *
 * <p>Representation: adjacency list ({@code List<List<Integer>>} or {@code Map<T, List<T>>}) — almost always.
 *
 * <p>Key patterns:
 * <ul>
 *   <li>BFS — shortest path in unweighted graphs; level-by-level exploration.</li>
 *   <li>DFS — connectivity, cycle detection, topological sort.</li>
 *   <li>Union-Find (DSU) — connectivity queries, Kruskal's MST, redundant edge.</li>
 *   <li>Topological sort — Kahn's (BFS in-degrees) or DFS post-order; requires DAG.</li>
 *   <li>Dijkstra — shortest path with non-negative weights, PriorityQueue.</li>
 *   <li>Visited set — every traversal needs one; on grids use boolean[][] or mutate input.</li>
 * </ul>
 *
 * <p>Classic problems: 200, 207, 210, 261, 269, 547, 684, 743, 787, 994, 1091.
 */
package graphs;
