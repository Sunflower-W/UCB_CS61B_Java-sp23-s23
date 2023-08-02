package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map <Character, Integer> map = new TreeMap();
        for (String elem : words) {
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

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt"); // 初始化
        Map <Character, Integer> map = nlfg.getFrequencyMap(); // 调用上一个方法
        if (map.isEmpty()) {
            return '?';
        }
        else {
            for (Character elem : guesses) {
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
            return MaxK;
        }
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt"); // 初始化
        System.out.println("list of words: " + nlfg.words); // 调用属性
        System.out.println("frequency map: " + nlfg.getFrequencyMap()); // 调用方法

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
