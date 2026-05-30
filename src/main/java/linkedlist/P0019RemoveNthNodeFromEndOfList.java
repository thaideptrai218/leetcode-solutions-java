package linkedlist;

import common.ListNode;

/**
 * LeetCode 19 — Remove Nth Node From End of List
 * <p>
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 * <p>
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * Follow up: Could you do this in one pass?
 * <p>
 * Example 1:
 * Input:  head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * <p>
 * Example 2:
 * Input:  head = [1], n = 1
 * Output: []
 * <p>
 * Example 3:
 * Input:  head = [1,2], n = 1
 * Output: [1]
 */
public class P0019RemoveNthNodeFromEndOfList {

    // Two-pass: find length first, then remove. O(n) time, O(1) space.
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        if (head == null) return null;

        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }

        if (n == count) {
            return head.next;
        }

        cur = head;
        int taget = count - n - 1;
        count = 0;
        while (cur != null) {
            if (taget == count) {
                cur.next = cur.next.next;
                return head;
            }
            cur = cur.next;
            count++;
        }
        return head;
    }

    // One-pass: two pointers with n-gap. O(n) time, O(1) space.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;

        int count = 0;
        while (fast != null) {
            fast = fast.next;
            if (count > n) {
                slow = slow.next;
            }
            count++;
        }
        slow.next = slow.next.next;
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
        P0019RemoveNthNodeFromEndOfList solution = new P0019RemoveNthNodeFromEndOfList();

        System.out.println("Input:    [1,2,3,4,5], n=2");
        System.out.println("Expected: [1,2,3,5]");
        System.out.println("Actual:   " + print(solution.removeNthFromEnd(build(1, 2, 3, 4, 5), 2)));
        System.out.println();

        System.out.println("Input:    [1], n=1 (remove only node)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.removeNthFromEnd(build(1), 1)));
        System.out.println();

        System.out.println("Input:    [1,2], n=1 (remove tail)");
        System.out.println("Expected: [1]");
        System.out.println("Actual:   " + print(solution.removeNthFromEnd(build(1, 2), 1)));
        System.out.println();

        System.out.println("Input:    [1,2], n=2 (remove head)");
        System.out.println("Expected: [2]");
        System.out.println("Actual:   " + print(solution.removeNthFromEnd(build(1, 2), 2)));
        System.out.println();

        System.out.println("Input:    [1,2,3,4,5], n=5 (remove head)");
        System.out.println("Expected: [2,3,4,5]");
        System.out.println("Actual:   " + print(solution.removeNthFromEnd(build(1, 2, 3, 4, 5), 5)));
    }
}
