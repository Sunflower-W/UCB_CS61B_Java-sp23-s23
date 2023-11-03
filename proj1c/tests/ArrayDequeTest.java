import deque.ArrayDeque;
import deque.Deque;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    public void addFirstAndLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            ad.addFirst(i);
        }
        assertThat(ad.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        assertThat(ad.toString()).isEqualTo("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]");
    }

    @Test
    public void isEmptyAndSizeTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad).isEmpty();
        assertThat(ad.size()).isEqualTo(0);
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque<String> ad = new ArrayDeque<>();
        ad.addLast("middle");
        ad.addFirst("first");
        ad.addLast("last");
        assertThat(ad.toList()).containsExactly("first", "middle", "last");
        ad.removeFirst();
        assertThat(ad.toList()).containsExactly("middle", "last");
        ad.removeLast();
        assertThat(ad.toList()).containsExactly("middle");
    }

    @Test
    public void getAndGetRecursiveTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        Deque<Double> ad0 = new ArrayDeque<>();
        for (int i = 0; i < 10000; i += 1) {
            ad.addLast(i);
        }
        Stopwatch sw1 = new Stopwatch();
        assertThat(ad.get(5000)).isEqualTo(5000); // nextFirst = 4,属性是private，不能被外部调用
        ad0.addFirst(sw1.elapsedTime());
        Stopwatch sw2 = new Stopwatch();
        assertThat(ad.getRecursive(5000)).isEqualTo(5000);
        ad0.addLast(sw2.elapsedTime());
        ad0.toList();
    }

    @Test
    public void equalTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addLast(0);
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(0);
        assertThat(ad.equal(ad1)).isTrue();
    }
}
