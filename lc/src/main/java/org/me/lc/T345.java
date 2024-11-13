package org.me.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sangnk
 * @Created 07/10/2024 - 9:05 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class T345 {
    /*
     Given a string s, reverse only all the vowels in the string and return it.

    The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

    Example 1:

    Input: s = "IceCreAm"

    Output: "AceCreIm"

    Explanation:

    The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

    Example 2:

    Input: s = "leetcode"

    Output: "leotcede"
     */
    public static void main(String[] args) {
//        System.out.println(reverseVowels("IceCreAm"));
        System.out.println(reverseVowels2("a."));
    }
    public static String reverseVowels(String s) {
        List<String> isVowels = List.of("a", "e", "i", "o", "u", "A", "E", "I", "O", "U");
        List<String> vowelsInString = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isVowels.contains(s.substring(i, i + 1))) {
                vowelsInString.add(s.substring(i, i + 1));
            }
        }

        // reverse vowelsInString
        Collections.reverse(vowelsInString);

        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isVowels.contains(s.substring(i, i + 1))) {
                s = s.substring(0, i) + vowelsInString.get(flag++) + s.substring(i + 1);
            }
        }
        return s;

    }

    public static String reverseVowels2(String s) {
        List<String> isVowels = List.of("a", "e", "i", "o", "u", "A", "E", "I", "O", "U");
        int left = 0;
        int right = s.length() - 1;
        boolean hasNextLeft = false;
        boolean hasNextRight = false;
        while ( left < right) {
            hasNextLeft = false;
            hasNextRight = false;
            for (int i = right; i > left; i--) {
                String currKey = s.substring(i, i + 1);
                if (isVowels.contains(currKey)) {
                    right = i;
                    hasNextLeft = true;
                    break;
                }
            }
            for (int i = left; i < right; i++) {
                String currKey = s.substring(i, i + 1);
                if (isVowels.contains(currKey)) {
                    left = i;
                    hasNextRight = true;
                    break;
                }
            }
            if (!hasNextLeft || !hasNextRight) {
                break;
            }
            if (left < right) {
                s = s.substring(0, left) + s.substring(right, right + 1) + s.substring(left + 1, right) + s.substring(left, left + 1) + s.substring(right + 1);
                left++;
                right--;
            }
        }
        return s;
    }
}
