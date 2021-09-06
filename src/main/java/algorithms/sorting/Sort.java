package algorithms.sorting;

import algorithms.sorting.comparison.ShellSort;
import algorithms.sorting.comparison.model.Student;

import java.text.DecimalFormat;
import java.util.Comparator;

// 排序抽象类
public abstract class Sort<E> implements Comparable<Sort<E>> {

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
        return cmp(e1, e2);
    }

    // 比较元素
    protected int cmp(E e1, E e2) {
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
    public int compareTo(Sort<E> o) {
        // 按时间
        int result = (int)(this.time - o.time);
        if (result != 0) return result;

        // 按比较次数
        result = this.cmpCount - o.cmpCount;
        if (result != 0) return result;

        // 按交换次数
        return this.swapCount - o.swapCount;
    }

    @Override
    public String toString() {
        // 先存储副本，因为isStable()是否稳定调用后，会改变这些值
        long time = this.time;
        int cmpCount = this.cmpCount;
        int swapCount = this.swapCount;

        return String.format("【%s】%n稳定性：%b  耗时：%ss(%sms)    比较：%s   交换：%s%n---------------------------------------------------------",
                getClass().getSimpleName(), isStable(), (time / 1000.0), time,
                numberString(cmpCount), numberString(swapCount));
    }


    private String numberString(int number) {
        if (number < 10000) return "" + number;
        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    // 是否稳定
    private boolean isStable(){

        // 希尔排序比较特殊，就属于不稳定排序
        if (this instanceof ShellSort) return false;

        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(18, i * 10);
        }

        sort((E[]) students);

        for (int i = 1; i < students.length; i++) {
            // 排好序后，每个也应该比前一个分数相差10
            if (students[i].getScore() - students[i - 1].getScore() != 10){
                return false;
            }
        }

        return true;
    }
}
