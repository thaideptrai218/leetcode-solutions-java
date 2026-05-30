package linkedlist;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 143 — Reorder List
 * <p>
 * You are given the head of a singly linked list. The list can be represented as:
 * L0 → L1 → ... → Ln-1 → Ln
 * <p>
 * Reorder it to:
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
 * <p>
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [1, 5 * 10^4].
 * 1 <= Node.val <= 1000
 * <p>
 * Example 1:
 * Input:  head = [1,2,3,4]
 * Output: [1,4,2,3]
 * <p>
 * Example 2:
 * Input:  head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
public class P0143ReorderList {

    /**
     * Reorders the list in-place. O(n) time, O(1) space.
     * Hint: this problem combines three techniques you already know —
     * Step 1: find the middle of the list
     * Step 2: reverse the second half
     * Step 3: merge the two halves by interleaving
     */
    public void reorderList(ListNode head) {
        // find the middle node;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse;
        ListNode curr2 = slow.next;
        ListNode prev = null;
        slow.next = null;

        while (curr2 != null) {
            ListNode temp = curr2.next;
            curr2.next = prev;
            prev = curr2;
            curr2 = temp;
        }

        // mix
        ListNode curr = head;
        while (prev != null) {
            ListNode next1 = curr.next;
            ListNode next2 = prev.next;
            curr.next = prev;
            prev.next = next1;
            curr = next1;
            prev = next2;
        }
    }

    // Helper: build a linked list from values
    private static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
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
        P0143ReorderList solution = new P0143ReorderList();

        ListNode l1 = build(1, 2, 3, 4);
        solution.reorderList(l1);
        System.out.println("Input:    [1,2,3,4]");
        System.out.println("Expected: [1,4,2,3]");
        System.out.println("Actual:   " + print(l1));
        System.out.println();

        ListNode l2 = build(1, 2, 3, 4, 5);
        solution.reorderList(l2);
        System.out.println("Input:    [1,2,3,4,5]");
        System.out.println("Expected: [1,5,2,4,3]");
        System.out.println("Actual:   " + print(l2));
        System.out.println();

        ListNode l3 = build(1, 2);
        solution.reorderList(l3);
        System.out.println("Input:    [1,2]");
        System.out.println("Expected: [1,2]");
        System.out.println("Actual:   " + print(l3));
        System.out.println();

        ListNode l4 = build(1);
        solution.reorderList(l4);
        System.out.println("Input:    [1] (single node)");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + print(l4));
    }
}
