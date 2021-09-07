package algorithms.sorting.nocmp;

import algorithms.sorting.Sort;

import java.util.LinkedList;
import java.util.List;

// 桶排序 （只对小数类型）
public class BucketSort extends Sort<Double> {
    @Override
    protected void sort() {
        // 创建和无序序列一样长度的有序桶
        List<Double>[] buckets = new List[array.length];

        // 将数据均匀分配到有序桶
        for (int i = 0; i < array.length; i++) {
            // 分配规则是，每个小数 * 桶长度（元素数量），得到它的所放入的桶，小数越大的乘以后，放的越后，越小的，算出来的桶索引越小
            int bucketIndex = array[i].intValue() * buckets.length;
            List<Double> bucket = buckets[bucketIndex];

            // 初始化桶
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }
            // 放入桶（一样的数，先遍历到的放在前面，后遍历到的放在后面，稳定性）
            bucket.add(array[i]);
        }


        for (int i = 0; i < buckets.length; i++) {
            // 排除空桶
            if (buckets[i] == null) continue;

            // 单独对桶进行排序（jdk自带的应该是快速排序或者其他）
            buckets[i].sort(null);

            // 依次合并桶里元素，为有序序列
            for (Double d : buckets[i]) {
                array[i++] = d;
            }
        }
    }


    public static void main(String[] args) {
        Double[] array = new Double[]{ 0.34, 0.47, 0.29, 0.84, 0.45, 0.38, 0.29, 0.76};
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(array);
        System.out.println(bucketSort);

        for (Double d : array) {
            System.out.print(d + " ");
        }
    }
}
