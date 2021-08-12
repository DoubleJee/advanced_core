package datastructure.tree.heap;

import datastructure.tree.Heap;

import java.util.Comparator;

@SuppressWarnings("unchecked")
// 抽象堆，描述公共属性、逻辑
public abstract class AbstractHeap<E> implements Heap<E> {

    // 大小
    protected int size;

    // 比较器
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 比较
    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

}
