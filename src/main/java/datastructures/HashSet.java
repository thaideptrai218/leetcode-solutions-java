package datastructures;
/**
 * A hash set built from scratch using SEPARATE CHAINING for collision handling.
 * <p>
 * Mental model:
 * - We keep an array of "buckets". Each bucket is the head of a small singly
 * linked list (a "chain") — exactly the SinglyLinkedList idea you already built.
 * - To store a key, a HASH FUNCTION maps it to one bucket index. Many keys can
 * map to the same bucket (a "collision"); they simply live in the same chain.
 * - add / contains / remove all do the same first step: hash the key to find its
 * bucket, then walk that one (short) chain.
 * <p>
 * Why it's fast: if the chains stay short (few collisions), each operation only
 * touches a handful of nodes → average O(1). Chains stay short because we RESIZE
 * (grow the bucket array and rehash everything) once it gets too crowded.
 * <p>
 * Load factor = size / number_of_buckets. When it crosses ~0.75, we resize so the
 * average chain length stays near 1.
 */
public class HashSet<T> {
    private static final class Node<T> {
        final int hash;            // ← NEW: cache the hash (the JDK trick from our lesson)
        final T key;
        Node<T> next;

        Node(int hash, T key, Node<T> next) {
            this.hash = hash;
            this.key = key;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Node[] buckets;
    private int size;

    public HashSet() {
        this.buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static int hashOf(Object key) {
        return key.hashCode() & 0x7fffffff;
    }

    private int indexFor(int hash) {
        return hash % buckets.length;
    }

    public boolean add(T key) {
        int h = hashOf(key), i = indexFor(h);
        for (Node<T> n = buckets[i]; n != null; n = n.next)
            if (n.hash == h && key.equals(n.key)) return false;
        buckets[i] = new Node<>(h, key, buckets[i]);            // prepend, O(1)
        if (++size > LOAD_FACTOR_THRESHOLD * buckets.length) resize();
        return true;
    }

    public boolean contains(T key) {
        int h = hashOf(key);
        for (Node<T> n = buckets[indexFor(h)]; n != null; n = n.next)
            if (n.hash == h && key.equals(n.key)) return true;
        return false;
    }

    public boolean remove(T key) {
        int h = hashOf(key), i = indexFor(h);
        Node<T> prev = null;
        for (Node<T> n = buckets[i]; n != null; prev = n, n = n.next) {
            if (n.hash == h && key.equals(n.key)) {
                if (prev == null) buckets[i] = n.next;
                else prev.next = n.next;
                size--;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<T>[] old = buckets;
        buckets = (Node<T>[]) new Node[old.length * 2];
        for (Node<T> head : old)
            for (Node<T> n = head; n != null; ) {
                Node<T> next = n.next;          // save before relinking
                int i = indexFor(n.hash);       // reuse cached hash — no hashCode() recompute
                n.next = buckets[i];            // move the SAME node object
                buckets[i] = n;
                n = next;
            }
    }


    // ---------------------------------------------------------------------------
// Test harness — prints PASS/FAIL. Stubs will FAIL until you implement them.
// ---------------------------------------------------------------------------
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        check("empty: isEmpty", set.isEmpty(), true);
        check("empty: size 0", set.size(), 0);
        check("empty: contains(5)", set.contains(5), false);

        check("add(5) -> true", set.add(5), true);
        check("add(5) again -> false (no dupes)", set.add(5), false);
        check("contains(5)", set.contains(5), true);
        check("size after one add", set.size(), 1);

        check("add(10) -> true", set.add(10), true);
        check("add(15) -> true", set.add(15), true);
        check("size now 3", set.size(), 3);

        // Collision test: with 16 buckets, 1 and 17 both hash to index 1 (17 % 16 == 1).
        set.add(1);
        set.add(17);
        check("collision: contains(1)", set.contains(1), true);
        check("collision: contains(17)", set.contains(17), true);

        check("remove(10) -> true", set.remove(10), true);
        check("contains(10) after remove", set.contains(10), false);
        check("remove(10) again -> false", set.remove(10), false);
        check("size after remove", set.size(), 4);

        // Resize test: add many keys to force at least one resize, then verify all survive.
        HashSet<Integer> big = new HashSet<>();
        for (int i = 0; i < 100; i++) big.add(i);
        check("resize: size 100", big.size(), 100);
        boolean allPresent = true;
        for (int i = 0; i < 100; i++) if (!big.contains(i)) allPresent = false;
        check("resize: all 100 keys survive rehash", allPresent, true);
        check("resize: contains(999) still false", big.contains(999), false);

        // Generics: works for any type with hashCode/equals.
        HashSet<String> words = new HashSet<>();
        words.add("hello");
        words.add("world");
        check("string: contains(\"hello\")", words.contains("hello"), true);
        check("string: contains(\"missing\")", words.contains("missing"), false);
    }

    private static void check(String label, Object actual, Object expected) {
        boolean pass = (actual == null && expected == null) || (actual != null && actual.equals(expected));
        System.out.printf("%-45s expected=%-6s actual=%-6s %s%n", label, expected, actual, pass ? "PASS" : "FAIL");
    }


}
