package algorithms.sorting.comparison;

public class ComparisonCore {
    /**
     * 比较排序
     */

    /**
     * 冒泡排序：
     * 1. 从头开始比较每一对相邻元素，如果第1个比第2个大，就交换它们的位置（执行完一轮后，最末尾的元素就是最大的元素）
     * 2. 忽略步骤1中曾经找到的最大元素，重复执行步骤1，直到全部元素有序
     *
     *
     * 稳定算法，原地算法
     *
     * 时间复杂度
     * 最好：O(n)
     * 最坏、平均：O(n^2)
     *
     * 空间复杂度：O(1)
     */


    /**
     * 选择排序：
     * 1.从序列中找出最大的那个元素，然后与最末尾的元素交换位置（执行完一轮后，最末尾的元素就是最大的元素）
     * 2.忽略步骤1中曾经找到的最大元素，重复执行步骤1，最后元素有序
     *
     * 稳定、原地算法
     *
     * 时间复杂度：均为O(n^2)，空间复杂度：O(1)
     */


    /**
     * 堆排序：     可以看作是对选择排序的优化，选择阶段使用堆来寻找最大元素
     * 1.对序列原地建堆
     * 2.重复以下步骤，直到堆的元素数量为1，最后元素有序
     *  交换堆顶元素和尾元素
     *  堆的元素数量 -1
     *  将0位置（新堆顶）进行1次SiftDown下滤操作
     *
     * 不稳定、原地算法
     * 时间复杂度：均为O(nlogn)，空间复杂度：O(1)
     */


    /**
     * 插入排序：
     * 1.在执行的过程中，插入排序会将序列分为2部分
     *  头部是已经排好序的，尾部是待排序的
     *
     * 2.从头扫描每一个元素
     *  每当扫描一个元素，就将它插入头部合适的位置，使得头部数据依然保持有序 （一开始第1个元素可以默认当头部，然后从尾部，从头到尾扫描）
     *
     *
     * 逆序对：在升序情况下，任意的两个数字，在前面顺序的一个数字 大于 在后面顺序的一个数字，这俩数字就是一个逆序对
     * 数组[2,3,8,6,1]，逆序对为：<2,1> <3,1> <8,1> <8,6> <6,1>
     *
     * 插入排序的 复杂度 与 逆序对的数量 成正比关系
     * 逆序对的数量越多，插入排序的时间复杂度越高
     * 逆序对的数量越少，插入排序的时间复杂度越低
     *
     * 逆序对数量极少时，甚至速度比O(nlogn)级别的快速排序还要快
     * 数据量不是特别大的时候，插入排序的效率也是非常好的
     *
     * 稳定、原地算法
     *
     * 时间复杂度：
     * 最好：O(n)
     * 最坏、平均：O(n^2)
     *
     * 空间复杂度：O(1)
     *
     */


    /**
     * 二分搜索：
     * 假设在 [begin, end) 范围内搜索某个元素v       （搜索是begin左包含，end右不包含，叫做左闭右开，end是最后一个位置索引 + 1）
     * 先去找中间位置，mid == (begin + end) / 2，拿到对应元素值m
     * 如果 v < m，就到左边搜索，在 [begin, mid) 范围内二分搜索      （相当于 mid - 1，下次搜索不会有到mid位置，mid被排除了，右不包含）
     * 如果 v > m，就到右边搜索，在 [mid + 1, end) 范围内二分搜索
     * 如果 v == m，就找到了，结束
     * 如果 begin == end，则代表已经是没有元素的位置了，没有元素可以搜索，没搜索到     （end是不包含的位置，begin也等于的话，则代表已经在边界外了）
     *
     *
     * 也就相当于每次将范围一分为二，进行搜索，每次排除掉一半，范围需要有序，也叫做折半查找
     * 如果要搜索的，在范围内存在多个重复的值，返回的位置是不确定的
     *
     * 最坏复杂度：O(logn)
     */



    /**
     * 归并排序：
     *
     * 1.序不断的将当前序列平均分割成2个子列，直到不能再分割（序列中只剩1个元素，不能再分割，它有序）---分割阶段
     * 2.不断的将2个子序列合并成一个有序序列，直到最终只剩下1个有序序列            ---合并阶段
     *  （2个子序列的元素挨个对比，谁符合条件，谁归并，它的下一个元素比对位置++，另外一个对比位置不变）
     *
     * 时间复杂度：
     * 最好、最坏、平均：O(nlogn)
     *
     * 空间复杂度:
     * O(n / 2 + logn) = O(n);  n / 2是拷贝的一半数组空间，和 logn次递归调用（栈空间）
     *
     * 稳定、非原地算法
     */


    /**
     * 快速排序：
     *
     * 1.从序列中选择一个轴点元素(pivot)
     *   （假设每次选择0位置的元素为轴点元素）
     *
     * 2.使用pivot将序列分割成2个子序列
     *    将小于pivot的元素放在pivot前面（左边）
     *    将大于pivot的元素放在pivot后面（右边）
     *    将等于pivot的元素放在哪边都可以
     *
     * 3.对子序列进行 1、2 步骤操作
     *    直到不能再分割（子序列中值剩下1个元素）
     *
     *
     * 快速排序的本质：逐渐将每一个元素都转换成轴点元素，全部变为轴点元素，整个序列就排序好了
     *
     *
     * 时间复杂度：
     * 最好、平均：O(nlogn)
     * 最坏：O(n^2)    左右分割极其不均匀
     * 为了降低最坏情况的出现概率，一般采取做法是：随机挑选轴点元素（解决大量有序数据导致不均匀）、还有等于pivot的情况互相放到对面（解决大量相等数据导致不均匀），为了尽量保证分割均匀
     *
     * 空间复杂度：O(logn)，递归调用耗费的栈
     *
     * 不稳定、非原地算法
     */
}
