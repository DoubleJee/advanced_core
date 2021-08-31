package algorithms.sorting.comparison;

import datastructure.AssertTool;

// 二分搜索
public class BinarySearch {

    /**
     * 搜索元素v在数组中的索引
     */
    public static int indexOf(Integer[] array, Integer v) {
        if (array == null || array.length == 0) return -1;

        int begin = 0;
        int end = array.length;
        // 左闭右开，end - begin = 元素数量

        // begin 必须小于 end，范围内才有元素可以找，如果begin == end，则代表在边界外了，没有元素可查找了
        while (begin < end) {
            // 找到中间位置，(begin + end) / 2
            int mid = (begin + end) / 2;

            if (v < array[mid]) {
                // 左边范围搜索，[begin, mid)
                end = mid;
            } else if (v > array[mid]) {
                // 右边范围搜索，[mid + 1, end]
                begin = mid + 1;
            } else {
                // 找到元素，返回位置
                return mid;
            }
        }

        // 找不到返回 -1 位置
        return -1;
    }


    /**
     * 查找元素v在数组中的插入位置
     */
    public static int search(Integer [] array, Integer v) {
        if (array == null || array.length == 0) return -1;

        // 寻找第一个大于v的元素位置
        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;

            if (v < array[mid]) {
                end = mid;
            } else if (v >= array[mid]) {
                // 为了寻找第一个大于v的位置，等于的情况下，也往右边搜索
                begin = mid + 1;
            }
        }

        // 最后begin 和 end 重叠一样的位置就是第一个大于v的元素位置
        return begin;
    }


    public static void main(String[] args) {
        Integer [] array = { 2, 4, 6, 8, 8, 10 };
        AssertTool.test(indexOf(array, 2) == 0);
        AssertTool.test(indexOf(array, 6) == 2);
        AssertTool.test(indexOf(array, 10) == 5);
        AssertTool.test(indexOf(array, 9) == -1);


        AssertTool.test(search(array, 8) == 5);
        AssertTool.test(search(array, 5) == 2);
        AssertTool.test(search(array, 1) == 0);
        AssertTool.test(search(array, 11) == 6);


    }
}
