package datastructure.linear;

import datastructure.linear.array.ArrayList;

public class LinearTest {


    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(99);
        integerArrayList.add(88);
        integerArrayList.add(77);
        integerArrayList.add(66);
        integerArrayList.add(55);
        integerArrayList.add(4,899);
        System.out.println(integerArrayList.set(5,66));
        System.out.println(integerArrayList);
    }
}
