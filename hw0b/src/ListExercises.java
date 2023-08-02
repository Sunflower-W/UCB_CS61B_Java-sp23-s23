import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        if (L.size() == 0) { // if the list is empty
            return 0;
        }
        else {
            int sum = 0;
            for (int i = 0; i < L.size(); i ++) {
                sum += L.get(i);
            }
            return sum;
        }
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < L.size(); i ++) {
            if (L.get(i) % 2 == 0) {
                lst.add(L.get(i));
            }
        }
        if (lst.size() == 0) {
            return new ArrayList<>();
        }
        else {
            return lst;
        }
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        if (L1.isEmpty() || L2.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            List<Integer> lst = new ArrayList<>();
            for (int i = 0; i < L1.size(); i++) {
                for (int j = 0; j < L2.size(); j++) {
//                    System.out.println(i+"&"+j);
                    if (L1.get(i) == L2.get(j)) {
                        lst.add(L1.get(i));
                    }
                }
            }
            if (lst.isEmpty()) {
                return new ArrayList<>();
            } else {
                return lst;
            }
        }
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int x = 0;
        for (int i = 0; i < words.size(); i ++) {
            if (words.get(i).contains(Character.toString(c))) {
                x += 1;
            }
        }
        return x;
    }
}
