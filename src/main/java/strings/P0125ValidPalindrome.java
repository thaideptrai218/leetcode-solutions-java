package strings;

/**
 * LeetCode 125 — Valid Palindrome
 * <p>
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase
 * letters and removing all non-alphanumeric characters, it reads the same forward
 * and backward. Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Constraints:
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 * <p>
 * Example 1:
 * Input:  s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * <p>
 * Example 2:
 * Input:  s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * <p>
 * Example 3:
 * Input:  s = " "
 * Output: true
 * Explanation: After removing non-alphanumeric chars, the string is empty
 *              which reads the same forward and backward.
 */
public class P0125ValidPalindrome {

    // Filter + reverse compare. O(n) time, O(n) space.
    public boolean isPalindromeFiltered(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int n = chars.length;
        char[] charsReverse = new char[chars.length];

        for (int i = 0; i < n; i++) {
            charsReverse[i] = chars[n - i - 1];
        }

        int index1 = 0;
        int index2 = 0;

        while (index1 < n && index2 < n) {
            while (index1 < n && !Character.isLetterOrDigit(chars[index1])) index1++;
            while (index2 < n && !Character.isLetterOrDigit(charsReverse[index2])) index2++;

            if (index1 < n && index2 < n) {
                if (chars[index1] != charsReverse[index2]) return false;
                index1++;
                index2++;
            }
        }

        return true;
    }

    // Two pointers from both ends, skipping non-alphanumerics. O(n) time, O(1) space.
    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int n = chars.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            while (left < n && !Character.isLetterOrDigit(chars[left])) left++;
            while (right > 0 && !Character.isLetterOrDigit(chars[right])) right--;
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        P0125ValidPalindrome solution = new P0125ValidPalindrome();

        System.out.println("Input:    \"A man, a plan, a canal: Panama\"");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.isPalindromeFiltered("A man, a plan, a canal: Panama"));
        System.out.println();

        System.out.println("Input:    \"race a car\"");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.isPalindromeFiltered("race a car"));
        System.out.println();

        System.out.println("Input:    \" \" (only space)");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.isPalindromeFiltered(" "));
        System.out.println();

        System.out.println("Input:    \".,\" (only non-alphanumeric)");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.isPalindromeFiltered(".,"));
        System.out.println();

        System.out.println("Input:    \"0P\" (digit vs letter — must NOT match)");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.isPalindromeFiltered("0P"));
        System.out.println();

        System.out.println("Input:    \"ab_a\" (underscore is NOT alphanumeric)");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.isPalindromeFiltered("ab_a"));
    }
}
