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
     * 1.先根据key生成哈希值（必须是整数，因为数组的index就是int类型）
     * 2.再让哈希值跟数组的大小进行相关运算，生成一个索引值  （例如：计算出来的哈希值和数组的长度，进行取模运算，数组的长度最好为2的n的次方，方便取模优化成位运算）
     *
     * 良好的哈希函数应该让 哈希值更加均匀分布、尽量散列，能减少哈希冲突次数，从而提升哈希表的性能
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
     * 这个n，jdk官方取的是31，因为31是一个神奇的奇素数，素数和其他数相乘的结果比其他方式更容易产生唯一性，减少哈希冲突
     * JVM又会将31 * i优化成(i << 5) - i  也就是  31 * i = (2^5 - 1) * i = (i << 5) - i
     * 31 * 5 可以优化成 5 << 5 - 5， 位操作、加减法操作比乘除快
     * 依据上面的理由，再根据观测分布结果后，jdk选择31作为这个n
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
     * jdk1.8的HashMap要求key，必须实现hashcode、equals方法
     * hashcode为了确认索引位置，equals为了冲突之后使用链地址法，依次元素比较是否相等来决定替换或新增
     *
     * jdk1.8的HashMap也会对每个key的hashCode进行扰乱计算，让其散布更均匀
     *
     */


    /**
     *
     * 哈希表扩容与数据挪动
     *
     * 为什么需要扩容：
     * 桶数组长度如果比较小，要添加的节点数量很多的话，可能每个桶里的红黑树会存储成千上万个节点，高度会很高
     * （因为hash冲突寻找节点的时候可能会有整棵红黑树扫描的情况，所以性能可能会比较低）
     * 这时候我们扩容，将节点重新分配分流到每个桶，减少每棵红黑树的节点数、高度，无论是搜索还是扫描，各方面性能都会提升
     *
     *
     * 什么时候需要扩容：
     * 装填因子（Load Factor）： 节点总数 / 哈希表桶数组长度，也叫做负载因子
     *
     * jdk1.8的HashMap中，当装填因子超过0.75，就需要扩容！扩容为原来的2倍
     * 据统计情况，装填因子不超过 0.75，哈希表性能最好
     *
     *
     * 数据挪动：
     * 将原本桶内的所有红黑树节点一一遍历，变为初始状态的节点（红节点，左右父节点都为空），重新计算index索引，来放入新的桶
     * 注意不是重新put放入，那样会创建新的节点，应该是直接挪动
     *
     * 据计算显示，每次扩容2倍，节点的新索引要么不变，要么是当前索引 + 旧时桶数组的长度
     *
     *
     */

    /**
     * hash取模运算优化
     * 如果取模运算使用位运算，建议数组的长度最好为2的n的次方，方便做位运算取模优化，扩容每次为旧容量的2倍
     * 如果取模运算使用%分号，建议是长度为素数（质数），对应扩容也要查素数表，来选择新容量，素数可以让计算结果更散列，减少冲突（了解即可）
     *
     */


    /**
     * hashCode方法和equals方法规范
     *
     * 1.hashCode方法和equals方法要同步更新，equals相等的对象，hashCode一定要相等
     * 2.自反性：自己equals自己一定要返回true
     * 3.对称性：对于任何非null的x、z对象，如果y.equals(x) 返回true，那么x.equals(y)，必须要 返回true
     * 4.传递性：对于任何非null的x、y、z对象，如果x.equals(y)、y.equals(z) 返回true，那么x.equals(y)必须 返回true
     * 5.一致性：对于任何非null的x、y对象，只要equals的比较操作在对象当中所用的信息没有被修改，多次调用x.equals(y) 就会一致的 返回true，或者一致的 返回false
     * 6.对于任何非null的x对象，x.equals(null) 必须返回false
     */


    /**
     *
     * 时间复杂度：
     *
     * hash表：增删改查 O(1)
     *
     * TreeMap 时间复杂度是 O(logn)
     * 元素具备可比较性，并且要求升序遍历（元素从小到大），可以使用TreeMap
     *
     *
     * HashMap 时间复杂度平均是 O(1)，因为每个桶内的红黑树，都被填充因子，控制到了很小的范围（节点数）、很均摊，甚至是一个桶就一个节点
     * 可以认为是原本的数据规模被每个红黑树分摊，是O(1)级别复杂度
     * 无序遍历，性能要求高的时候可以使用HashMap
     *
     */
}
