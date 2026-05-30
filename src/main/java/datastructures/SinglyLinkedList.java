package datastructures;

import arrays.P0054SpiralMatrix;

import java.util.NoSuchElementException;

/**
 * Singly Linked List — custom implementation for learning purposes.
 * <p>
 * A linked list stores elements in nodes scattered across the heap.
 * Each node holds a value and a pointer to the next node.
 * Unlike a dynamic array, there is no contiguous memory — no indexing by position.
 * <p>
 * Key internals to understand:
 * - Node structure: value + next pointer
 * - head pointer: entry point to the list
 * - tail pointer: optional, makes addLast() O(1) instead of O(n)
 * - traversal: only forward (no going back)
 * - size tracked manually (no .length like arrays)
 * <p>
 * Operations to implement:
 * - addFirst(value)       → O(1)
 * - addLast(value)        → O(1) with tail pointer
 * - removeFirst()         → O(1)
 * - removeLast()          → O(n) — why can't this be O(1)?
 * - get(index)            → O(n)
 * - contains(value)       → O(n)
 * - size()                → O(1)
 * - isEmpty()             → O(1)
 * <p>
 * Think about:
 * - What happens when the list is empty?
 * - What happens when there is only one node?
 * - Why is removeLast() O(n) even with a tail pointer?
 */
public class SinglyLinkedList<T> {

