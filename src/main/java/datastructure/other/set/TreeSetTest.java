package datastructure.other.set;

import datastructure.tree.BinaryTree;

public class TreeSetTest {

    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();

        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.add(4);
        treeSet.add(44);
        treeSet.add(33);

        treeSet.traversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean stop() {
                return false;
            }

            @Override
            public void visit(Integer element) {
                System.out.println(element);
            }
        });
    }
}
