package datastructure.tree.binary;

import datastructure.tree.printer.BinaryTrees;

import java.util.Scanner;

public class RBTreeTest {


    public static void main(String[] args) {
        RBTree<Integer> integerRBTree = new RBTree<>();
        Integer[] integerArrays = new Integer[]{
                98, 89, 79, 83, 92, 90, 37, 55, 35, 33, 20,82, 45, 23, 47, 50, 68, 21, 71, 67, 81, 93, 56,98, 39, 72, 16, 40, 37, 2, 3, 69, 60, 13, 24, 46, 73, 44, 70, 25
        };
        for (Integer integer : integerArrays){
            integerRBTree.add(integer);
        }
        BinaryTrees.println(integerRBTree);


//        for (Integer integer : integerArrays){
//            integerRBTree.remove(integer);
//            BinaryTrees.println(integerRBTree);
//        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入删除的元素：");
            int removeElement = scanner.nextInt();
            if (removeElement == 8888) break;
            integerRBTree.remove(removeElement);
            BinaryTrees.println(integerRBTree);
        }


    }

}
