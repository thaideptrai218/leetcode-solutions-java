package common;

/**
 * Singly-linked list node — matches LeetCode's standard signature.
 * Used by problems like 2 (Add Two Numbers), 21 (Merge Two Lists), 206 (Reverse), etc.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
