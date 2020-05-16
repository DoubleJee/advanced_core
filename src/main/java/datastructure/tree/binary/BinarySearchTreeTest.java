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


    public static void main(String[] args) {

        BinarySearchTree<Person> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(new Person(20));
        binarySearchTree.add(new Person(9));
        binarySearchTree.add(new Person(30));
        binarySearchTree.add(new Person(44));
        binarySearchTree.add(new Person(10));
        binarySearchTree.add(new Person(18));
        binarySearchTree.add(new Person(35));

        BinaryTrees.println(binarySearchTree);
        System.out.println("前序---------------------------");
        binarySearchTree.preorderTraversal();
        System.out.println("中序---------------------------");
        binarySearchTree.inorderTraversal();
        System.out.println("后序---------------------------");
        binarySearchTree.postorderTraversal();
        System.out.println("层序---------------------------");
        binarySearchTree.levelOrderTraversal();
        System.out.println("自由接口层序---------------------------");
        binarySearchTree.levelOrder(new BinarySearchTree.Visitor<Person>() {
            @Override
            public void visit(Person element) {
                System.out.print(element.getAge() + "_");
            }
        });
        System.out.println();

        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();

        for (int i = 0; i < 10 ; i++) {
            integerBinarySearchTree.add((int)(Math.random() * 100));
        }

        BinaryTrees.println(integerBinarySearchTree);
        System.out.println("前序---------------------------");
        integerBinarySearchTree.preorderTraversal();
        System.out.println("中序---------------------------");
        integerBinarySearchTree.inorderTraversal();
        System.out.println("后序---------------------------");
        integerBinarySearchTree.postorderTraversal();
        System.out.println("层序---------------------------");
        integerBinarySearchTree.levelOrderTraversal();



    }
}
