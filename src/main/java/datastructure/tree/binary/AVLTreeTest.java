package datastructure.tree.binary;

import datastructure.tree.Person;
import datastructure.tree.printer.BinaryTrees;

public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree<Person> avlTree = new AVLTree<>(new BinarySearchTreeTest.DirectPersonComparator());
        avlTree.add(new Person(20));
        avlTree.add(new Person(9));
        avlTree.add(new Person(30));
        avlTree.add(new Person(44));
        avlTree.add(new Person(10));
        avlTree.add(new Person(18));
        avlTree.add(new Person(35));

        BinaryTrees.println(avlTree);
    }
}
