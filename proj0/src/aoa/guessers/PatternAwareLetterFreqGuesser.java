package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

import aoa.utils.LFGHelper;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public List KeepOnlyWordsThatMatchPattern(String pattern, List<String> all_words) {
        List<String> new_words = new ArrayList<>();
        for (String elem: all_words) {
            if (elem.length() == pattern.length()) { // In the full dataset, not every word is of the same length.
                String s = "";
                for (int i = 0; i < elem.length(); i ++) {
                    if (pattern.charAt(i) == '-') {
                        s += "-";
                    }
                    else {
                        s += elem.charAt(i);
                    }
                }
                if (s.equals(pattern)) { // 符合pattern中每个字母的要求
                    new_words.add(elem);
                }
            }
        }
        return new_words;
    }

    public Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> new_words) {
        Map<Character, Integer> map = new TreeMap<>();
        for (String elem : new_words) {
            for (int i = 0; i < elem.length(); i ++) {
                if (!map.keySet().contains(elem.charAt(i))) {
                    map.put(elem.charAt(i), 1);
                }
                else {
                    map.replace(elem.charAt(i), map.get(elem.charAt(i)) + 1);
                }
            }
        }
        return map;
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map <Character, Integer> map;
        if (pattern == "----") {  // 如果字符串中每个字符都满足一定的条件，则语句
            map = getFreqMapThatMatchesPattern(words);
        }
        else {
            List <String> list = KeepOnlyWordsThatMatchPattern(pattern, words);
            map = getFreqMapThatMatchesPattern(list);
        }
        for (Character elem: guesses) {
            map.remove(elem);
        }
        Character MaxK = null;
        Integer MaxV = 0;
        for (Character elem : map.keySet()) {
            if (map.get(elem) > MaxV) {
                MaxV = map.get(elem);
                MaxK = elem;
            }
        }
//        LFGHelper.
        return MaxK;
    }


    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.KeepOnlyWordsThatMatchPattern("-e--", palfg.words));
        System.out.println(palfg.getFreqMapThatMatchesPattern(palfg.KeepOnlyWordsThatMatchPattern("-e--", palfg.words)));
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}