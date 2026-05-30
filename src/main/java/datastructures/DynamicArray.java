package datastructures;

/**
 * Dynamic Array — generic implementation for learning purposes.
 * <p>
 * A dynamic array automatically resizes when it runs out of capacity.
 * Internally backed by Object[] (same approach as Java's ArrayList).
 * <p>
 * Why Object[]? Java generics use type erasure — the JVM does not know T
 * at runtime, so T[] cannot be allocated directly. Object[] is the fallback.
 * Elements are cast to T on read via an unchecked cast.
 * <p>
 * Operations to implement:
 * - get(index)        → O(1)
 * - set(index, value) → O(1)
 * - add(value)        → O(1) amortized
 * - remove(index)     → O(n)
 * - size()            → O(1)
 * - isEmpty()         → O(1)
 * - contains(value)   → O(n)
 * <p>
 * Key internals to understand:
 * - capacity (data.length) vs size (elements in use)
 * - growth factor (why 2x?)
 * - why shrinking is optional but useful
 */
public class DynamicArray<T> {

    private Object[] data;
    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    public DynamicArray() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    public DynamicArray(int initialCapacity) {
        data = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Returns the number of elements currently stored.
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if no elements are stored.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns element at index. Throws if out of bounds.
     */
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Overwrites element at index. Throws if out of bounds.
     */
    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    /**
     * Appends value to the end, resizing if needed.
     */
    public void add(T value) {
        // TODO: implement — when is resize needed?
        if (size == data.length) {
            resize();
        }

        data[size] = value;
        size++;
    }

    /**
     * Removes element at index, shifts remaining elements left. Nulls out last slot.
     */
    public T remove(int index) {
        // TODO: implement — remember to null the freed slot to avoid memory leak
        checkIndex(index);
        T value = (T) data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[--size] = null;
        if (size > 0 && size <= data.length / 4 && data.length / 2 >= DEFAULT_INITIAL_CAPACITY) {
            shrink();
        }
        return value;
    }

    /**
     * Returns true if value exists in the array. Handles null safely.
     */
    public boolean contains(T value) {
        // TODO: implement — use equals(), not ==. Handle null value case.
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(data[i])) return true;
            }
        }

        return false;
    }

    /**
     * Inserts value at index, shifting existing elements right.
     * Hint: validate index in range [0, size] (size is valid — appends at end).
     *       Resize first if full. Then shift elements from the end down to index,
     *       then place the value. Think: which direction do you shift to avoid overwriting?
     */
    public void addAt(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (size == data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    /**
     * Returns the first index where value is found, or -1 if not present.
     * Hint: same null-safe pattern as contains() — check separately for null vs non-null.
     */
    public int indexOf(T value) {
        // TODO: implement
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(data[i])) return i;
            }
        }
        return -1;
    }

    /**
     * Removes all elements. Nulls every slot to release object references.
     * Hint: loop through data[0..size-1], null each slot, then reset size.
     *       Should you also reset capacity? Think about what makes sense.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Halves internal capacity when size drops below 25% of capacity.
     * Called inside remove() after decrementing size.
     * Hint: only shrink if capacity > initial minimum (avoid shrinking to 0).
     *       New capacity = data.length / 2. Copy size elements, reassign data.
     */
    private void shrink() {
        // TODO: implement
        Object[] newData = new Object[data.length / 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * Doubles internal capacity and copies existing data.
     */
    private void resize() {
        // TODO: implement — allocate new Object[], copy, reassign data
        Object[] newData = new Object[data.length * 2];

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
    }

    /**
     * Validates index is within [0, size).
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<>();

        // Test add and toString
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println("After add 1,2,3:   " + arr);              // [1, 2, 3]
        System.out.println("Size:              " + arr.size());        // 3

        // Test get
        System.out.println("get(0):            " + arr.get(0));        // 1
        System.out.println("get(2):            " + arr.get(2));        // 3

        // Test set
        arr.set(1, 99);
        System.out.println("After set(1,99):   " + arr);               // [1, 99, 3]

        // Test contains
        System.out.println("contains(99):      " + arr.contains(99));  // true
        System.out.println("contains(5):       " + arr.contains(5));   // false

        // Test remove
        arr.remove(1);
        System.out.println("After remove(1):   " + arr);               // [1, 3]

        // Test resize — add many elements beyond initial capacity
        DynamicArray<Integer> arr2 = new DynamicArray<>(2);
        for (int i = 0; i < 10; i++) arr2.add(i);
        System.out.println("After 10 adds:     " + arr2);              // [0,1,2,...,9]
        System.out.println("Size:              " + arr2.size());       // 10

        // Test with String type
        DynamicArray<String> words = new DynamicArray<>();
        words.add("hello");
        words.add("world");
        System.out.println("String array:      " + words);             // [hello, world]

        // Test isEmpty
        DynamicArray<Integer> empty = new DynamicArray<>();
        System.out.println("isEmpty (new):     " + empty.isEmpty());   // true

        // Test null handling
        DynamicArray<String> withNull = new DynamicArray<>();
        withNull.add(null);
        withNull.add("abc");
        System.out.println("contains(null):    " + withNull.contains(null)); // true

        // Test addAt
        DynamicArray<Integer> arr3 = new DynamicArray<>();
        arr3.add(1); arr3.add(2); arr3.add(3);
        arr3.addAt(1, 99);
        System.out.println("After addAt(1,99): " + arr3);             // [1, 99, 2, 3]
        arr3.addAt(0, 0);
        System.out.println("After addAt(0,0):  " + arr3);             // [0, 1, 99, 2, 3]
        arr3.addAt(arr3.size(), 100);
        System.out.println("After addAt(end):  " + arr3);             // [0, 1, 99, 2, 3, 100]

        // Test indexOf
        System.out.println("indexOf(99):       " + arr3.indexOf(99)); // 2
        System.out.println("indexOf(999):      " + arr3.indexOf(999));// -1

        // Test clear
        arr3.clear();
        System.out.println("After clear:       " + arr3);             // []
        System.out.println("isEmpty:           " + arr3.isEmpty());   // true
    }
}
