package algorithms.sorting.comparison.old;

import algorithms.sorting.comparison.util.Integers;

// 堆排序
public class HeapSort {

    static void heapSort(Integer [] array){
        int heapSize = array.length;

        // 原地建堆
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            siftDown(i, heapSize, array);
        }


        // 小于等于1，就不需要再比较找最大了
        while (heapSize > 1) {
            // 交换堆顶和末尾元素位置
            int tmp = array[heapSize - 1];
            array[heapSize - 1] = array[0];
            array[0] = tmp;

            // 堆大小--
            heapSize--;

            // 新交换的堆顶0下滤，下滤比较后，堆顶重新变为最大的
            siftDown(0, heapSize, array);
        }



    }

    static void siftDown(int index, int size, Integer[] array) {
        Integer element = array[index];
        int leafStartIndex = size / 2;

        while (index < leafStartIndex) {
            int childIndex = index * 2 + 1;
            Integer child = array[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < size && (child - array[rightIndex]) < 0) {
                childIndex = rightIndex;
                child = array[rightIndex];
            }

            if (element - child >= 0) break;

            array[index] = child;
            index = childIndex;

        }
        array[index] = element;
    }

    public static void main(String[] args) {
        Integer[] random = Integers.random(10, 1, 100);
        heapSort(random);
        Integers.println(random);

    }
}
