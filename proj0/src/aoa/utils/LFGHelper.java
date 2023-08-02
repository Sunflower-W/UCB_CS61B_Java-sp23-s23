package aoa.utils;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LFGHelper {

    private final List<String> words;

    public LFGHelper(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public static List KeepOnlyWordsThatMatchPattern(String pattern, List<String> all_words) {
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

}
