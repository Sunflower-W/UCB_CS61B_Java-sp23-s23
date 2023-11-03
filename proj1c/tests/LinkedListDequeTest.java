import deque.Deque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;
//import org.junit.rules.Stopwatch;
import edu.princeton.cs.algs4.Stopwatch;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDequeTest {

    @Test
    public void addFirstTest() {
        LinkedListDeque lld = new LinkedListDeque();
        lld.addFirst(10);
        assertThat(lld.toList()).containsExactly(10);
    }

    @Test
    public void addLastTest() {
        LinkedListDeque lld = new LinkedListDeque();
        lld.addFirst("cat");
        lld.addLast("dog");
        assertThat(lld.toList()).containsExactly("cat", "dog");
    }

    @Test
    public void isEmptyAndSizeTest() {
        LinkedListDeque lld = new LinkedListDeque();
        lld.addLast("last");
        assertThat(lld.isEmpty()).isFalse();
        assertThat(lld.size()).isEqualTo(1);
    }

    @Test
    public void removeFirstAndLastTest() {
        LinkedListDeque lld = new LinkedListDeque();
        lld.addLast("last");
        lld.addFirst("first");
        assertThat(lld.toList()).containsExactly("first", "last");
        assertThat(lld.removeFirst()).isEqualTo("first");
        assertThat(lld.toList()).contains("last");
        assertThat(lld.toString()).contains("[last]");
    }

    @Test
    public void getAndGetRecursiveTest() {
        LinkedListDeque lld = new LinkedListDeque();
        for (int i = 0; i < 10000; i += 1) {
            lld.addLast(0);
        }
        Stopwatch sw1 = new Stopwatch();
        assertThat(lld.get(9999)).isEqualTo(0);
        System.out.println(sw1.elapsedTime());
        Stopwatch sw2 = new Stopwatch();
        assertThat(lld.getRecursive(0)).isEqualTo(0);
        assertThat(lld.getRecursive(1)).isEqualTo(0);
        assertThat(lld.getRecursive(9998)).isEqualTo(0);
        assertThat(lld.getRecursive(9999)).isEqualTo(0);
        System.out.println(sw2.elapsedTime());
//        System.out.println(sw1.elapsedTime() + ">" + sw2.elapsedTime());
    }

    @Test
    public void iteratorTest() {
        Deque<Integer> lld = new LinkedListDeque<>(); // new对象的时候定义item的类型
        for (int i = 0; i < 2; i += 1) {
            lld.addLast(0);
        }
        for (Integer i : lld.toList()) {
            System.out.println(i);
        }
        assertThat(lld.toList()).containsExactly(0, 0);

        Deque<String> lld1 = new LinkedListDeque<>(); // new对象的时候定义item的类型
        lld1.addFirst("middle");
        lld1.addFirst("first");
        lld1.addLast("last");
        for (String s : lld1.toList()) {
            System.out.println(s);
        }
        assertThat(lld1.toList()).containsExactly("first", "middle", "last");
    }

    @Test void equalTest() {
        LinkedListDeque lld1 = new LinkedListDeque();
        lld1.addLast("middle");
        lld1.addFirst("first");
        lld1.addLast("last");
        LinkedListDeque lld2 = new LinkedListDeque();
        lld2.addLast("middle");
        lld2.addFirst("first");
        lld2.addLast("last");
        assertThat(lld1.equal(lld2)).isTrue();
    }
}
