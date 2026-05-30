package linkedlist;

import common.ListNode;

/**
 * LeetCode 206 — Reverse Linked List
 * <p>
 * Given the head of a singly linked list, reverse the list and return the reversed list.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively.
 * Could you implement both?
 * <p>
 * Example 1:
 * Input:  head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * Input:  head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * Input:  head = []
 * Output: []
 */
public class P0206ReverseLinkedList {

    // Iterative: O(n) time, O(1) space.
    public ListNode reverseIterative(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    // Recursive: O(n) time, O(n) space (call stack).
    public ListNode reverseRecursive(ListNode head) {
        // TODO: implement

        return null;
    }

    // Helper: build a linked list from an array
    private static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    // Helper: print linked list as [1,2,3,...]
    private static String print(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        P0206ReverseLinkedList solution = new P0206ReverseLinkedList();

        System.out.println("Input:    [1,2,3,4,5]");
        System.out.println("Expected: [5,4,3,2,1]");
        System.out.println("Actual:   " + print(solution.reverseIterative(build(1, 2, 3, 4, 5))));
        System.out.println();

        System.out.println("Input:    [1,2]");
        System.out.println("Expected: [2,1]");
        System.out.println("Actual:   " + print(solution.reverseIterative(build(1, 2))));
        System.out.println();

        System.out.println("Input:    [] (empty)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.reverseIterative(null)));
        System.out.println();

        System.out.println("Input:    [1] (single)");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + print(solution.reverseIterative(build(1))));
    }
}
