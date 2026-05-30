package linkedlist;

import common.ListNode;

/**
 * LeetCode 2 — Add Two Numbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * <p>
 * Example 1:
 * Input:  l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * <p>
 * Example 2:
 * Input:  l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * Example 3:
 * Input:  l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
public class P0002AddTwoNumbers {

    /**
     * Iterative: O(max(n,m)) time, O(max(n,m)) space.
     * Hint: walk both lists in sync, tracking carry.
     *       Use a dummy head so the first digit isn't a special case.
     *       Stop only when BOTH lists are exhausted AND carry is 0.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1, cur2 = l2, result = new ListNode(-999);
        int carry = 0;

        ListNode cur = result;
        while (cur1 != null || cur2 != null || carry != 0) {
            int v1 = cur1 != null ? cur1.val : 0;
            int v2 = cur2 != null ? cur2.val : 0;
            int res;
            if (carry > 0) {
                res = v1 + v2 + carry;
                carry--;
            } else {
                res = v1 + v2;
            }

            if (res > 9) {
                carry = 1;
                res = res % 10;
            }
            cur.next = new ListNode(res);
            cur = cur.next;
            if (cur1 != null) cur1 = cur1.next;
            if (cur2 != null) cur2 = cur2.next;
        }
        return result.next;
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
        P0002AddTwoNumbers solution = new P0002AddTwoNumbers();

        System.out.println("Input:    l1=[2,4,3], l2=[5,6,4]   (342 + 465 = 807)");
        System.out.println("Expected: [7,0,8]");
        System.out.println("Actual:   " + print(solution.addTwoNumbers(build(2, 4, 3), build(5, 6, 4))));
        System.out.println();

        System.out.println("Input:    l1=[0], l2=[0]");
        System.out.println("Expected: [0]");
        System.out.println("Actual:   " + print(solution.addTwoNumbers(build(0), build(0))));
        System.out.println();

        System.out.println("Input:    l1=[9,9,9,9,9,9,9], l2=[9,9,9,9]   (carry chain)");
        System.out.println("Expected: [8,9,9,9,0,0,0,1]");
        System.out.println("Actual:   " + print(solution.addTwoNumbers(build(9, 9, 9, 9, 9, 9, 9), build(9, 9, 9, 9))));
        System.out.println();

        System.out.println("Input:    l1=[1,8], l2=[0]   (uneven length)");
        System.out.println("Expected: [1,8]");
        System.out.println("Actual:   " + print(solution.addTwoNumbers(build(1, 8), build(0))));
        System.out.println();

        System.out.println("Input:    l1=[5], l2=[5]   (single digit, carry creates new node)");
        System.out.println("Expected: [0,1]");
        System.out.println("Actual:   " + print(solution.addTwoNumbers(build(5), build(5))));
    }
}
