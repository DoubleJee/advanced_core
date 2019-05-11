package datastructure.linear;

import datastructure.linear.array.ArrayList;
import datastructure.linear.array.Car;

public class LinearTest {


    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(99);
        integerArrayList.add(88);
        integerArrayList.add(77);
        integerArrayList.add(66);
        integerArrayList.add(55);
        for (int i = 0; i < 20; i++) {
            integerArrayList.add(i);
        }
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Car(2,"黄色"));
        carList.add(new Car(4,"红色"));
        carList.add(new Car(6,"银色"));
        carList.add(new Car(8,"金色"));
        carList.clear();
        System.gc();
        System.out.println(integerArrayList);
        System.out.println(carList);

    }
}
