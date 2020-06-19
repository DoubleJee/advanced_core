package datastructure.tree.binary;

import datastructure.tree.printer.BinaryTrees;

public class RBTreeTest {


    public static void main(String[] args) {
        RBTree<Integer> integerRBTree = new RBTree<>();
        Integer[] integerArrays = new Integer[]{
                84, 71, 7, 53, 60, 34, 87, 42, 70, 85, 75,88, 2, 18, 69, 17, 67, 55, 64, 82, 32, 27, 46, 84, 12, 9, 45, 62, 40, 46, 75, 33, 66, 96, 73, 82, 65, 5, 51, 17, 94, 35, 10, 2, 1, 77, 8, 11,
        };
        for (Integer integer : integerArrays){
            integerRBTree.add(integer);
        }
        BinaryTrees.println(integerRBTree);


//        for (Integer integer : integerArrays){
//            integerRBTree.remove(integer);
//            BinaryTrees.println(integerRBTree);
//        }
    }

}
