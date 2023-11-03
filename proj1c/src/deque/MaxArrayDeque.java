package deque;

import java.util.*;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private Comparator<T> comparator;
    private T[] items;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) { // items.length == size
                a[nextFirst + 1 + i] = items[nextFirst + 1 + i];
            } else {
                a[nextFirst + 1 + i] = items[nextFirst + 1 + i - items.length];
            }
        }
        nextLast = nextFirst + size + 1;
        items = a;
    }

    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            if (nextFirst + 1 + i < items.length) {
                a[i] = items[nextFirst + 1 + i];
            } else {
                a[i] = items[nextFirst + 1 + i - items.length];
            }
            nextFirst = capacity - 1;
            nextLast = size;
            items = a;
        }
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }

    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            if (nextFirst + 1 + i < items.length) {
                returnList.add(items[nextFirst + 1 + i]);
            } else {
                returnList.add(items[nextFirst + i + 1 - items.length]);
            }
        }
        return returnList;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && (float)size / items.length < 0.5) {
            resizeDown((int)Math.ceil(items.length / 2));
        }
        T a;
        if (nextFirst + 1 < items.length) {
            a = items[nextFirst + 1];
            items[nextFirst + 1] = null;
        } else {
            a = items[nextFirst + 1 - items.length];
            items[nextFirst + 1 - items.length] = null;
        }
        nextFirst += 1;
        size -= 1;
        return a;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && (float)size / items.length < 0.5) {
            resizeDown((int)Math.ceil(items.length / 2));
        }
        T b = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return b;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        }
        return items[nextFirst + 1 + index - items.length];
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> C) {
        if (size == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < size; i ++) {
            if (C.compare(get(maxIndex), get(i)) < 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
