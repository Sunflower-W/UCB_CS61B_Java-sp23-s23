package aoa.choosers;

import aoa.guessers.PAGALetterFreqGuesser;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;


public class RandomChooser implements Chooser {
    private final String chosenWord;// Store the chosen word as a field.
    private String pattern = "----";
    private char letter;

    public RandomChooser(int wordLength, String dictionaryFile) { // 这是一个构造方法 Cannot return a value from a method with void result type
        // TODO: Fill in/change this constructor.
        // You should throw an `IllegalArgumentException` if `wordLength` is less than one.
        if (wordLength <= 0) throw new IllegalArgumentException("argument must be positive: " + wordLength);
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);// 从文件中取出符合字符长度的字符，生成words列表
        // You should throw an `IllegalStateException` if there are no words found of `wordLength`.
        if (words.isEmpty()) throw new IllegalStateException("list cannot be empty:" + words);
        int numWords = words.size(); // 待选字符列表words中包含字符数量 // Words is a list of the desired length in sorted order.i.e. the output of calling `readWordsOfLength` from `FileUtils.java` in `utils`.
        int randomlyChosenWordNumber = StdRandom.uniform(numWords); // 字符列表的随机序列
        chosenWord = words.get(randomlyChosenWordNumber); // 选择随机字符;
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        // return 0;
        this.letter = letter;
        int numOfOccur = 0;
        for (int i = 0; i < chosenWord.length(); i ++) {
            if (chosenWord.charAt(i) == letter) {
                numOfOccur ++;
            }
        }
        return numOfOccur;
    }

    @Override
    public String getPattern() { // 更新pattern
        // TODO: Fill in this method.
        // return "";
// pattern = ""; // 避免每次调用时重置pattern，改为成员变量初始化
//初始pattern “----”
// for (int i = 0; i < chosenWord.length(); i ++){
// pattern = pattern.concat("-");
// }
//根据一次猜中的字符更新pattern
// for (int i = 0; i < chosenWord.length(); i ++){
// if (chosenWord.charAt(i) == letter) {
// pattern = pattern.concat(String.valueOf(letter));
// }
// else {
// pattern = pattern.concat("-");
// }
// }
//根据多次猜中字符更新pattern，初始化的值也到成员变量中去
        StringBuilder patternB = new StringBuilder(pattern);
        for (int i = 0; i < chosenWord.length(); i ++){
            if (chosenWord.charAt(i) == letter) {
                patternB.setCharAt(i, letter); ;
            }
        }
        pattern = patternB.toString();
        return pattern;
    }

    @Override
    public String getWord() { // 返回随机选择器选择的任一字符字符
        // TODO: Fill in this method.
        return chosenWord;
    }
}
