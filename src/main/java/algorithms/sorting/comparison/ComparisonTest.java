package algorithms.sorting.comparison;

import algorithms.sorting.comparison.util.Integers;

import java.util.Arrays;

public class ComparisonTest {

    public static void main(String[] args) {
        Integer[] random = Integers.random(10000, 1, 20000);
        testSorts(random,
                new BubbleSort<>(),
                new SelectionSort<>(),
                new HeapSort<>()
        );
    }

    static void testSorts(Integer[] array, Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            sort.sort(Integers.copy(array));
        }

        Arrays.sort(sorts);

        for (Sort<Integer> sort : sorts) {
            System.out.println(sort);
        }
    }
}
