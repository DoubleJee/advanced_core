package datastructure.tree.heap;

import datastructure.tree.printer.BinaryTrees;

public class TopK {

    public static void main(String[] args) {

        // n个整数
        Integer[] data = {99, 87, 5, 31, 100, 42, 49, 88, 44, 48, 96, 29, 97, 61,
                64, 38, 52, 4, 55, 76, 35, 69, 84, 68, 27, 83, 12, 34, 26, 85, 7,
                86, 81, 19, 39, 9, 47, 95, 24, 74};

        // 最小堆
        BinaryHeap<Integer> heap = new BinaryHeap<>((v1, v2) -> v2 - v1);

        // 在n个数据中，找最大的前k个数
        int k = 5;

        // 扫描 n 个整数
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) {
                // 先放入k个数在小顶堆中
                heap.add(data[i]);  // O(logk)
            } else if (data[i] > heap.get()) {
                // 从k + 1个数开始，如果大于小顶堆的堆顶元素，则删除堆顶元素然后放入这个数
                heap.replace(data[i]); // O(logk)
            }
        }
        // 扫描完毕后，堆中剩下的数就是最大的前k个数
        // 也就是说堆里永远保证k个最大数，每次对比堆顶最小的，如果当前数大于堆顶数，则将这个堆里最小数挤出去，将当前数加入最大k数中
        // 复杂度是O(nlogk)
        System.out.println("最大的" + k + "个数");
        BinaryTrees.println(heap);


        BinaryHeap<Integer> bigHeap = new BinaryHeap<>();

        for (int i = 0; i < data.length; i++) {
            if (bigHeap.size() < k) {
                bigHeap.add(data[i]);
            } else if (data[i] < bigHeap.get()) {
                bigHeap.replace(data[i]);
            }
        }
        System.out.println("最小的" + k + "个数");
        BinaryTrees.println(bigHeap);

    }
}
