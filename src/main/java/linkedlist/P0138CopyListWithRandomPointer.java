package linkedlist;

import common.ListNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * LeetCode 138 — Copy List with Random Pointer
 * <p>
 * A linked list of length n is given such that each node contains an additional
 * random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n
 * brand new nodes, where each new node has its value set to the value of its
 * corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the
 * original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * Constraints:
 * 0 <= n <= 1000
 * -10^4 <= Node.val <= 10^4
 * Node.random is null or pointing to some node in the linked list.
 * <p>
 * Example 1:
 * Input:  head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * Input:  head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 */
public class P0138CopyListWithRandomPointer {

    /** Node with both next and random pointers. */
    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) { this.val = val; }
    }

    /**
     * HashMap approach: O(n) time, O(n) space.
     * Hint: two passes. First pass creates all new nodes and maps original → copy.
     *       Second pass wires next and random pointers using the map.
     */
    public Node copyRandomListHashMap(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {
            Node c = map.get(cur);
            c.next = map.get(cur.next);
            c.random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }

    /**
     * Interleaving approach: O(n) time, O(1) extra space.
     * Hint: three passes —
     *   Pass 1: insert copy node right after each original (A → A' → B → B' → ...)
     *   Pass 2: set random for each copy (copy.random = original.random.next)
     *   Pass 3: detach the copy list from the original
     */
    public Node copyRandomList(Node head) {
        Node cur = head;

        while (cur != null) {
            Node copy = new Node(cur.val);
            Node next = cur.next;
            cur.next = copy;
            copy.next = next;
            cur = next;
        }

        var original = head;
        var copy = original.next;
        while (original != null) {
            copy.random = Objects.requireNonNullElse(original.random.next, null);
            original = original.next.next;
            copy = Objects.requireNonNullElse(copy.next.next, null);
        }
//
//
//        original = head;
//        copy = original.next;
//        Node dummy = new Node(-333);
//        dummy.next = copy;
//        while (copy != null) {
//            original = copy.next;
//            copy.next = original.next;
//            copy = copy.next;
//        }

        return head;
    }

    // Helper: build list from [val, randomIndex] pairs. randomIndex of -1 means null.
    private static Node build(int[][] data) {
        if (data.length == 0) return null;
        Node[] nodes = new Node[data.length];
        for (int i = 0; i < data.length; i++) nodes[i] = new Node(data[i][0]);
        for (int i = 0; i < data.length; i++) {
            if (i + 1 < data.length) nodes[i].next = nodes[i + 1];
            int rIdx = data[i][1];
            nodes[i].random = (rIdx == -1) ? null : nodes[rIdx];
        }
        return nodes[0];
    }

    // Helper: print list as [[val, randomIndex], ...]
    private static String print(Node head) {
        if (head == null) return "[]";
        Map<Node, Integer> index = new HashMap<>();
        Node cur = head;
        int i = 0;
        while (cur != null) { index.put(cur, i++); cur = cur.next; }

        StringBuilder sb = new StringBuilder("[");
        cur = head;
        while (cur != null) {
            sb.append("[").append(cur.val).append(",");
            sb.append(cur.random == null ? "null" : index.get(cur.random));
            sb.append("]");
            if (cur.next != null) sb.append(",");
            cur = cur.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        P0138CopyListWithRandomPointer solution = new P0138CopyListWithRandomPointer();

        // [7,null],[13,0],[11,4],[10,2],[1,0]
        Node l1 = build(new int[][]{{7, -1}, {13, 0}, {11, 4}, {10, 2}, {1, 0}});
        System.out.println("Input:    [[7,null],[13,0],[11,4],[10,2],[1,0]]");
        System.out.println("Expected: [[7,null],[13,0],[11,4],[10,2],[1,0]]");
        System.out.println("Actual:   " + print(solution.copyRandomList(l1)));
        System.out.println();

        // [1,1],[2,1]
        Node l2 = build(new int[][]{{1, 1}, {2, 1}});
        System.out.println("Input:    [[1,1],[2,1]]");
        System.out.println("Expected: [[1,1],[2,1]]");
        System.out.println("Actual:   " + print(solution.copyRandomList(l2)));
        System.out.println();

        // [3,null],[3,0],[3,null]
        Node l3 = build(new int[][]{{3, -1}, {3, 0}, {3, -1}});
        System.out.println("Input:    [[3,null],[3,0],[3,null]]");
        System.out.println("Expected: [[3,null],[3,0],[3,null]]");
        System.out.println("Actual:   " + print(solution.copyRandomList(l3)));
        System.out.println();

        // Empty
        System.out.println("Input:    [] (empty)");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + print(solution.copyRandomList(null)));
    }
}
