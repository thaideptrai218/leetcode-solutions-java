package linkedlist;

import common.ListNode;

/**
 * LeetCode 83 — Remove Duplicates from Sorted List
 * <p>
 * Given the head of a sorted linked list, delete all duplicates such that
 * each element appears only once. Return the linked list sorted as well.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * <p>
 * Example 1:
 * Input:  head = [1,1,2]
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input:  head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
public class P0083RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;

            while (next != null  && next.val == cur.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }

    // Helper: build a linked list from values
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
        P0083RemoveDuplicatesFromSortedList solution = new P0083RemoveDuplicatesFromSortedList();

        System.out.println("Input:    [1,1,2]");
        System.out.println("Expected: [1,2]");
        System.out.println("Actual:   " + print(solution.deleteDuplicates(build(1, 1, 2))));
        System.out.println();

        System.out.println("Input:    [1,1,2,3,3]");
        System.out.println("Expected: [1,2,3]");
        System.out.println("Actual:   " + print(solution.deleteDuplicates(build(1, 1, 2, 3, 3))));
        System.out.println();

        System.out.println("Input:    [1,1,1] (all same)");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + print(solution.deleteDuplicates(build(1, 1, 1))));
        System.out.println();

        System.out.println("Input:    [1,2,3] (no duplicates)");
        System.out.println("Expected: [1,2,3]");
        System.out.println("Actual:   " + print(solution.deleteDuplicates(build(1, 2, 3))));
        System.out.println();

        System.out.println("Input:    [] (empty)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.deleteDuplicates(null)));
    }
}
