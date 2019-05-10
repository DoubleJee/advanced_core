package datastructure.linear.array;

public class ArrayList<E> {
    /**
     * 大小
     */
    private int size;
    /**
     * 元素
     */
    private Object[] elements;
    /**
     * 默认容量
     */
    private static int DEFAULT_CAPACITY = 10;
    /**
     * 异常下标
     */
    private static int EXCEPTION_NOT_FIND = -1;

    public ArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY)
            capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != EXCEPTION_NOT_FIND;
    }

    public void add(E element) {

    }

    public void add(int index, E element) {
        return;
    }

    public E get(int index) {
        if (index <= 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
        }
        return (E) elements[index];
    }

    public E set(int index, E element) {
        if (index <= 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
        }
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    public E remove(int index) {
        return null;
    }

    //时间复杂度 O(n)
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) return i;
        }
        return EXCEPTION_NOT_FIND;
    }

    public void clear() {
        size = 0;
    }

}
