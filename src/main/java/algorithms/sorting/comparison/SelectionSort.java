package algorithms.sorting.comparison;

import algorithms.sorting.comparison.util.Integers;
import datastructure.TimeTool;

// 选择排序
public class SelectionSort {


    static void selectionSort(Integer [] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // 默认0是最大的
            int maxIndex = 0;
            // 从头到尾开始比较
            for (int begin = 1; begin <= end; begin++) {
                // 找最大的，为了保证算法稳定性，相等的两个元素，在后面的属于大的
                if (array[begin] >= array[maxIndex]) {
                    maxIndex = begin;
                }
            }

            // 将选择的最大的，与当前比较数中的末尾交换位置
            int tmp = array[end];
            array[end] = array[maxIndex];
            array[maxIndex] = tmp;
            // end-- 进入下一轮
        }
    }

    public static void main(String[] args) {

        Integer[] random = Integers.random(10000, 1, 20000);
        TimeTool.checkTime("选择排序", () -> selectionSort(Integers.copy(random)));
        TimeTool.checkTime("冒泡排序", () -> BubbleSort.basicBubble(Integers.copy(random)));

    }
}
