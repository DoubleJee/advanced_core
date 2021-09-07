package algorithms.sorting.nocmp;

import algorithms.sorting.Sort;
import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;

// 基数排序 （只针对非负整数排序）
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 找出最大值，以确认位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // max == 593，三位数，要循环3次
        
        // 根据每个位数进行计数排序
        // 个位数：593 / 1 % 10 = 3
        // 十位数：593 / 10 % 10 = 9
        // 百位数：593 / 100 % 10 = 5
        // 千位数：593 / 1000 % 10 = ...

        // 除数变化，求得位数，除数不超过最大数，最大数593只有百位数，就不用求千位数除以1000
        for (int divider = 1; divider <= max ; divider *= 10) {
            countingSort(divider);
        }


    }


    // 计数排序
    private void countingSort(int divider) {
        // 位数都是0~9
        int [] counts = new int[10];

        // 计数
        for (int i = 0; i < array.length; i++) {
            // 通过位数排序，并计数
            counts[array[i] / divider % 10]++;
        }

        // 出现次数累加，以求在有序数组位置
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }


        // 从后向前，稳定放入有序数组
        int [] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            // 根据位数，求得索引，放入有序数组
            sortedArray[--counts[array[i] / divider % 10]] = array[i];
        }

        // 有序数组覆盖原数组
        for (int i = 0; i < sortedArray.length; i++) {
            array[i] = sortedArray[i];
        }

    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{ 7, 3, 5, 8, 6, 7, 4, 5};
        RadixSort radixSort = new RadixSort();
        radixSort.sort(array);
        System.out.println(radixSort);
        AssertTool.test(Integers.isAscOrder(array));
        Integers.println(array);
    }
}
