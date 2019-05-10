package datastructure.linear;

import datastructure.linear.array.ArrayList;

public class LinearTest {


    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.set(0, 1);
        Integer integer = integerArrayList.get(0);
        System.out.println(integer);

    }
}
