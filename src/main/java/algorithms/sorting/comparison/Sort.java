package algorithms.sorting.comparison;

import java.text.DecimalFormat;
import java.util.Comparator;

// 排序抽象类
public abstract class Sort<E> implements Comparable<Sort> {

    protected E[] array;

    protected Comparator<E> comparator;

    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E [] array) {
        sort(array, null);
    }

    public void sort(E [] array, Comparator<E> comparator) {
        // 空数组和长度只有1的数组，不需要排序
        if (array == null || array.length < 2) return;
        this.array = array;
        this.comparator = comparator;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    // 子类需要实现的排序
    protected abstract void sort();

    // 比较指定索引元素
    protected int cmp(int idx1, int idx2) {
        E e1 = array[idx1];
        E e2 = array[idx2];
        return cmpElements(e1, e2);
    }

    // 比较元素
    protected int cmpElements(E e1, E e2) {
        cmpCount++;
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>)e1).compareTo(e2);
    }

    // 交换元素
    protected void swap(int idx1, int idx2) {
        E tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
        swapCount++;
    }

    @Override
    public int compareTo(Sort o) {
        int result = (int)(this.time - o.time);
        if (result != 0) return result;

        result = this.cmpCount - o.cmpCount;
        if (result != 0) return result;

        return this.swapCount - o.swapCount;
    }

    @Override
    public String toString() {
        return String.format("【%s】%n耗时：%ss(%sms)    比较：%s   交换：%s%n---------------------------------------------------------",
                getClass().getSimpleName(), (time / 1000.0), time,
                numberString(cmpCount), numberString(swapCount));
    }


    private String numberString(int number) {
        if (number < 10000) return "" + number;
        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}
