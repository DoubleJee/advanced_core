package algorithms.sorting.nocmp;

import algorithms.sorting.Sort;
import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;


// 计数排序  （只针对整数排序）
public class CountingSort extends Sort<Integer> {


    // 计数排序改进优化，保证稳定性，节省内存空间
    @Override
    protected void sort() {
        // 找出最值 （节省内存）
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 创建统计数组   （长度为最大数到最少数之间的 个数）
        int [] counts = new int[max - min + 1];
        // 统计每个整数的出现次数
        for (int i = 0; i < array.length; i++) {
            // 每个整数的值 - 最小值 算出的相对值，作为统计数组的索引，并计数    （节省内存，最小值索引从0开始，每个整数值分到相对索引；元素对应到数组索引，数组索引正好由小到大）
            counts[array[i] - min]++;
        }

        // 将出现次数进行累加，得到每个整数在有序数组中的索引位置
        for (int i = 1; i < counts.length; i++) {
            // 每个整数出现次数 加上 前一个整数的出现次数，就能推算出自己所在有序数组的索引
            // 累加值，代表在自己之前有多个数（自己包含在这个数当中），自己是第几个
            counts[i] += counts[i - 1];
        }

        // 为了保证稳定性，从右往左遍历原数组，确定每个整数在有序数组的索引位置，并且选择挪动过去，而不是直接生成值放进去
        int [] sortedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            // 统计数组存储的出现次数 - 1，得出有序数组索引位置，因为要将它挪动过去，对应维护统计数组正确出现次数，将出现次数--，两步合成一条代码   （次数--，也代表在他前面也包含自己的个数减少了，下次遇到相同整数，求出正确索引）
            int index = --counts[array[i] - min];

            // 挪动过去
            sortedArray[index] = array[i];
        }


        // 最后有序数组覆盖原本数组，完成排序
        for (int i = 0; i < sortedArray.length; i++) {
            array[i] = sortedArray[i];
        }

    }

    // 基本计数排序
    protected void sort0() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // 创建统计数组
        int [] counts = new int[max + 1];
        // 统计每个整数的出现次数
        for (int i = 0; i < array.length; i++) {
            // 每个整数的值 array[i]，作为统计数组的索引，并计数        （元素对应到数组索引，数组索引正好由小到大）
            counts[array[i]]++;
        }

        // 根据整数出现次数进行排序 （索引由小到大的方式帮忙排序）
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            // 出现次数不是0，根据索引值和次数，放入有序数组
            while (counts[i]-- > 0) {
                array[index++] = i;
            }
        }
    }


    public static void main(String[] args) {
        Integer[] array = new Integer[]{ 7, 3, 5, 8, 6, 7, 4, 5};
        CountingSort countingSort = new CountingSort();
        countingSort.sort(array);
        System.out.println(countingSort);
        AssertTool.test(Integers.isAscOrder(array));
        Integers.println(array);
    }
}
