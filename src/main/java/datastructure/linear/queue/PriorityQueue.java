package datastructure.linear.queue;

import datastructure.tree.heap.BinaryHeap;

import java.util.Comparator;

// 优先级队列
public class PriorityQueue<E> {

    // 底层使用最大堆来实现优先级队列
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void enQueue(E element){
        heap.add(element);
    }

    public E deQueue(){
        return heap.remove();
    }

    public E front(){
        return heap.get();
    }

    public void clear(){
        heap.clear();
    }

}
