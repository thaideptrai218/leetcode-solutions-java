/**
 * Backtracking.
 *
 * <p>Template:
 * <pre>
 * void backtrack(state, choices, result) {
 *     if (isComplete(state)) { result.add(copyOf(state)); return; }
 *     for (choice : choices) {
 *         if (!isValid(choice, state)) continue;
 *         apply(choice, state);
 *         backtrack(state, nextChoices, result);
 *         undo(choice, state);   // <-- the "back" in backtracking
 *     }
 * }
 * </pre>
 *
 * <p>Key patterns:
 * <ul>
 *   <li>Permutations — track used[] or remove/restore from list.</li>
 *   <li>Combinations / subsets — pass a start index to avoid duplicates.</li>
 *   <li>Constraint problems — N-Queens, Sudoku; prune aggressively before recursing.</li>
 *   <li>Always copy before adding to result — same list reference will mutate.</li>
 * </ul>
 *
 * <p>Classic problems: 17, 22, 37, 39, 40, 46, 47, 51, 78, 79, 90, 131, 216, 491.
 */
package backtracking;
