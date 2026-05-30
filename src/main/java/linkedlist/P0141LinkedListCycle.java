package linkedlist;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 141 — Linked List Cycle
 * <p>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle if some node can be reached again by continuously following the next pointer.
 * <p>
 * Return true if there is a cycle, otherwise false.
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked list (-1 means no cycle).
 * <p>
 * Follow up: Can you solve it using O(1) memory?
 * <p>
 * Example 1:
 * Input:  head = [3,2,0,-4], pos = 1  (tail connects back to index 1)
 * Output: true
 * <p>
 * Example 2:
 * Input:  head = [1,2], pos = 0  (tail connects back to index 0)
 * Output: true
 * <p>
 * Example 3:
 * Input:  head = [1], pos = -1  (no cycle)
 * Output: false
 */
public class P0141LinkedListCycle {

    // HashSet approach: O(n) time, O(n) space.
    public boolean hasCycleHashSet(ListNode head) {
        if (head == null) return false;
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (!set.add(cur)) return true;
            cur = cur.next;
        }

        return false;
    }

    // Floyd's slow/fast pointer: O(n) time, O(1) space.
    public boolean hasCycle(ListNode head) {

        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Helper: build a linked list and optionally connect tail to node at cyclePos (-1 = no cycle)
    private static ListNode build(int cyclePos, int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        if (cyclePos == -1) return dummy.next;
        // connect tail to cyclePos node
        ListNode tail = cur;
        ListNode cycleNode = dummy.next;
        for (int i = 0; i < cyclePos; i++) cycleNode = cycleNode.next;
        tail.next = cycleNode;
        return dummy.next;
    }

    public static void main(String[] args) {
        P0141LinkedListCycle solution = new P0141LinkedListCycle();

        System.out.println("Input:    [3,2,0,-4], cycle at pos=1");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.hasCycle(build(1, 3, 2, 0, -4)));
        System.out.println();

        System.out.println("Input:    [1,2], cycle at pos=0");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.hasCycle(build(0, 1, 2)));
        System.out.println();

        System.out.println("Input:    [1], no cycle");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.hasCycle(build(-1, 1)));
        System.out.println();

        System.out.println("Input:    [] (empty)");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.hasCycle(null));
        System.out.println();

        System.out.println("Input:    [1,2,3,4,5], cycle at pos=2");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.hasCycle(build(2, 1, 2, 3, 4, 5)));
    }
}
