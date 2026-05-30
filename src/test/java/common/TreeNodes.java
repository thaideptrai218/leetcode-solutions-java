package common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Test helpers for binary trees.
 * Uses LeetCode's level-order-with-nulls format: [3, 9, 20, null, null, 15, 7]
 * means root=3, left=9, right=20, with 20.left=15 and 20.right=7.
 */
public final class TreeNodes {
    private TreeNodes() {}

    /** Build tree from LeetCode-style level-order Integer[] (use null for missing children). */
    public static TreeNode fromLevelOrder(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode parent = queue.poll();
            if (i < vals.length && vals[i] != null) {
                parent.left = new TreeNode(vals[i]);
                queue.add(parent.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                parent.right = new TreeNode(vals[i]);
                queue.add(parent.right);
            }
            i++;
        }
        return root;
    }

    /** Serialize back to level-order with nulls — useful for asserting tree equality. */
    public static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) return out;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add(null);
                continue;
            }
            out.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        // Trim trailing nulls — LeetCode's representation does the same.
        while (!out.isEmpty() && out.get(out.size() - 1) == null) {
            out.remove(out.size() - 1);
        }
        return out;
    }
}
