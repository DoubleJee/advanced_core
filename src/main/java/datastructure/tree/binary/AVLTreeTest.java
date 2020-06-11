package datastructure.tree.binary;

import datastructure.tree.Person;
import datastructure.tree.printer.BinaryTrees;

public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree<Person> avlTree = new AVLTree<>(new BinarySearchTreeTest.DirectPersonComparator());
        avlTree.add(new Person(1));
        avlTree.add(new Person(2));
        avlTree.add(new Person(3));
        avlTree.add(new Person(4));
        avlTree.add(new Person(5));
        avlTree.add(new Person(6));
        avlTree.add(new Person(7));
        avlTree.add(new Person(8));
        avlTree.add(new Person(9));
        avlTree.add(new Person(10));
        avlTree.add(new Person(11));
        avlTree.add(new Person(12));
        avlTree.add(new Person(13));

//        avlTree.add(new Person(13));
//        avlTree.add(new Person(12));
//        avlTree.add(new Person(11));
//        avlTree.add(new Person(10));
//        avlTree.add(new Person(9));
//        avlTree.add(new Person(8));
//        avlTree.add(new Person(7));
//        avlTree.add(new Person(6));
//        avlTree.add(new Person(5));
//        avlTree.add(new Person(4));
//        avlTree.add(new Person(3));
//        avlTree.add(new Person(2));
//        avlTree.add(new Person(1));

        BinaryTrees.println(avlTree);

        /**
         * 38, 93, 48, 42, 2, 51, 83, 67, 78, 3, 69, 95, 63, 25, 85, 43, 30
         */

        AVLTree<Integer> integerAVLTree = new AVLTree<>();
        Integer[] integerArrays = new Integer[]{
                38, 93, 48, 42, 2, 51, 83, 67, 78, 3, 69, 95, 63, 25, 85, 43, 30
        };
        for (Integer integer : integerArrays){
            integerAVLTree.add(integer);
        }

        BinaryTrees.println(integerAVLTree);
    }
}
