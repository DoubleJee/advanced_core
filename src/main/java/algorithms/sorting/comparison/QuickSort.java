package algorithms.sorting.comparison;

import algorithms.sorting.Sort;

// 快速排序
public class QuickSort<E> extends Sort<E> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }


    /**
     * 将指定范围进行快速排序 [begin, end)
     */
    private void sort(int begin, int end) {
        // 如果这个范围就一个元素，已经是轴点，无需再建轴，已经有序
        if (end - begin < 2) return;

        // 先将此范围构造轴点，然后以轴点位置分割分开成两个部分（小于轴的放左部，大于轴的放右部），再分别去快速排序
        int pid = buildPivot(begin, end);

        // 左边部分
        sort(begin, pid);
        // 右边部分
        sort(pid + 1, end);
    }


    /**
     * 将指定范围构造轴点    （左边放比轴点小的，右边放比轴点大的，返回轴点位置）
     */
    private int buildPivot(int begin, int end) {
        // 优化：随机挑选范围的一个元素跟 第一个元素（begin）交换位置，作为轴点元素，以防止 大量有序数据 导致左右分割不均匀，出现最坏情况
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        // 默认第一个元素（begin）为轴点元素，备份它，已防止构造覆盖的时候丢失    （一开始做冗余位置，每次覆盖冗余位置变化）
        E pivot = array[begin];
        // 从末尾第一个元素开始比较构造
        end--;

        // 依次与轴点元素比较，并放到正确位置，最后begin == end重叠的时候，构造完毕，左右都归到了正确的位置，重叠的位置就是轴点位置（左右相交的位置）
        while (begin < end) {

            // 右边方向
            while (begin < end) {
                // 右边元素 > 轴点元素
                if (cmp(pivot, array[end]) < 0) {
                    // 符合 大于的放在轴点右边，不用动，只需要比较下一个，end--
                    end--;
                } else {
                    // 右边元素 <= 轴点元素，不符合，需要将小的放到轴点左边，左边比对位置++    （优化：等于 也放到对面，是为了左右分割均匀，以防止大量相等的数据导致分割不均匀）
                    array[begin] = array[end];
                    begin++;
                    // 改变扫描方向
                    break;
                }
            }

            // 左边方向
            while (begin < end) {
                // 左边元素 < 轴点元素
                if (cmp(array[begin], pivot) < 0) {
                    // 符合 小于的放在轴点左边，不用动，只需要比较下一个，begin++
                    begin++;
                } else {
                    // 左边元素 >= 轴点元素，不符合，需要将大的放到轴点右边，右边比对位置--    （优化：等于 也放到对面，是为了左右分割均匀，以防止大量相等的数据导致分割不均匀）
                    array[end] = array[begin];
                    end--;
                    // 改变扫描方向
                    break;

                }
            }
        }

        // 轴点元素放入最终正确位置
        array[begin] = pivot;
        // 轴点位置
        return begin;
    }
}
