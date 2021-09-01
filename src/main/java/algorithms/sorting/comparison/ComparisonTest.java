package algorithms.sorting.comparison;

import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;

import java.util.Arrays;

public class ComparisonTest {

    public static void main(String[] args) {
        Integer[] random = Integers.random(10000, 1, 20000);
        testSorts(random,
                new BubbleSort<>(),
                new SelectionSort<>(),
                new HeapSort<>(),
                new InsertionSort<>(),
                new MergeSort<>()
        );
    }

    static void testSorts(Integer[] array, Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            AssertTool.test(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (Sort<Integer> sort : sorts) {
            System.out.println(sort);
        }
    }
}
