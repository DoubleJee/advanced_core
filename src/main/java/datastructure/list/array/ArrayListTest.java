package datastructure.list.array;

import datastructure.list.Car;
import datastructure.list.List;

public class ArrayListTest {


    public static void main(String[] args) {
        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(99);
        integerArrayList.add(88);
        integerArrayList.add(77);
        integerArrayList.add(66);
        integerArrayList.add(55);
        for (int i = 0; i < 20; i++) {
            integerArrayList.add(i);
        }
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(2, "黄色"));
        carList.add(new Car(4, "红色"));
        carList.add(new Car(6, "银色"));
        carList.add(new Car(8, "金色"));
        carList.add(new Car(9, "豪华金"));
        carList.add(null);
        carList.add(null);
        System.out.println(carList.indexOf(new Car(9, "豪华金")));
        carList.clear();
        System.gc();
        System.out.println(integerArrayList);
        System.out.println(carList);

        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            integerList.add(i);
        }

        for (int i = 0; i < 50; i++) {
            integerList.remove(0);
        }

    }
}
