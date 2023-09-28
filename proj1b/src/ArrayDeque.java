import java.util.ArrayList;
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
//        T[] a = (T[]) new Object[capacity];
//        if (nextFirst + 1 > capacity - 1) {
//            nextFirst = nextFirst - capacity;
//        } // 若没有此处判断，多次缩减后会导致下方else中a超界
//        for(int i = 0; i < size; i++){
//            if(nextFirst + 1 + i < capacity){
//                a[nextFirst + 1 + i] = items[nextFirst + 1 + i];
//            }
//            else{
//                a[nextFirst + 1 + i - capacity] = items[nextFirst + 1 + i]; // 否则继续赋前面的值 Index 2048 out of bounds for length 2048
//            }
//        }
////        nextFirst = capacity - 1;
//        nextLast = nextFirst + 1 + size - capacity;
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
//        if (nextLast == items.length) {
//            nextLast = 0;
//        }
        items[nextLast] = x;
        size += 1;
        if (nextLast < items.length - 1) {
            nextLast += 1;
        }
        else {
            nextLast = 0;
        }

//        if(nextLast + 1 == items.length){
//            nextLast = 0;
//        }
//        else {
//            nextLast += 1;
//        }

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            if(nextFirst + 1 + i < items.length){
                returnList.add(items[nextFirst + 1 + i]);
            }
            else{
                returnList.add(items[nextFirst + 1 + i - items.length]);
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
//                填满率至少25% resizeDown(items.length / 2 + 1);
                resizeDown((int)Math.ceil(items.length / 2));
            }
        }
        T a;
        if (nextFirst + 1 < items.length) {
            a = items[nextFirst + 1];
            items[nextFirst + 1] = null;
        }
        else {
            a = items[nextFirst + 1 - items.length];
            items[nextFirst + 1 - items.length] = null;
        }
//        nextFirst += 1;
//        size -= 1;
//        return a;
//        T a = items[nextFirst + 1];
//        items[nextFirst + 1] = null;
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
        T b = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return b;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        }
        return items[nextFirst + 1 + index - items.length];
    }
}
