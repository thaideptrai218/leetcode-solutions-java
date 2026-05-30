/**
 * Heap / Priority Queue.
 *
 * <p>Java: {@link java.util.PriorityQueue} is a min-heap by default.
 * For max-heap: {@code new PriorityQueue<>(Comparator.reverseOrder())}.
 *
 * <p>Key patterns:
 * <ul>
 *   <li>Top-K — maintain a heap of size K; min-heap for top K largest, max-heap for K smallest.</li>
 *   <li>K-way merge — push first element of each list, pop & push next from same list.</li>
 *   <li>Two heaps — running median: max-heap for lower half, min-heap for upper half.</li>
 *   <li>Custom comparator — sort by frequency, distance, custom key.</li>
 *   <li>Lazy deletion — heap doesn't support remove(O(1)); mark as stale and skip on poll.</li>
 * </ul>
 *
 * <p>Classic problems: 23, 215, 253, 295, 347, 373, 378, 502, 621, 692, 703, 973, 1046.
 */
package heap;
