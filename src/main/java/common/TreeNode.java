package common;

/**
 * Binary tree node — matches LeetCode's standard signature.
 * Used by problems like 100 (Same Tree), 104 (Max Depth), 226 (Invert Tree), etc.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
