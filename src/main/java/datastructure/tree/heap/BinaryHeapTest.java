package datastructure.tree.heap;

import datastructure.tree.printer.BinaryTrees;

import java.util.Comparator;

public class BinaryHeapTest {

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.replace(70);
        BinaryTrees.println(heap);


        // 批量建堆
        Integer[] integers = {69, 79, 22, 26, 82, 54, 81, 41, 34, 62, 73, 23, 20, 53, 70};
        BinaryHeap<Integer> heapify = new BinaryHeap<>(integers);
        BinaryTrees.println(heapify);

        integers[0] = 100;
        BinaryTrees.println(heapify);


        // 通过修改比较器，让最大堆将值比较小的放在最顶部
        // 从而变成最小堆
        Comparator<Integer> smallComparator = (v1, v2) -> v2 - v1;
        BinaryHeap<Integer> smallHeap = new BinaryHeap<>(smallComparator);
        smallHeap.add(68);
        smallHeap.add(72);
        smallHeap.add(43);
        smallHeap.add(50);
        smallHeap.add(38);
        smallHeap.add(10);
        smallHeap.add(90);
        smallHeap.add(65);
        BinaryTrees.println(smallHeap);

    }
}
