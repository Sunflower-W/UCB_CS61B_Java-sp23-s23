import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public class Node { // 构造嵌套类表示节点
        public Node prev;
        public T item;
        public Node next;

        public Node(Node m, T i, Node n){
            prev = m;
            item = i;
            next = n;
        }
    }
    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /* Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
//        sentinel = new Node(sentinel, null, sentinel);
        sentinel = new Node(null, null, null);
        size = 0;
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
//        lld.addFirst(10);
        lld.addLast(100);
    }

    @Override
    public void addFirst(T x) {
        Node p = sentinel;
        Node X = new Node(sentinel, x, sentinel);
        if(p.next == null) { //空
            p.next = X;
            p.prev = p.next;
        }
        else {
            X.next = p.next;//不空 ! 前面的步骤不能影响后面的步骤，即前面的步骤不能改变后面代码所需要使用的内容
            p.next.prev = X;
            p.next = X;
        }
        size += 1;
        toList();
    }

    @Override
    public void addLast(T x) {
        Node p = sentinel;
        Node X = new Node(sentinel, x, sentinel);
        if(p.next == null) {
            p.prev = X;
            p.next = p.prev;
        }
        else {
            X.prev = p.prev;
            p.prev.next = X;
            p.prev = X;
        }
        size += 1;
        toList();
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
//        System.arraycopy(sentinel, 0, returnList, 0, size); // ArrayStoreException: arraycopy: source type LinkedListDeque$Node is not an array
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            returnList.add(p.next.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(sentinel.next == null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node p = sentinel;
        if(p == null) {
            return null;
        }
        else {
            Node X = p.next;
            p.next = X.next;
            X.next.prev = p;
            X.prev = null;
            X.next = null;
            size -= 1;
            return X.item;
        }
    }

    @Override
    public T removeLast() {
        Node p = sentinel;
        if(p == null) {
            return null;
        }
        else {
            Node X = p.prev;
            p.prev = X.prev;
            X.prev.next = p;
            X.prev = null;
            X.next = null;
            size -= 1;
            return X.item;
        }
    }

    @Override
    public T get(int index) { // 从0开始计数
        if((index < 0) || (index >= size) || (size == 0)) {
            return null;
        }
        else {
            Node p = sentinel;
            for(int i = 0; i <= index; i++) {
                    p = p.next;
                }
            return p.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if(index == 0) {
            return sentinel.next.item;
        }
        return getRecursive(index, sentinel); // ! 通过传递参数（实现递归）
    }

    private T getRecursive(int index, Node node) {
        if ((index < 0) || (index >= size) || (size == 0)) {
            return null;
        }
        else {
            return getRecursive(index - 1, node.next); // ！（通过传递参数）实现递归
        }
    }
}
