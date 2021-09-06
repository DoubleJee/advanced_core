package algorithms.sorting.comparison;

import algorithms.sorting.Sort;
import algorithms.sorting.comparison.util.Integers;
import datastructure.AssertTool;
import datastructure.TimeTool;

// 堆排序
public class HeapSort<E> extends Sort<E> {
    // 堆大小
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;

        // 原地建堆
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }

        // 小于等于1，就不需要再比较找最大了
        while (heapSize > 1) {
            // 交换堆顶和末尾元素位置
            swap(0, heapSize - 1);

            // 堆大小--
            heapSize--;

            // 新交换的堆顶0下滤，下滤比较后，堆顶重新变为最大的（恢复堆的性质）
            siftDown(0);
        }

    }

    private void siftDown(int index) {
        E element = array[index];
        int leafStartIndex = heapSize / 2;

        while (index < leafStartIndex) {
            int childIndex = index * 2 + 1;
            E child = array[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize && cmp(childIndex, rightIndex) < 0) {
                childIndex = rightIndex;
                child = array[rightIndex];
            }

            if (cmp(element, child) >= 0) break;

            array[index] = child;
            index = childIndex;

        }
        array[index] = element;
    }


    public static void main(String[] args) {
        Integer[] random = Integers.random(10000, 1, 20000);
        TimeTool.checkTime("堆排序", () -> new HeapSort<Integer>().sort(random));
        AssertTool.test(Integers.isAscOrder(random));
    }
}
