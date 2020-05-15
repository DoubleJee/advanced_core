package datastructure.linear.array;

import datastructure.linear.AbstractList;
import datastructure.linear.List;

public class ArrayList<E> extends AbstractList<E> implements List<E> {
    /**
     * 元素
     */
    private Object[] elements;
    /**
     * 默认容量
     */
    private static int DEFAULT_CAPACITY = 10;

    public ArrayList(int capacity) {
        //初始化数组
        if (capacity < DEFAULT_CAPACITY)
            capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    public void add(int index, E element) {
        //检查下标是否有效
        checkIndexForAdd(index);
        //检查容量是否充足，以及动态扩容
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    // 时间复杂度是O(1)
    public E get(int index) {
        checkIndex(index);

        // 编译器会计算好位置，假设存储的是int类型，一个int 4字节，每个元素相隔4字节，计算公式：数组首地址 + (index * 4)
        return (E) elements[index];
    }

    // 时间复杂度是O(1)
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }


    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    public E remove(int index) {
        checkIndex(index);
        E old = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        //移除最后一位对象引用，否则前一位删除的话，将造成内存泄漏
        elements[--size] = null;

        // 缩容
        trimCapacity();
        return old;
    }

    //时间复杂度 O(n)
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return EXCEPTION_NOT_FIND;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            //数组存储的对象，不被引用，将会被GC回收
            elements[i] = null;
        }
        size = 0;

        //缩容
        if (elements == null && elements.length > DEFAULT_CAPACITY){
            elements = new Object[DEFAULT_CAPACITY];
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size:").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }


    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
//        System.out.println(oldCapacity + "扩容" + newCapacity);
    }


    private void trimCapacity(){
        int capacity = elements.length;
        int newCapacity = capacity / 2;
        // 元素大于容量的一半，或者容量小于默认容量了，都不进行缩容
        if (size >= newCapacity || capacity <= DEFAULT_CAPACITY){
            return;
        }
        // 缩容
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
//        System.out.println(capacity + "缩容" + newCapacity);
    }

}
