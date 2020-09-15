package datastructure.other.set;

import datastructure.tree.BinaryTree;

public class MapSetTest {

    public static void main(String[] args) {
        Set<Integer> mapSet = new MapSet<>();

        mapSet.add(1);
        mapSet.add(1);
        mapSet.add(3);
        mapSet.add(3);
        mapSet.add(4);
        mapSet.add(5);
        mapSet.add(11);
        mapSet.add(1);

        mapSet.traversal(new BinaryTree.Visitor<Integer>() {
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
