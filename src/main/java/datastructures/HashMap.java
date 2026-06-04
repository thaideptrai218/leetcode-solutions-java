package datastructures;

import java.util.Objects;

public class HashMap {
    private static final int DEFAULT_CAPACITY = 1 << 4; // 16;
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;

    private static class Node {
        final int hash;
        final int key;
        int value;
        Node next;

        Node(int hash, int key, int value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final int getKey() {
            return key;
        }

        public final int getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        public final void setValue(int value) {
            this.value = value;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key && value == node.value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }


    private Node[] tables;
    private int size;

    private static int hashOf(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash & tables.length - 1;
    }

    public HashMap() {
        tables = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(int key, int value) {
        int hashCode = hashOf(key);
        int i = indexFor(hashCode);
        for (Node cur = tables[i]; cur != null; cur = cur.next) {
            if (hashCode == cur.hash && key == cur.key) {
                cur.value = value;
                return;
            }
        }
        tables[i] = new Node(hashCode, key, value, tables[i]);
        if (++size > LOAD_FACTOR_THRESHOLD * tables.length) resize();
    }

    public void remove(int key) {
        int hashCode = hashOf(key);
        int i = indexFor(hashCode);
        Node prev = null;
        for (Node cur = tables[i]; cur != null; prev = cur, cur = cur.next) {
            if (cur.hash == hashCode && key == cur.key) {
                if (prev == null) {
                    tables[i] = tables[i].next;
                } else {
                    prev.next = cur.next;
                }
            }
        }
    }

    public int get(int key) {
        int hashCode = hashOf(key);
        int i = indexFor(hashCode);
        for (Node cur = tables[i]; cur != null; cur = cur.next) {
            if (hashCode == cur.hash && key == cur.key) {
                return cur.value;
            }
        }
        return 0;
    }

    private void resize() {
        Node[] old = tables;
        tables = new Node[old.length * 2];
        for (Node head : old) {
            for (Node n = head; n != null; ) {
                Node next = n.next;
                int i = indexFor(n.hash);
                n.next = tables[i];
                tables[i] = n;
                n = next;
            }
        }
    }
}
