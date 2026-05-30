package strings;

import java.util.*;

/**
 * LeetCode 49 — Group Anagrams
 * <p>
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 * <p>
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * <p>
 * Example 1:
 * Input:  strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input:  strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input:  strs = ["a"]
 * Output: [["a"]]
 */
public class P0049GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        // TODO: implement
        List<List<String>> answer = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = Arrays.toString(charArray);
            map.putIfAbsent(key, new ArrayList<>());
            var array = map.get(key);
            array.add(str);
            map.put(key, array);
        }


        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        P0049GroupAnagrams solution = new P0049GroupAnagrams();

        System.out.println("Input:    [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]");
        System.out.println("Expected: [[bat],[nat,tan],[ate,eat,tea]]");
        System.out.println("Actual:   " + solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println();

        System.out.println("Input:    [\"\"]");
        System.out.println("Expected: [[\"\"]]");
        System.out.println("Actual:   " + solution.groupAnagrams(new String[]{""}));
        System.out.println();

        System.out.println("Input:    [\"a\"]");
        System.out.println("Expected: [[a]]");
        System.out.println("Actual:   " + solution.groupAnagrams(new String[]{"a"}));
        System.out.println();

        System.out.println("Input:    [\"abc\",\"bca\",\"xyz\",\"zyx\",\"cab\"]");
        System.out.println("Expected: [[abc,bca,cab],[xyz,zyx]]");
        System.out.println("Actual:   " + solution.groupAnagrams(new String[]{"abc", "bca", "xyz", "zyx", "cab"}));
    }
}
