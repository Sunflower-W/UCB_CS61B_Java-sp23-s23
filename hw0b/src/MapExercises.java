import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /**
     * Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), i + 1);
        }
        return map;
    }

    /**
     * Returns a map from the integers in the list to their squares. For example, if the input list
     * is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        if (nums.isEmpty()) {
            return new TreeMap<>();
        } else {
            Map<Integer, Integer> map = new TreeMap<>();
            for (Integer elem : nums) {
                map.put(elem, (int) Math.pow(elem.doubleValue(), 2));
            }
            return map;
        }
    }

    /**
     * Returns a map of the counts of all words that appear in a list of words.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        if (words.isEmpty()) {
            return new TreeMap<>();
        } else {
            Map<String, Integer> map = new TreeMap<>();
            for (String elem : words) {
                if (map.containsKey(elem)) {
                    map.replace(elem, map.get(elem) + 1);
                }
                else {
                    map.put(elem, 1);
                }
            }
            return map;
        }
    }
}
