package algorithms.sorting.comparison;

import java.util.ArrayList;
import java.util.List;

// 希尔排序
public class ShellSort<E> extends Sort<E> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();
        // 根据步长序列，分列，逐列排序
        for (Integer step : stepSequence) {
            sort(step);
        }

    }

    // 生成希尔步长序列 （步长序列可以按照自己的算法来生成，可以改变效率）
    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        // 将数据长度 不断的除以2，直到值为1，得到步长序列
        while ((step /= 2) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 根据步长进行希尔排序
     */
    private void sort(int step){
        // 分为step列，逐列排序
        for (int col = 0; col < step; col++) {
            // 对当前列进行插入排序

            // 每列的元素索引计算公式为 col + (row * step)，第0列第0行 = 0 + (0 * step)，第0列第1行 = 0 + (1 * step)，第0列第2行 = 0 + (2 * step)
            // 依次寻找元素位置，相当于 col、 col + step、 col + step + step，也就是下一个元素每次加一个step步长
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                // 不断抓牌，放到头部有序合适位置
                while (cur > col && cmp(cur - step, cur) > 0){
                    swap(cur - step, cur);
                    cur -= step;
                }
            }

        }
    }
}
