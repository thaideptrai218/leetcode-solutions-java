package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Test helpers for building and inspecting ListNodes.
 * Lets you write tests in the same array form LeetCode shows examples in.
 */
public final class ListNodes {
    private ListNodes() {}

    /** Build list from array. fromArray(new int[]{1,2,3}) → 1 → 2 → 3 → null. */
    public static ListNode fromArray(int[] vals) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /** Walk list into a List<Integer> for easy assertEquals comparisons. */
    public static List<Integer> toList(ListNode head) {
        List<Integer> out = new ArrayList<>();
        while (head != null) {
            out.add(head.val);
            head = head.next;
        }
        return out;
    }
}
