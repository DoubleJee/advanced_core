package basic_core.genericity.method;

import java.util.ArrayList;
import java.util.List;

public class GenericMethod {

    public static void main(String[] args) {
        add(1, 2f);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(30);
        checkList(integerList, 30);
        checkNumber(integerList, 30);


        List<String> strings = new ArrayList<>();
        strings.add("love");
//        checkNumber(strings, 10);
        checkList(strings, "love");

    }


    // 接收Number类型的泛型计算方法
    public static <N extends Number> double add(N a, N b) {
        double sum = 0;
        sum = a.doubleValue() + b.doubleValue();
        return sum;
    }


    // 检查一个未知类型的List里，是否包含某种类型对象     （使用通配符?，代表未知类型）
    public static <T> void checkList(List<?> myList, T obj) {
        if (myList.contains(obj)) {
            System.out.println("这个集合包含这个元素" + obj);
        } else {
            System.out.println("这个集合不包含这个元素" + obj);
        }
    }

    // 检查一个数字类型的List里，是否包含某种类型对象     （使用有界上下限，来限制通配符?的未知类型范围）
    public static <T> void checkNumber(List<? extends Number> myList, T obj) {
        if (myList.contains(obj)) {
            System.out.println("这个集合包含这个元素" + obj);
        } else {
            System.out.println("这个集合不包含这个元素" + obj);
        }
    }



}
