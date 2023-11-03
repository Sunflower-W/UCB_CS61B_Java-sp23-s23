package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst; // if =4, Private field 'nextFirst' is assigned but never accessed
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
//        items = (T[]) new T<>[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addLast(2);
        ad.addLast(3);
        ad.addFirst(1);
        ad.addLast(4);
        ad.addLast(5);
        ad.addFirst(0);
        ad.addLast(6);
        ad.addLast(7);
        ad.addLast(8);
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for(int i = 0; i < size; i++){ // 循环条件：i小于ArrayDeque的size
            if(nextFirst + 1 + i < size){ // 如果ArrayDeque的前空位+1即第1位+i小于ArrayDeque的size（最大size-1位）
                a[nextFirst + 1 + i] = items[nextFirst + 1 + i]; // 新组a相同位置赋item相同值
            }
            else{
                a[nextFirst + 1 + i] = items[nextFirst + 1 + i - size]; // 否则继续赋前面的值
            }
        }
        nextLast = nextFirst + size + 1;
        items = a;
    }

    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
//        nextFirst = capacity;
        for (int i = 0; i < size; i ++) {
            if(nextFirst + 1 + i < items.length) {
                a[i] = items[nextFirst + 1 + i];
            }
            else {
                a[i] = items[nextFirst + 1 + i - items.length];
            }
        }
        nextFirst = size;
        items = a;
    }
    @Override
    public void addFirst(T x) {
        if(size == items.length){
            resize(size * 2);
        }
        if(nextFirst < 0){
            nextFirst = items.length - 1;
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if(size == items.length){
            resize(size * 2);
        }
        if(nextLast == items.length){
            nextLast = 0;
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            if(nextFirst + 1 + i < items.length){
                returnList.add(items[nextFirst + 1 + i]);
            }
            else{
                returnList.add(items[nextFirst + 1 + i - size]);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16){
            if ((float)size/ items.length < 0.5) {
                resizeDown(items.length / 2 + 1);
            }
        }

        T a;
        if (nextFirst == items.length - 1) {
            a = items[0];
            items[0] = null;
            nextFirst = -1; // 循环外还要+1，回到容器首部标位0
        } else {
            a = items[nextFirst + 1];
            items[nextFirst + 1] = null;
        }
        nextFirst += 1;
        size -= 1;
        return a;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16){
            if (size/ items.length < 0.5) {
                resizeDown(items.length / 2 + 1);
            }
        }

        T b;
        if (nextLast == 0) {
            b = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            b = items[nextLast - 1];
            items[nextLast - 1] = null;
        }
        nextLast -= 1;
        size -= 1;
        return b;
    }

    @Override
    public T get(int index) {
        T c;
        if (index >= size || index < 0){
            return null;
        }
        else{
            if (nextFirst + 1 + index < items.length) {
                c = items[nextFirst + 1 + index];
            } else {
                c = items[nextFirst + 1 + index - items.length];
            }
            return c;
        }
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equal(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque oas) {
            if (this.size != oas.size) {
                return false;
            }
            for (T x : this) {
                if (!oas.toList().contains(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() { // 考虑容量扩大后值所在处的序号
        if (size == 0) {return "[]";}
        StringBuilder stringSB = new StringBuilder("[");
        if (size == 1) {stringSB.append(items[nextFirst + 1]);}
        else {
            for (int i = 0; i < size - 1; i++) { //加到倒数第二位，加","，最后一位单独加，在加"]"
                stringSB.append(items[nextFirst + 1 + i]); // 这里无需判断索引是否小于items的length，因为扩大两倍时，粘贴到新对象中不会被断开
                // 为什么这里不可以用this.get() this是【9, ...3, null, null, null, null】
                stringSB.append(", ");
            }
        }
        stringSB.append(items[nextLast - 1]);
        stringSB.append("]");
        return stringSB.toString();
//        return this.toList().toString();
    }
}
