package algorithms.sorting.comparison;

import algorithms.sorting.Sort;
import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;
import datastructure.TimeTool;

// 选择排序
public class SelectionSort<E> extends Sort<E> {


    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            // 默认0是最大的
            int maxIndex = 0;
            // 从头到尾开始比较
            for (int begin = 1; begin <= end; begin++) {
                // 找最大的，为了保证算法稳定性，相等的两个元素，在后面的属于大的 （最后发现还是保证不了稳定，例如：[10, 10, 2, 4, 2]）
                if (cmp(begin, maxIndex) >= 0) {
                    maxIndex = begin;
                }
            }

            // 将选择的最大的，与当前比较数中的末尾交换位置
            swap(maxIndex, end);
            // end-- 进入下一轮
        }
    }

    public static void main(String[] args) {
        Integer[] random = Integers.random(10000, 1, 20000);
        TimeTool.checkTime("选择排序", () -> new SelectionSort<Integer>().sort(random));
        AssertTool.test(Integers.isAscOrder(random));
    }
}
