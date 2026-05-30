package strings;

import javax.xml.stream.FactoryConfigurationError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 242 — Valid Anagram
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word formed by rearranging the letters of another word using all
 * original letters exactly once.
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 * <p>
 * Follow up: What if the inputs contain Unicode characters?
 * How would you adapt your solution?
 * <p>
 * Example 1:
 * Input:  s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input:  s = "rat", t = "car"
 * Output: false
 */
public class P0242ValidAnagram {

    // Sort both strings and compare. O(n log n) time, O(n) space.
    public boolean sortApproach(String s, String t) {
        var sCharArray = s.toCharArray();
        var tCharArray = t.toCharArray();
        if (sCharArray.length != tCharArray.length) return false;

        Arrays.sort(sCharArray);
        Arrays.sort(tCharArray);

        for (int i = 0; i < sCharArray.length; i++) {
            if (sCharArray[i] != tCharArray[i]) return false;
        }
        return true;
    }

    // Frequency count with int[26] array. O(n) time, O(1) space.
    public boolean frequencyCount(String s, String t) {
        // TODO: implement
        int[] fre = new int[26];
        var sCharArray = s.toCharArray();
        var tCharArray = t.toCharArray();
        final int CODE_POINT_a = (int) 'a';

        for (int i = 0; i < sCharArray.length; i++) {
            fre[Character.codePointAt(sCharArray, i) - CODE_POINT_a] += 1;
        }

        for (int i = 0; i < tCharArray.length; i++) {
            fre[Character.codePointAt(tCharArray, i) - CODE_POINT_a] -= 1;
        }


        for (var num : fre) {
            if (num != 0) return false;
        }
        return true;
    }

    // HashMap frequency count — handles Unicode follow-up. O(n) time, O(n) space.
    public boolean hashMapCount(String s, String t) {
        // TODO: implement
        Map<Character, Integer> map = new HashMap<>();
        var sCharArray = s.toCharArray();
        var tCharArray = t.toCharArray();

        for (int i = 0; i < sCharArray.length; i++) {
            var value = map.getOrDefault(sCharArray[i], 0);
            map.put(sCharArray[i], ++value);
        }

        for (int i = 0; i < tCharArray.length; i++) {
            var value = map.getOrDefault(tCharArray[i], 0);
            map.put(tCharArray[i], --value);
        }

        boolean flag = false;

        for ( var entry : map.entrySet() ) {
            if (entry.getValue() != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P0242ValidAnagram solution = new P0242ValidAnagram();

        System.out.println("Input:    s=\"anagram\", t=\"nagaram\"");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.hashMapCount("anagram", "nagaram"));
        System.out.println();

        System.out.println("Input:    s=\"rat\", t=\"car\"");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.hashMapCount("rat", "car"));
        System.out.println();

        System.out.println("Input:    s=\"a\", t=\"ab\" (different lengths)");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.hashMapCount("a", "ab"));
        System.out.println();

        System.out.println("Input:    s=\"ab\", t=\"ba\"");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.hashMapCount("ab", "ba"));
        System.out.println();

        System.out.println("Input:    s=\"aacc\", t=\"ccac\" (same chars, wrong count)");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.hashMapCount("aacc", "ccac"));
    }
}
