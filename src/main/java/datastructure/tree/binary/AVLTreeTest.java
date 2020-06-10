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

        BinaryTrees.println(avlTree);
    }
}
