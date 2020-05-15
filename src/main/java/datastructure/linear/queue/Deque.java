package datastructure.linear.queue;

import datastructure.linear.List;
import datastructure.linear.linked.LinkedList;

// 双端队列
public class Deque<E> {
    List<E> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    // 队头入队
    public void enQueueFront(E element){
        list.add(0,element);
    }

    // 队头出队
    public E deQueueFront(){
        return list.remove(0);
    }

    // 队尾入队
    public void enQueueRear(E element){
        list.add(element);
    }

    // 队尾出队
    public E deQueueRear(){
        return list.remove(size() - 1);
    }

    public E front(){
        return list.get(0);
    }

    public E rear(){
        return list.get(size() - 1);
    }

    public void clear(){
        list.clear();
    }
}
