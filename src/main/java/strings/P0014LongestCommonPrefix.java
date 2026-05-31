package strings;

import java.util.Arrays;

/**
 * LeetCode 14 — Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * <p>
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 * <p>
 * Example 1:
 * Input:  strs = ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Example 2:
 * Input:  strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class P0014LongestCommonPrefix {

    /**
     * Find the longest common prefix shared by every string in strs.
     * <p>
     * Hint: the answer can never be longer than the SHORTEST string in the array.
     * <p>
     * Two classic strategies (don't need both — pick one):
     *   - Vertical scan: walk a column index 0,1,2,... For each column, check that
     *     EVERY string has the same character there. Stop at the first mismatch,
     *     or when you run off the end of any string.
     *   - Horizontal scan: treat strs[0] as a candidate prefix, then for each other
     *     string, shrink the candidate until it actually is a prefix of that string.
     * <p>
     * Edge cases worth handling: an empty string "" anywhere forces the answer to "",
     * and a single-element array returns that element unchanged.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        String minStr = strs[0];
        for (int indexStr = 0; indexStr < minStr.length(); indexStr++) {
            char c = minStr.charAt(indexStr);
            for (int i = 1; i < strs.length; i++) {
                String curString = strs[i];
                if (indexStr >= curString.length() || c != curString.charAt(indexStr)) {
                    return res.toString();
                }
            }
            res.append(c);
        }
        return res.toString();
    }



    public static void main(String[] args) {
        P0014LongestCommonPrefix solution = new P0014LongestCommonPrefix();

        System.out.println("Input:    [\"flower\",\"flow\",\"flight\"]");
        System.out.println("Expected: \"fl\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}) + "\"");
        System.out.println();

        System.out.println("Input:    [\"dog\",\"racecar\",\"car\"]");
        System.out.println("Expected: \"\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}) + "\"");
        System.out.println();

        System.out.println("Input:    [\"interspecies\",\"interstellar\",\"interstate\"]");
        System.out.println("Expected: \"inters\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"interspecies", "interstellar", "interstate"}) + "\"");
        System.out.println();

        System.out.println("Input:    [\"throne\"] (single string)");
        System.out.println("Expected: \"throne\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"throne"}) + "\"");
        System.out.println();

        System.out.println("Input:    [\"ab\",\"abc\"] (one is prefix of another)");
        System.out.println("Expected: \"ab\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"ab", "abc"}) + "\"");
        System.out.println();

        System.out.println("Input:    [\"\",\"b\"] (empty string in array)");
        System.out.println("Expected: \"\"");
        System.out.println("Actual:   \"" + solution.longestCommonPrefix(new String[]{"", "b"}) + "\"");
    }
}
