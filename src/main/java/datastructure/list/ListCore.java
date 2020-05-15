package datastructure.list;

public class ListCore {

    /**
     * 线性结构
     *
     * 线性表：具有n个相同元素的有限序列
     * a1首驱，an尾节点。a1是a2的前驱，a2是a1的后续
     *
     * 数组：一种顺序存储的线性表，所有元素的内存地址是连续的，致命缺点无法动态扩容。
     * 链表：一种链式存储的线性表，所有元素的内存地址不一定是连续的。（单向、双向、环形）
     * 栈：一种特殊的线性表，只能在一断操作，LIFO后进先出
     */


    /**
     *
     * 线性结构复杂度对比：
     *
     *     动态数组 链表
     * 新增 O(n)   O(n)
     * 删除 O(n)   O(n)
     * 修改 O(1)   O(n)
     * 查找 O(1)   O(n)
     *
     *
     *
     * 动态数组的add(Element e) 最好是O(1)，最坏是O(n)，平均是O(1)，大部分情况都不需要移动元素O(1)，最后一次再扩容移动元素O(n)，
     * 有个更合适的称呼叫 均摊复杂度，适合使用场景：结果连续多次复杂度比较低的操作后，出现个别复杂度比较高的情况。
     * 一般均摊复杂度就是最好情况复杂度。
     * 如：数组长度为4，进行4次add()，第5次add()的时候，需要扩容，循环移动4次，把这4次均摊到前几次中，每次的操作数是2，也就是复杂度O(1)。
     *
     *
     * 扩容缩容时机，要保证避免复杂度震荡，扩容倍数和缩容时机相乘不等于1，可以避免复杂度震荡，也就是不要在临界区的地方新增删除，不然每次都会O(n)
     *
     * @datastructure.list.linked.single.SingleLinkedList  @datastructure.list.array.ArrayList 在以上类有解释
     *
     * 双向链表比单向链表效率高一半。
     *
     * 如果频繁在尾部进行添加、删除操作，动态数组和双向链表均可选择
     * 如果频繁在头部进行添加、删除操作，建议选择双向链表
     * 如果有频繁的在任意位置进行添加、删除操作，建议选择双向链表
     * 如果有频繁的查询操作（随机访问操作），建议选择动态数组
     *
     */
}
