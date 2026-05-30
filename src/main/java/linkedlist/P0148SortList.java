package linkedlist;

import common.ListNode;

/**
 * LeetCode 148 — Sort List
 * <p>
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 * <p>
 * Follow up: Can you sort the linked list in O(n log n) time and O(1) space?
 * <p>
 * Example 1:
 * Input:  head = [4,2,1,3]
 * Output: [1,2,3,4]
 * <p>
 * Example 2:
 * Input:  head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * <p>
 * Example 3:
 * Input:  head = []
 * Output: []
 */
public class P0148SortList {

    /**
     * Top-down merge sort. O(n log n) time, O(log n) space (call stack).
     * Hint: this combines two techniques —
     *   Step 1: find the middle, split into two halves (slow/fast pointer)
     *   Step 2: recursively sort each half
     *   Step 3: merge the two sorted halves (same as problem 21)
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) return head;

        ListNode dummy = new ListNode(-9999);
        ListNode left = head;
        ListNode mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        ListNode right = mid.next;
        mid.next = null;

        ListNode node1 = mergeSort(left); // -1 -> 0 -> 3
        ListNode node2 = mergeSort(right); // 4 5
        return mergeTwoLists(node1, node2);
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-9999);
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
        P0148SortList solution = new P0148SortList();

        System.out.println("Input:    [4,2,1,3]");
        System.out.println("Expected: [1,2,3,4]");
        System.out.println("Actual:   " + print(solution.sortList(build(4, 2, 1, 3))));
        System.out.println();

        System.out.println("Input:    [-1,5,3,4,0]");
        System.out.println("Expected: [-1,0,3,4,5]");
        System.out.println("Actual:   " + print(solution.sortList(build(-1, 5, 3, 4, 0))));
        System.out.println();

        System.out.println("Input:    [] (empty)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.sortList(null)));
        System.out.println();

        System.out.println("Input:    [1] (single)");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + print(solution.sortList(build(1))));
        System.out.println();

        System.out.println("Input:    [2,1] (two nodes)");
        System.out.println("Expected: [1,2]");
        System.out.println("Actual:   " + print(solution.sortList(build(2, 1))));
        System.out.println();

        System.out.println("Input:    [3,3,3,1,1] (duplicates)");
        System.out.println("Expected: [1,1,3,3,3]");
        System.out.println("Actual:   " + print(solution.sortList(build(3, 3, 3, 1, 1))));
    }
}
