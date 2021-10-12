package algorithms.divide_and_conquer;

public class DivideAndConquerCore {

    /**
     * 分治：
     * 也就是分而治之
     *
     * 它的一般步骤是：
     * 1.将原问题分解成若干个规模较小的子问题 （子问题和原问题的结构一样，只是规模不一样）
     * 2.子问题又不断分解成规模更小的子问题，直到不能再分解  （直到可以轻易计算出子问题的解）
     * 3.利用子问题的解推导出原问题的解
     *
     * 注意：子问题之间是相互独立的
     * 因此，分治策略非常适合用递归
     *
     * 分治的应用：归并排序、快速排序、大数乘法
     */


    /**
     * 主定理：
     * 分治策略通常遵守一种通用模式
     * 解决规模为n的问题，分解成a个规模为 n / b 的子问题，然后在O(n^d)时间内将子问题的解合并起来
     *
     * 算法的运行时间为：T(n) = a * T(n / b) + O(n^d)
     * 其中必须a > 0, b > 1, d >= 0，也就是必须拆解出子问题，规模必须是缩小了
     * d > logb(a), T(n) = O(n^d)
     * d = logb(a), T(n) = O(n^d * logn)
     * d < logb(a), T(n) = O(n^logb(a))
     *
     * logb(a)是求a能被b整除几次，求对数
     * 比如归并排序：T(n) = 2 * T(n / 2) + O(n)，a = 2, b = 2, d = 1， d = log2(2)，所以T(n) = O(nlogn)
     *
     */

    /**
     * 为什么有些问题采取分治策略能提升性能？
     * 假设有个问题解法为O(n^2)，n分成两半，分别为 O((n/2) ^ 2)，最终两部分时间加起来是 O(n^2 / 2)
     * 例如：n为8，8^2 = 64，n分为两半，4^2 = 16，16 + 16 = 32，32是64除以2，效率提升了，当然还有一个合并操作，如果合并操作大于32，那效率则会降低，因此合并操作是效率的一大判别标志
     *
     * 再比如之前的冒泡，选择，插入，是n个数据依次两两比较，有多余的重复比较
     * 而快速和归并排序，则分为两部分比较，然后两部分之间比较的时候，部分内的不会再重复比较，只会和另外一个部分比较
     */


}