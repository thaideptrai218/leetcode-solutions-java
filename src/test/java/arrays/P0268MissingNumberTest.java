package arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class P0268MissingNumberTest {

    private final P0268MissingNumber sol = new P0268MissingNumber();

    @Test
    void example1() {
        int[] nums = {3, 0, 1};
        assertEquals(2, sol.bruteForce(nums.clone()));
        assertEquals(2, sol.sorting(nums.clone()));
        assertEquals(2, sol.markerArray(nums.clone()));
        assertEquals(2, sol.xor(nums.clone()));
    }

    @Test
    void example2() {
        int[] nums = {0, 1};
        assertEquals(2, sol.bruteForce(nums.clone()));
        assertEquals(2, sol.sorting(nums.clone()));
        assertEquals(2, sol.markerArray(nums.clone()));
        assertEquals(2, sol.xor(nums.clone()));
    }

    @Test
    void example3() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        assertEquals(8, sol.bruteForce(nums.clone()));
        assertEquals(8, sol.sorting(nums.clone()));
        assertEquals(8, sol.markerArray(nums.clone()));
        assertEquals(8, sol.xor(nums.clone()));
    }

    @Test
    void singleElementMissingZero() {
        int[] nums = {1};
        assertEquals(0, sol.bruteForce(nums.clone()));
        assertEquals(0, sol.sorting(nums.clone()));
        assertEquals(0, sol.markerArray(nums.clone()));
        assertEquals(0, sol.xor(nums.clone()));
    }

    @Test
    void singleElementMissingOne() {
        int[] nums = {0};
        assertEquals(1, sol.bruteForce(nums.clone()));
        assertEquals(1, sol.sorting(nums.clone()));
        assertEquals(1, sol.markerArray(nums.clone()));
        assertEquals(1, sol.xor(nums.clone()));
    }
}
