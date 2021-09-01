package algorithms.sorting.comparison;

// 归并排序
public class MergeSort<E> extends Sort<E> {

    private E[] leftArray;

    @Override
    protected void sort() {

        // 用来备份最大序列的一半，以防止合并过程中，被覆盖（一半长度是按照分割过程的公式计算 = (begin + end) / 2 = (array.length + 0) / 2）
        leftArray = (E [])new Object[array.length / 2];
        // 归并排序整个数组
        sort(0, array.length);
    }


    /**
     * 将指定范围进行归并排序 [begin, end)
     */
    private void sort(int begin, int end) {
        // 如果这个范围就一个元素，则不需要归并排序，已经有序
        if (end - begin < 2) return;

        // 找到中间位置， 将它们分割分开成两个部分，去归并排序
        int mid = (begin + end) / 2;

        // 左边部分
        sort(begin, mid);
        // 右边部分
        sort(mid, end);

        // 以上两个部分归并排序后，再将它们归并排序
        merge(begin, mid, end);
    }


    /**
     * 将两个有序范围合并有序
     */
    private void merge(int begin, int mid, int end) {
        // 左边部分有序序列范围
        int li = 0, le = mid - begin;
        // 右边部分有序序列范围
        int ri = mid, re = end;
        // 要归并到的位置
        int ai = begin;

        // 以防合并过程被覆盖，将左边部分 拷贝到 备份数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }


        // 如果左边先归并结束，则右边不需要动了，因为右边原本就在归并的地方中
        while (li < le) {

            // 将左边和右边的索引元素对比，谁小谁归并，然后小的索引++，大的索引不变，这样进行比较归并


            // 如果右边没结束，并且当前 右边元素 小于 左边元素，右边归并   （右 < 左，保证稳定；如果右边结束了，只需要将左边的依次归并就行了，不需要比较归并）
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                // 右边归并
                array[ai] = array[ri];
                ai++;
                ri++;
            } else {
                // 左边归并
                array[ai] = leftArray[li];
                ai++;
                li++;
            }
        }
    }
}
