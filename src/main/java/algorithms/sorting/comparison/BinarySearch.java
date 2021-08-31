package algorithms.sorting.comparison;

import datastructure.AssertTool;

// 二分搜索
public class BinarySearch {

    public static int indexOf(Integer[] array, Integer v) {
        if (array == null || array.length == 0) return -1;

        int begin = 0;
        int end = array.length;
        // 左闭右开，end - begin = 元素数量

        // begin 必须小于 end，范围内才有元素可以找，如果begin == end，则代表在边界外了，没有元素可查找了
        while (begin < end) {
            // 找到中间元素，(begin + end) / 2
            int mid = (begin + end) >> 1;

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


    public static void main(String[] args) {
        Integer [] array = { 2, 4, 6, 8, 10 };
        AssertTool.test(indexOf(array, 2) == 0);
        AssertTool.test(indexOf(array, 6) == 2);
        AssertTool.test(indexOf(array, 10) == 4);
        AssertTool.test(indexOf(array, 9) == -1);

    }
}
