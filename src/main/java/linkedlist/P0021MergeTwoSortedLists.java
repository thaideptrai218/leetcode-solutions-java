package linkedlist;

import common.ListNode;

import java.util.List;

/**
 * LeetCode 21 — Merge Two Sorted Lists
 * <p>
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by
 * splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 * <p>
 * Constraints:
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 * <p>
 * Example 1:
 * Input:  list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * <p>
 * Example 2:
 * Input:  list1 = [], list2 = []
 * Output: []
 * <p>
 * Example 3:
 * Input:  list1 = [], list2 = [0]
 * Output: [0]
 */
public class P0021MergeTwoSortedLists {

    // Iterative: O(n+m) time, O(1) space.
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // TODO: implement
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode cur1 = list1;
        ListNode cur2 = list2;

        while (cur1 != null && cur2 != null) {
            if (cur2.val <= cur1.val) {
                cur.next = cur2;
                cur = cur.next;
                cur2 = cur2.next;
            } else {
                cur.next = cur1;
                cur = cur.next;
                cur1 = cur1.next;
            }
        }

        while (cur1 != null) {
            cur.next = cur1;
            cur = cur.next;
            cur1 = cur1.next;
        }

        while (cur2 != null) {

            cur.next = cur2;
            cur = cur.next;
            cur2 = cur2.next;

        }
        return dummy.next;
    }

    // Recursive: O(n+m) time, O(n+m) space (call stack).
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // TODO: implement
        if (list1 == null || list2 == null) {
            return (list1 != null) ? list1 : list2;
        }

        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }

        list1.next = mergeTwoListsRecursive(list1.next, list2);
        return list1;
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
        P0021MergeTwoSortedLists solution = new P0021MergeTwoSortedLists();

        System.out.println("Input:    [1,2,4] + [1,3,4]");
        System.out.println("Expected: [1,1,2,3,4,4]");
        System.out.println("Actual:   " + print(solution.mergeTwoListsRecursive(build(1, 2, 4), build(1, 3, 4))));
        System.out.println();

        System.out.println("Input:    [] + [] (both empty)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.mergeTwoLists(null, null)));
        System.out.println();

        System.out.println("Input:    [] + [0] (one empty)");
        System.out.println("Expected: [0]");
        System.out.println("Actual:   " + print(solution.mergeTwoLists(null, build(0))));
        System.out.println();

        System.out.println("Input:    [1,3,5] + [2,4,6]");
        System.out.println("Expected: [1,2,3,4,5,6]");
        System.out.println("Actual:   " + print(solution.mergeTwoLists(build(1, 3, 5), build(2, 4, 6))));
        System.out.println();

        System.out.println("Input:    [5] + [1,2,3] (one longer)");
        System.out.println("Expected: [1,2,3,5]");
        System.out.println("Actual:   " + print(solution.mergeTwoLists(build(5), build(1, 2, 3))));
    }
}
