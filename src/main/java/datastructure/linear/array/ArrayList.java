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

    private void indexException(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            indexException(index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            indexException(index);
        }
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
        add(size, element);
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    public E remove(int index) {
        checkIndex(index);
        E old = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return old;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size:").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(i != 0){
                stringBuilder.append(",");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
