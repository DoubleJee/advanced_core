package datastructure.tree.heap;

import datastructure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

// 二叉堆（最大堆 ）
@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    private static int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] elements){
        super(comparator);

        // 如果传入的是空数组，就初始化我们默认容量的内部数组
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            // 如果传入的数组长度太小，那使用默认容量
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];

            // 将传入的数组的值copy到内部数组，（以免外面的值更改影响到我们的二叉堆）
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            size = elements.length;

            // 批量建堆
            heapify();
        }
    }

    public BinaryHeap(E[] elements) {
        this(null, elements);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(comparator, null);
    }

    public BinaryHeap() {
        this(null, null);
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        // 空元素检查
        elementNotNullCheck(element);
        // 扩容与否
        ensureCapacity(size + 1);

        elements[size++] = element;

        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        // 用最后一个节点覆盖根节点
        elements[0] = elements[size - 1];
        // 删除最后一个节点
        elements[--size] = null;

        // 下滤 根节点
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            // 单纯的新增，没有可删替换的堆顶元素
            elements[0] = element;
            size++;
        } else {
            // 新值替换根节点
            elements[0] = element;
            // 下滤 根节点
            siftDown(0);

            root = element;
        }

        return root;
    }

    // 空检查
    private void emptyCheck(){
        if (size == 0) throw new IndexOutOfBoundsException("Heap is empty.");
    }

    // 空元素检查
    private void elementNotNullCheck(E element){
        if (element == null) throw new IllegalArgumentException("elements must not be null");
    }

    // 扩容
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 上滤      Node为index索引的节点，让它上滤
     *
     * 最大堆 Node > 父节点，与父节点交换位置，然后继续， Node <= 父节点，或者Node没有父节点，结束
     */
    private void siftUp(int index) {
        E element = elements[index];
        // index为0，是根节点，则没有父节点，结束
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            E parent = elements[parentIndex];
            // 如果Node <= 父节点，结束
            if (compare(element, parent) <= 0) break;

            // Node > 父节点，将父元素放到子节点位置，自己上滤
            elements[index] = parent;

            index = parentIndex;
        }

        // 确定最后索引节点位置，放入元素值
        elements[index] = element;

    }


    /**
     * 下滤      Node为index索引的节点，让它下滤
     * 最大堆 Node < 子节点（最大子节点），与最大子节点交换位置，然后继续，Node >= 子节点（最大子节点），或者Node没有子节点，也就是叶子节点，结束
     */
    private void siftDown(int index) {
        E element = elements[index];

        // 数组索引为 非叶子节点总数 开始的节点都是叶子节点，完全二叉树非叶子节点个数计算公式: floor(n / 2)，只要遇到第一个叶子节点，往后都是叶子节点
        int leafStartIndex = size / 2;

        // 不是叶子节点才进行循环，否则结束
        while (index < leafStartIndex) {
            // 最大子节点索引，默认是左子节点，（完全二叉树非叶子节 只有左子节点，或者同时有两个节点，故默认选择左）
            int childIndex = index * 2 + 1;
            E child = elements[childIndex];

            int rightIndex = childIndex + 1;
            // 如果右子节点 大于 左子节点，右子节点是最大子节点
            if (rightIndex < size && compare(child, elements[rightIndex]) < 0) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }

            // Node >= 最大子节点，结束
            if (compare(element, child) >= 0) break;

            // Node < 最大子节点，将最大子节点元素放到当前节点位置，自己下滤
            elements[index] = child;

            index = childIndex;

        }
        elements[index] = element;
    }


    /**
     * 批量建堆        有两种方式
     */
    private void heapify(){
        // 自上而下的上滤 （从索引1开始，根节点不需要再上滤，相当于挨个添加）
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }

        // 自下而上的下滤 （从最后一个非叶子节点开始下滤，叶子节点不需要再下滤，n / 2 - 1 是最后一个非叶子节点索引），效率高

        for (int i = (size / 2 - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 打印要实现的接口
    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = (int) node;
        int left = index * 2 + 1;
        return left > size - 1 ? null : left;
    }

    @Override
    public Object right(Object node) {
        int index = (int) node;
        int right = index * 2 + 2;
        return right > size - 1 ? null : right;
    }

    @Override
    public Object string(Object node) {
        int index = (int) node;
        return elements[index];
    }
}
