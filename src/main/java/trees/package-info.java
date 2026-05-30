/**
 * Binary Trees & BSTs.
 *
 * <p>Use {@link common.TreeNode} for problem inputs/outputs.
 *
 * <p>Key patterns:
 * <ul>
 *   <li>DFS recursion — most tree problems reduce to "answer for left + right + this node".</li>
 *   <li>BFS / level order — Deque, process by level for problems mentioning "depth" or "level".</li>
 *   <li>Inorder traversal of BST — yields sorted sequence; detects validity.</li>
 *   <li>Path tracking — pass accumulator down OR return values up; pick one direction.</li>
 *   <li>Lowest common ancestor — return non-null subtree results, ancestor is where both come back.</li>
 * </ul>
 *
 * <p>Classic problems: 94, 98, 100, 102, 104, 105, 110, 124, 199, 226, 230, 235, 236, 297, 543.
 */
package trees;
