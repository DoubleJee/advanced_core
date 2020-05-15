package datastructure.linear.queue;

import datastructure.linear.stack.Stack;

public class QueueTest {

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }


        Deque<Integer> deque = new Deque<>();
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        deque.enQueueRear(33);
        deque.enQueueRear(44);

        System.out.println("尾部：" + deque.rear());


        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }


    }


    static class LeeQueueTest<E>{

        Stack<E> inStack = new Stack<>();
        Stack<E> outStack = new Stack<>();

        public LeeQueueTest(){

        }

        public boolean isEmpty(){
            return inStack.isEmpty() && outStack.isEmpty();
        }

        public void push(E element){
            inStack.push(element);
        }

        public E pop(){
            if (outStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        public static void main(String[] args) {
            LeeQueueTest queue = new LeeQueueTest();
            queue.push(11);
            queue.push(22);
            queue.push(33);
            queue.push(44);
            while (!queue.isEmpty()){
                System.out.println(queue.pop());
            }
        }
    }
}
