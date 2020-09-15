package datastructure.linear.set;

import datastructure.tree.BinaryTree;

public class ListSetTest {

    public static void main(String[] args) {
        Set<Integer> listSet = new ListSet<>();

        listSet.add(1);
        listSet.add(1);
        listSet.add(3);
        listSet.add(3);
        listSet.add(4);
        listSet.add(5);
        listSet.add(11);
        listSet.add(1);

        listSet.traversal(new BinaryTree.Visitor<Integer>() {
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
