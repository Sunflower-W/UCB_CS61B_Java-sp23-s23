import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    public void toList() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i ++) {
            ad1.addLast(i);
        }
        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4);
    }
    @Test
    public void isEmptyAndSizeTest() {
        Deque<Character> ad1 = new ArrayDeque<>();

        assertThat(ad1.isEmpty()).isTrue();
        assertThat(ad1.size()).isEqualTo(0);
        ad1.addLast('f');
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void getTest() {
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("myTest");
        assertWithMessage("get receives an invalid index").that(ad1.get(33)).isNull();
        assertWithMessage("get receives an invalid index").that(ad1.get(-2)).isNull();
        assertThat(ad1.get(0)).isNotEqualTo("myTest");
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addLast(2);
        ad.addFirst(1);
        assertThat(ad.toList()).containsExactly(1, 2);
        ad.addLast(3);
        assertThat(ad.toList()).containsExactly(1, 2, 3);
        ad.removeFirst();
        assertThat(ad.toList()).containsExactly(2, 3);
        ad.removeFirst();
        ad.removeLast();
        assertThat(ad.removeLast()).isNull();
    }

    @Test
    public void resizeDownTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 33; i ++) {
            ad1.addLast(0);
        }
        assertThat(ad1.size()).isEqualTo(33);
        for (int j = 0; j < 25; j ++) {
            ad1.removeFirst();
        }
        assertThat(ad1.size()).isLessThan(16);

        Deque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i <10000; i ++) {
            ad2.addLast(0);
        }
        assertThat(ad2.size()).isEqualTo(10000);
        for (int j = 0; j < 9999; j ++) {
            ad2.removeFirst();
        }
        assertThat(ad2.size()).isLessThan(30);
    }

    @Test
    public void get() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i ++) {
            ad1.addLast(i);
        }
        assertThat(ad1.get(0)).isEqualTo(0);
    }
}
