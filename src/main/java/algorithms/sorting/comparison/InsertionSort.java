package algorithms.sorting.comparison;

// 插入排序
public class InsertionSort<E> extends Sort<E> {

//    // 基本
//    @Override
//    protected void sort() {
//        // 从1开始抓牌
//        for (int begin = 1; begin < array.length; begin++) {
//            // 记录当前牌的位置
//            int cur = begin;
//
//            // 跟前面的进行比较，放到合适位置         （如果前面的牌 大于 抓的牌，交换位置，继续寻找合适位置，否则小于，则结束）
//            while (cur > 0 && cmp(cur - 1, cur) > 0){
//                swap(cur - 1, cur);
//
//                // 交换后当前牌位置对应变动
//                cur--;
//            }
//        }
//    }


//    // 优化，将交换变为挪动       （逆数对越多，while循环执行越多次，优化越明显）
//    @Override
//    protected void sort() {
//        for (int begin = 1; begin < array.length; begin++) {
//            int cur = begin;
//            // 备份当前牌的值
//            E value = array[cur];
//            // 跟前面的进行比较，最后找到最终位置
//            while (cur > 0 && cmp(array[cur - 1], value) > 0){
//                // 将前面元素挪动到后面
//                array[cur] = array[cur - 1];
//                cur--;
//            }
//            // 找到最后正确的位置，替换赋值
//            array[cur] = value;
//        }
//    }

    /**
     * 优化，使用二分搜索来查找正确插入位置，变成一次性挪动
     *
     * 复杂度依然是O(n^2)，虽然优化了查找为O(logn)，但是挪动依然是O(n)
     */
    @Override
    public void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            // 备份当前牌的值
            E value = array[begin];
            // 二分查找，头部待插入位置
            int insertIndex = searchInsertIndex(begin);

            // 依次向后挪动
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }

            // 最后插入指定位置
            array[insertIndex] = value;
        }
    }


    // 使用二分查找，查找头部正确插入位置
    private int searchInsertIndex(int index){
        int begin = 0;
        int end = index;

        while (begin < end) {
            // 中间位置
            int mid = (begin + end) / 2;

            if (cmp(index, mid) < 0) {
                // 往左边找第一个大于index元素的位置
                end = mid;
            } else {
                // 大于等于 的话，往右边找第一个大于index元素的位置
                begin = mid + 1;
            }
        }

        // 最后的正确位置
        return begin;
    }
}
