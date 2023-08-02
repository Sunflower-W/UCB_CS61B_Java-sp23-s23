package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public List KeepOnlyWordsThatMatchPattern(String pattern, List<String> all_words, List<Character> guesses) {
        List<String> new_words = new ArrayList<>();
        for (String elem: all_words) {
            if (elem.length() == pattern.length()) { // In the full dataset, not every word is of the same length.
                String s = "";
                for (int i = 0; i < elem.length(); i ++) {
                    if (pattern.charAt(i) == '-' && !guesses.contains(elem.charAt(i)) ) {
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

    public List KeepOnlyWordsThatMatchGuesses(String pattern, List<Character> guesses, List<String> new_words) {
        List<String> new_words1 = new ArrayList<>();
        for (String elem: new_words){ //遍历符合模式的单词列表
            new_words1.add(elem); //复制列表
            }
        for (int i = 0; i < guesses.size(); i ++){ //遍历已猜测字符
            if (!(pattern.indexOf(guesses.get(i)) != -1)) {//如果pattern中不包含已猜测字符
                for (String elem: new_words){ //遍历符合模式的单词列表
                    if (elem.indexOf(guesses.get(i)) != -1) { //如果单词中包含该字母
                        new_words1.remove(elem);//去掉该单词
                    }
                }
            }
        }
        return new_words1;
    }

//    public List KeepOnlyWordsThatMatchGuesses(String pattern, List<Character> guesses, List<String> new_words) {
//        for (int i = 0; i < guesses.size(); i ++){ //遍历已猜测字符
//            if (!(pattern.indexOf(guesses.get(i)) != -1)) {//如果pattern中不包含已猜测字符
//                for (String elem: new_words){ //遍历符合模式的单词列表
//                    if (elem.indexOf(guesses.get(i)) != -1) { //如果单词中包含该字母
//                        new_words.remove(elem);//去掉该单词 //！！！《不能一边遍历一边修改对象》！！！
//                    }
//                }
//            }
//        }
//        return new_words;
//    }

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
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
//        LFGHelper lfghelper = new LFGHelper(); // How to use Helper class? 1 实例化（固定data集？） 2 调用属性
        Map <Character, Integer> map = null;
        if (guesses.isEmpty()) {
            map = getFreqMapThatMatchesPattern(words);
//            List<String> lst = KeepOnlyWordsThatMatchPattern(pattern, words);
        }
        else {
            List<String> lst = KeepOnlyWordsThatMatchPattern(pattern, words, guesses);
            List<String> lst1 = KeepOnlyWordsThatMatchGuesses(pattern, guesses, lst);
            map = getFreqMapThatMatchesPattern(lst1);
//            List<String> filter_words = new ArrayList<>();
//            for (int i = 0; i < guesses.size(); i ++) { // 遍历已猜测字符
//                if (!(pattern.indexOf(guesses.get(i)) != -1)) { // pattern中不包含已猜测字符
//                    for (String elem: words) {
//                        if (!(elem.indexOf(guesses.get(i)) != -1)) { // 单词中不包含该字符
//                            filter_words.add(elem); // 单词可能是结果
//                        }
//                    }
//                    List <String> lst = KeepOnlyWordsThatMatchPattern(pattern, filter_words);
//                    map = getFreqMapThatMatchesPattern(lst);
//                }
//                else { // pattern中包含已猜测字符
//                    List <String> lst = KeepOnlyWordsThatMatchPattern(pattern, words);
//                    map = getFreqMapThatMatchesPattern(lst);
//                    map.remove(lst.get(0));
//                }
//            }
        }

        Map<Character, Integer> new_map = new TreeMap<>();
        new_map.putAll(map);
        for (Character elem : map.keySet()) { //如果字符频率字典中包含已猜测字符，需要去掉
            if (guesses.contains(elem)) {
                new_map.remove(elem);
            }
        }
        Character MaxK = null;
        Integer MaxV = 0;
        for (Character elem : new_map.keySet()) { //遍历健
            if (new_map.get(elem) > MaxV) { //由健取值
                MaxV = new_map.get(elem);
                MaxK = elem;
            }
        }
        return MaxK;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e'))); // 'e'不存在于pattern中，在[ally, cool，good]中判断下一个最可能出现的字符
    }
}
