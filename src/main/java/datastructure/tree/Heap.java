package datastructure.tree;

// 堆接口
public interface Heap<E> {

    /**
     * 元素数量
     */
    int size();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void clear();

    /**
     * 添加元素
     */
    void add(E element);

    /**
     * 获得堆顶元素
     */
    E get();

    /**
     * 删除堆顶元素
     */
    E remove();

    /**
     * 删除堆顶元素，然后插入一个新元素
     */
    E replace(E element);
}
