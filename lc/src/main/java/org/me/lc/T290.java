package org.me.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sangnk
 * @Created 07/10/2024 - 8:36 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class T290 {

    /*
     Given a pattern and a string s, find if s follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:

    Each letter in pattern maps to exactly one unique word in s.
    Each unique word in s maps to exactly one letter in pattern.
    No two letters map to the same word, and no two words map to the same letter.
     */
    public static void main(String[] args) {
        System.out.println(wordPattern("aaaa", "dog cat cat dog"));
    }

    public static boolean wordPattern(String pattern, String s) {
        Map<String, List<Integer>> positionSameWordOfPattern = new HashMap<>();
        Map<String, List<Integer>> positionSameWordOfString = new HashMap<>();

        List<String> allWordsNotDuplicateOfPattern = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (allWordsNotDuplicateOfPattern.contains(String.valueOf(pattern.charAt(i)))) {
                continue;
            }
            allWordsNotDuplicateOfPattern.add(String.valueOf(pattern.charAt(i)));
        }
        for (int i = 0; i < allWordsNotDuplicateOfPattern.size(); i++) {
            String word = allWordsNotDuplicateOfPattern.get(i);
            for (int j = 0; j < pattern.length(); j++) {
                if (String.valueOf(pattern.charAt(j)).equals(word)) {
                    if (positionSameWordOfPattern.containsKey(word)) {
                        List<Integer> positions = positionSameWordOfPattern.get(word);
                        positions.add(j);
                        positionSameWordOfPattern.put(word, positions);
                    } else {
                        List<Integer> positions = new ArrayList<>();
                        positions.add(j);
                        positionSameWordOfPattern.put(word, positions);
                    }
                }
            }
        }
        List<String> allWordsNotDuplicateOfString = new ArrayList<>();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (allWordsNotDuplicateOfString.contains(words[i])) {
                continue;
            }
            allWordsNotDuplicateOfString.add(words[i]);
        }
        for (int i = 0; i < allWordsNotDuplicateOfString.size(); i++) {
            String word = allWordsNotDuplicateOfString.get(i);
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(word)) {
                    if (positionSameWordOfString.containsKey(word)) {
                        List<Integer> positions = positionSameWordOfString.get(word);
                        positions.add(j);
                        positionSameWordOfString.put(word, positions);
                    } else {
                        List<Integer> positions = new ArrayList<>();
                        positions.add(j);
                        positionSameWordOfString.put(word, positions);
                    }
                }
            }
        }
        if (positionSameWordOfString.size() != positionSameWordOfPattern.size()) {
            return false;
        }
        for (int i = 0; i < positionSameWordOfString.size(); i++) {
            String word = allWordsNotDuplicateOfString.get(i);
            String wordPattern = allWordsNotDuplicateOfPattern.get(i);
            List<Integer> positionsOfString = positionSameWordOfString.get(word);
            List<Integer> positionsOfPattern = positionSameWordOfPattern.get(wordPattern);
            if (positionsOfString.size() != positionsOfPattern.size()) {
                return false;
            }
            for (int j = 0; j < positionsOfString.size(); j++) {
                if (!positionsOfString.get(j).equals(positionsOfPattern.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
