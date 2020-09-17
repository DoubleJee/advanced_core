package datastructure.hash;

public class HashCore {

    /**
     *
     * HashTable 哈希表
     * 也叫做散列表，Hash英文有 剁碎 的意思
     *
     */

    /**
     * HashTable基本概念：
     * HashTable = 哈希函数 + 数组
     * HashTable底层使用 数组 来实现，数组里的元素叫做Bucket（桶），整个数组叫做Buckets或者Bucket Array
     * Hash函数：将key生成对应的索引
     * Hash冲突：两个不同的key生成的索引是一样的
     *
     * HashTable的 添加、搜索、删除流程都是类似的
     * 1.利用哈希函数生成key对应的index     O(1)
     * 2.根据index定位操作数组元素     O(1)
     *
     * HashTable是典型的 空间换时间 例子
     *
     */


    /**
     * Hash函数实现步骤：
     * 1.先根据key生成哈希值（必须是整数）
     * 2.再让哈希值跟数组的大小进行相关运算，生成一个索引值  （例如：计算出来的哈希值和数组的长度，进行取模运算）
     *
     * 良好的哈希函数应该让 哈希值更加均匀分布，能减少哈希冲突次数，从而提升哈希表的性能
     *
     *
     * hash值生成方式：
     * 不同种类（整数、浮点数、字符串、自定义对象）的key，哈希值生成方式不一样，但是目标都是一致的
     * 尽量让每个key的哈希值唯一、尽量让key的所有信息参与运算、生成的hash值都是整数
     *
     * 整数：直接拿整数值当hash值
     * 浮点数：将内存里表示自己的bits，当成整数来读，读取的整数当hash值
     * Long：利用高32bit和低32bit，混合计算出32bit的整数，当做hash值，（jdk做法是将高32bit与低32bit进行异或运算后，将32bit读成整数，= (int) bit ^ (bits >>> 32) )
     * Double：转换成Long，然后同上
     *
     * 字符串：因为字符串是由若干个字符组成的，每个字符有自己的ASCII码，本质也就是一个整数
     * 可以按照这种方式：10进制值是这样计算出来的，5632 = (5 * 10^3) + (6 * 10^2) + (3 * 10^1) + (2 * n^0)
     * 我们也可以按照上面的方式来套用，"jack"的hash值 = (j * n^3) + (a * n^2) + (c * n^1) + (k * n^0)
     * 计算公式可以等价为 [(j * n + a) * n + c] * n + k，不需要次方运算，全部是乘法，可以看成是：先利用某个值 * n 再加上自己的值，某个值也就是前面那个字符算出来的结果
     * 也就是 hash值 = 每个字符 + 前一个字符 * n
     *
     * 这个n，jdk官方取的是31，因为31是一个神奇的奇素数，JVM会将31 * i优化成(i << 5) - i
     * 31 * 5 可以优化成 5 << 5 - 5， 位操作、加减法操作比乘除快
     *
     * 自定义对象：拿自己的所有信息（也就是每个属性，整数、自定义对象、浮点），分别计算出hash值，再将每个信息的hash值，按照字符串的方式去计算最终的hash值
     * (每个信息 * n) + 前一个信息计算的
     *
     *
     * hash值相等的对象，两个对象不一定相等，    一个String类型对象和一个Integer类型对象的hash值可能相等，但是这俩对象不相等
     * 两个相等的对象，hash值一定相等
     *
     */



    /**
     * Hash冲突解决方法：
     *
     * 1.开放定址法
     * 按照一定规则向其他地址探测，直到遇到空桶

     * 2.再哈希法
     * 设计多个哈希函数，进行多次计算

     * 3.链地址法
     * 比如通过链表将同一index的元素串起来，两个key共用一个桶
     *
     *
     * jdk1.8的哈希冲突使用了链地址法，默认使用 链表 + 红黑树 将元素串联起来 （key也会存放）
     * 在添加元素时，可能会由单向链表转为红黑树来存储元素，比如当 哈希表容器 >= 64 并且 单向链表节点数大于8 ，转换为红黑树
     * 当红黑树节点数量少到一定程度时，又会退化为单向链表
     * 使用单向链表的原因是因为每次要拿冲突的key，在链表里依次比对，如果一样替换，不一样就新增，从头到尾，完全可以使用单向链表，它还少了个prev指针，也更省内存空间
     *
     * jdk1.8的HashMap要求key，必须实现hashcode、equals方法，hashcode为了确认索引位置，equals为了冲突之后使用链地址法，依次元素比较是否相等来决定替换或新增
     *
     */
}
