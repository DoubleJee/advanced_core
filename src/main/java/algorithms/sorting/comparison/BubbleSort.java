package algorithms.sorting.comparison;


import algorithms.sorting.Sort;
import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;
import datastructure.TimeTool;


// 冒泡排序
public class BubbleSort<E> extends Sort<E> {

    /**
     * 冒泡排序
     *
     * 时间复杂度：最好：O(n); 最坏、平均：O(n^2)
     *
     * 空间复杂度：O(1)
     *
     * 稳定算法
     * 因为两个数比较，相等的时候不会交换他们位置，相对位置没有变，先出现的在后出现的前面
     * 如果将冒泡算法改成 <= 前面的元素，交换位置，那会变成不稳定算法
     *
     * 原地算法
     *
     */

    public static void main(String[] args) {
        Integer[] random = Integers.random(10000, 1, 20000);
        TimeTool.checkTime("基础冒泡", () -> new BubbleSort<Integer>().sort(random));
        AssertTool.test(Integers.isAscOrder(random));
    }

    // 基础冒泡
    public void basicBubble() {
        // 最开始从头比较到尾
        for (int end = array.length - 1; end > 0; end--) {
            // 每次比，都会把 最大的数 放到比较数中的最后一个位置
            for (int begin = 1; begin <= end; begin++) {
                // 如果当前数小于前面的数，交换位置
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
            // 每次end--，下次比较，忽略上次的最大位置   （它不需要比较，已经是最大的并且放在最后面了）
        }
    }


    // 完全有序冒泡优化，发现已经提前完全有序就不会再继续排序下去了    （小小的优化，在已经完全有序的情况下有用，避免了不必要的排序）
    public void breakShortBubble() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }

            // 一次交换都没有进行，说明已经有序
            if (sorted) break;
        }
    }


    // 尾部有序冒泡优化，发现尾部已经有序，下次循环比较排序的时候直接忽略它们，同时也保证了完全有序的优化    （记录最后一次交换位置，代表已经有序的尾部）
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1; // 默认给1，如果一次交换都没有，最后end--，就可以提前退出（在完全有序的时候有用）

            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    // 记录最后一次交换位置
                    sortedIndex = begin;
                }
            }

            // 最后一次交换位置后的元素都是排好序的，下次扫描时忽略他们
            end = sortedIndex;
        }
    }


}
