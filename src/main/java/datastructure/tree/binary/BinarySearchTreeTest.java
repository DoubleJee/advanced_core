package datastructure.tree.binary;

import datastructure.tree.Person;
import datastructure.tree.printer.BinaryTrees;

import java.util.Comparator;

public class BinarySearchTreeTest {

    static class DirectPersonComparator implements Comparator<Person> {

        // o1的age大于o2的age 认为o1大
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    static class ReversePersonComparator implements Comparator<Person>{

        // o2的age小于o1的age 认为o1小
        @Override
        public int compare(Person o1, Person o2) {
            return o2.getAge() - o1.getAge();
        }
    }

    static class SimpleVisitor implements BinarySearchTree.Visitor {

        private boolean stop;

        @Override
        public boolean stop() {
            return stop;
        }

        @Override
        public void visit(Object element) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree<Person> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(new Person(20));
        binarySearchTree.add(new Person(9));
        binarySearchTree.add(new Person(30));
        binarySearchTree.add(new Person(44));
        binarySearchTree.add(new Person(10));
        binarySearchTree.add(new Person(18));
        binarySearchTree.add(new Person(35));

        System.out.println(binarySearchTree);
        System.out.println(binarySearchTree.height());
        System.out.println(binarySearchTree.recursionHeight());

        BinaryTrees.println(binarySearchTree);
        System.out.println("前序---------------------------");
        binarySearchTree.preorderTraversal(new SimpleVisitor());
        System.out.println("中序---------------------------");
        binarySearchTree.inorderTraversal(new SimpleVisitor());
        System.out.println("后序---------------------------");
        binarySearchTree.postorderTraversal(new SimpleVisitor());
        System.out.println("层序---------------------------");
        binarySearchTree.levelOrderTraversal(new SimpleVisitor());
        System.out.println("自由接口层序---------------------------");
        binarySearchTree.levelOrderTraversal(new BinarySearchTree.Visitor<Person>() {
            private int size = 0;
            private boolean stop;
            @Override
            public boolean stop() {
                return stop;
            }
            @Override
            public void visit(Person element) {
                if (size < 2){
                    System.out.print(element.getAge() + "_");
                    size++;
                }
                stop = true;
            }
        });
        System.out.println();

        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();

        for (int i = 0; i < 10 ; i++) {
            integerBinarySearchTree.add((int)(Math.random() * 100));
        }

        BinaryTrees.println(integerBinarySearchTree);
        System.out.println("前序---------------------------");
        integerBinarySearchTree.preorderTraversal(new SimpleVisitor());
        System.out.println("中序---------------------------");
        integerBinarySearchTree.inorderTraversal(new SimpleVisitor());
        System.out.println("后序---------------------------");
        integerBinarySearchTree.postorderTraversal(new SimpleVisitor());
        System.out.println("层序---------------------------");
        integerBinarySearchTree.levelOrderTraversal(new SimpleVisitor());

        System.out.println(integerBinarySearchTree);
        System.out.println("高度：" + integerBinarySearchTree.height());
        System.out.println("高度：" + integerBinarySearchTree.recursionHeight());
        System.out.println("根节点前驱" + integerBinarySearchTree.predecessor(integerBinarySearchTree.getRoot()).element);
        System.out.println("根节点后驱" + integerBinarySearchTree.successor(integerBinarySearchTree.getRoot()).element);


        BinarySearchTree<Integer> complete = new BinarySearchTree<>();
        complete.add(7);
        complete.add(4);
        complete.add(9);
        complete.add(2);
        complete.add(5);
        complete.add(8);
//        complete.add(1);
        BinaryTrees.println(complete);
        System.out.println(complete.isComplete());

    }
}