    /**
     * Internal node — holds value and pointer to next node.
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        size = 0;
        head = tail = null;
    }

    /**
     * Returns number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if list has no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts value at the front. O(1).
     */
    public void addFirst(T value) {
        // Hint: create a new Node. Point its next to current head. Update head.
        //       Special case: if list was empty, tail must also point to new node.
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * Appends value at the end. O(1) with tail pointer.
     */
    public void addLast(T value) {
        // Hint: create a new Node. Point current tail.next to it. Update tail.
        //       Special case: if list was empty, head must also point to new node.
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            tail = head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Removes and returns the first element. O(1).
     */
    public T removeFirst() {
        // Hint: save head.value. Move head to head.next. Decrement size.
        //       Special case: if list becomes empty after removal, update tail too.
        //       Throw NoSuchElementException if empty.
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<T> next = head.next;
        T value = head.value;
        head = next;
        size--;

        if (isEmpty()) {
            tail = head;
        }
        return value;
    }

    /**
     * Removes and returns the last element. O(n).
     */
    public T removeLast() {
        // TODO: implement
        // Hint: traverse to the node BEFORE tail (second-to-last).
        //       Point its next to null. Update tail. Decrement size.
        //       Special case: only one node — removeFirst() handles it.
        //       Throw NoSuchElementException if empty.
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            return removeFirst();
        }

        Node<T> cur = head;
        T value;
        while (cur.next != null && cur.next != tail) {
            cur = cur.next;
        }

        value = tail.value;
        cur.next = null;
        tail = cur;
        size--;

        return value;
    }

    /**
     * Returns element at index without removing. O(n).
     */
    public T get(int index) {
        // Hint: validate index, then walk from head using a loop counter.
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

        var cur = head;
        int i = 0;
        while (i != index) {
            cur = cur.next;
            i++;
        }
        return cur.value;
    }

    /**
     * Returns true if value is present in the list. O(n).
     */
    public boolean contains(T value) {
        // Hint: traverse from head. Use equals() for comparison. Handle null safely.
        var cur = head;
        while (cur != null) {
            if (value == null) {
                if (cur.value == null) return true;
            } else {
                if (value.equals(cur.value)) return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * Inserts value at given index, shifting the existing node at that index forward.
     * Hint: index 0 → addFirst. index == size → addLast.
     *       Otherwise walk to node at (index-1), rewire its next pointer.
     *       Think: you need the node BEFORE the insertion point.
     */
    public void addAt(int index, T value) {
        // TODO: implement
    }

    /**
     * Removes and returns the node at given index.
     * Hint: index 0 → removeFirst. index == size-1 → removeLast.
     *       Otherwise walk to node at (index-1), rewire its next to skip index.
     *       Think: same "trailing pointer" pattern as removeByValue.
     */
    public T removeAt(int index) {
        // TODO: implement
        return null;
    }

    /**
     * Removes the first node whose value equals the given value.
     * Returns true if a node was removed, false if value not found.
     * Hint: you need TWO pointers — prev and cur — walking together.
     *       When cur.value matches: prev.next = cur.next. Update tail if needed.
     *       Special case: match is at head → use removeFirst().
     */
    public boolean removeByValue(T value) {
        // TODO: implement
        return false;
    }

    /**
     * Reverses the list in-place. O(n) time, O(1) space.
     * Hint: use three pointers — prev (starts null), cur (starts head), next.
     *       Each step: save cur.next, point cur.next backward to prev, advance both.
     *       After loop: head becomes old tail, tail becomes old head.
     */
    public void reverse() {
        // TODO: implement
    }

    /**
     * Returns a string like: 1 -> 2 -> 3 -> null
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Test addLast
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println("After addLast 1,2,3:  " + list);         // 1 -> 2 -> 3 -> null
        System.out.println("Size:                 " + list.size());   // 3

        // Test addFirst
        list.addFirst(0);
        System.out.println("After addFirst(0):    " + list);          // 0 -> 1 -> 2 -> 3 -> null

        // Test get
        System.out.println("get(0):               " + list.get(0));   // 0
        System.out.println("get(2):               " + list.get(2));   // 2

        // Test contains
        System.out.println("contains(3):          " + list.contains(3)); // true
        System.out.println("contains(99):         " + list.contains(99));// false

        // Test removeFirst
        System.out.println("removeFirst():        " + list.removeFirst()); // 0
        System.out.println("After removeFirst:    " + list);               // 1 -> 2 -> 3 -> null

        // Test removeLast
        System.out.println("removeLast():         " + list.removeLast());  // 3
        System.out.println("After removeLast:     " + list);               // 1 -> 2 -> null

        // Test single element edge case
        SinglyLinkedList<String> single = new SinglyLinkedList<>();
        single.addLast("only");
        System.out.println("Single removeLast:    " + single.removeLast()); // only
        System.out.println("isEmpty after:        " + single.isEmpty());    // true

        // Test isEmpty
        System.out.println("isEmpty (new):        " + new SinglyLinkedList<>().isEmpty()); // true

        // Test addFirst on empty list
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.addFirst(5);
        System.out.println("addFirst on empty:    " + list2);              // 5 -> null
        System.out.println("size after addFirst:  " + list2.size());       // 1

        // Test removeFirst until empty
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
        list3.addLast(10); list3.addLast(20);
        list3.removeFirst();
        list3.removeFirst();
        System.out.println("isEmpty after 2 removals: " + list3.isEmpty()); // true
        System.out.println("List after 2 removals:    " + list3);           // null

        // Test addLast after full removal
        list3.addLast(99);
        System.out.println("addLast after empty:  " + list3);              // 99 -> null
        System.out.println("size:                 " + list3.size());        // 1

        // Test removeLast until empty
        SinglyLinkedList<Integer> list4 = new SinglyLinkedList<>();
        list4.addLast(1); list4.addLast(2); list4.addLast(3);
        list4.removeLast();
        list4.removeLast();
        list4.removeLast();
        System.out.println("isEmpty after removeLast x3: " + list4.isEmpty()); // true

        // Test addFirst after full removeLast
        list4.addFirst(42);
        System.out.println("addFirst after empty: " + list4);              // 42 -> null
        System.out.println("size:                 " + list4.size());        // 1

        // Test get on boundary indices
        SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>();
        list5.addLast(7); list5.addLast(8); list5.addLast(9);
        System.out.println("get(0) first:         " + list5.get(0));       // 7
        System.out.println("get(2) last:          " + list5.get(2));       // 9

        // Test contains after removals
        SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
        list6.addLast(1); list6.addLast(2); list6.addLast(3);
        list6.removeFirst();
        System.out.println("contains(1) removed:  " + list6.contains(1)); // false
        System.out.println("contains(2) present:  " + list6.contains(2)); // true

        // Test alternating addFirst and addLast
        SinglyLinkedList<Integer> list7 = new SinglyLinkedList<>();
        list7.addLast(2);
        list7.addFirst(1);
        list7.addLast(3);
        list7.addFirst(0);
        System.out.println("Alternating adds:     " + list7);              // 0 -> 1 -> 2 -> 3 -> null
        System.out.println("size:                 " + list7.size());        // 4

        // Test NoSuchElementException on removeFirst empty
        try {
            new SinglyLinkedList<>().removeFirst();
            System.out.println("removeFirst empty:    FAILED (no exception)");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("removeFirst empty:    OK (exception thrown)");
        }

        // Test NoSuchElementException on removeLast empty
        try {
            new SinglyLinkedList<>().removeLast();
            System.out.println("removeLast empty:     FAILED (no exception)");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("removeLast empty:     OK (exception thrown)");
        }

        // Test addAt middle
        SinglyLinkedList<Integer> list8 = new SinglyLinkedList<>();
        list8.addLast(1); list8.addLast(2); list8.addLast(3);
        list8.addAt(1, 99);
        System.out.println("addAt(1,99):          " + list8);              // 1 -> 99 -> 2 -> 3 -> null
        System.out.println("size:                 " + list8.size());        // 4

        // Test addAt head and tail
        list8.addAt(0, 0);
        System.out.println("addAt(0,0):           " + list8);              // 0 -> 1 -> 99 -> 2 -> 3 -> null
        list8.addAt(list8.size(), 100);
        System.out.println("addAt(end,100):       " + list8);              // 0 -> 1 -> 99 -> 2 -> 3 -> 100 -> null

        // Test removeAt middle
        SinglyLinkedList<Integer> list9 = new SinglyLinkedList<>();
        list9.addLast(1); list9.addLast(2); list9.addLast(3); list9.addLast(4);
        System.out.println("removeAt(1):          " + list9.removeAt(1));  // 2
        System.out.println("After removeAt(1):    " + list9);              // 1 -> 3 -> 4 -> null
        System.out.println("removeAt(0):          " + list9.removeAt(0));  // 1
        System.out.println("After removeAt(0):    " + list9);              // 3 -> 4 -> null
        System.out.println("removeAt(last):       " + list9.removeAt(list9.size() - 1)); // 4
        System.out.println("After removeAt(last): " + list9);              // 3 -> null

        // Test removeByValue
        SinglyLinkedList<Integer> list10 = new SinglyLinkedList<>();
        list10.addLast(1); list10.addLast(2); list10.addLast(3); list10.addLast(2);
        System.out.println("removeByValue(2):     " + list10.removeByValue(2)); // true
        System.out.println("After remove first 2: " + list10);                  // 1 -> 3 -> 2 -> null
        System.out.println("removeByValue(1):     " + list10.removeByValue(1)); // true (head)
        System.out.println("After remove head:    " + list10);                  // 3 -> 2 -> null
        System.out.println("removeByValue(2):     " + list10.removeByValue(2)); // true (tail)
        System.out.println("After remove tail:    " + list10);                  // 3 -> null
        System.out.println("removeByValue(99):    " + list10.removeByValue(99));// false

        // Test reverse
        SinglyLinkedList<Integer> list11 = new SinglyLinkedList<>();
        list11.addLast(1); list11.addLast(2); list11.addLast(3); list11.addLast(4);
        list11.reverse();
        System.out.println("After reverse:        " + list11);             // 4 -> 3 -> 2 -> 1 -> null
        System.out.println("size after reverse:   " + list11.size());      // 4
        System.out.println("get(0) new head:      " + list11.get(0));      // 4
        System.out.println("get(3) new tail:      " + list11.get(3));      // 1

        // Test reverse single and empty
        SinglyLinkedList<Integer> list12 = new SinglyLinkedList<>();
        list12.addLast(42);
        list12.reverse();
        System.out.println("Reverse single:       " + list12);             // 42 -> null
        new SinglyLinkedList<Integer>().reverse();
        System.out.println("Reverse empty:        OK (no exception)");
    }
}
