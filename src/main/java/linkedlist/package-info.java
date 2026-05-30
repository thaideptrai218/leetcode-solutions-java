/**
 * Linked List.
 *
 * <p>Use {@link common.ListNode} for problem inputs/outputs.
 *
 * <p>Key patterns:
 * <ul>
 *   <li>Dummy head — simplifies edge cases when the head itself may be removed.</li>
 *   <li>Two pointers — fast/slow for cycle detection (Floyd), middle finding, nth-from-end.</li>
 *   <li>Iterative reversal — three pointers: prev, curr, next.</li>
 *   <li>Recursion — reverse, merge; mind stack depth on long lists.</li>
 *   <li>Splice/relink — never lose the next pointer before reassigning.</li>
 * </ul>
 *
 * <p>Classic problems: 2, 19, 21, 23, 25, 92, 138, 141, 142, 206, 234, 328.
 */
package linkedlist;
