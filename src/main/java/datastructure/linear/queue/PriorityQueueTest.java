package datastructure.linear.queue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
        priorityQueue.enQueue(new Person("小明", 177));
        priorityQueue.enQueue(new Person("小王", 185));
        priorityQueue.enQueue(new Person("小红", 165));
        priorityQueue.enQueue(new Person("小丽", 175));

        System.out.println("身高越高的人，越适合这个岗位");
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.deQueue());
        }

    }
}
