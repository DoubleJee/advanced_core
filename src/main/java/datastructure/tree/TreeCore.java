package datastructure.tree;

public class TreeCore {

    /**
     * 树形结构
     *
     *
     */

    /**
     * 树的基本概念
     *  1.节点、根节点、父节点、子节点、兄弟节点
     *  2.一棵树也可以只有1个节点，也就是只有根节点，最多只能有1个根节点
     *  3.一棵树可以没有任何节点，成为空树
     *  4.子树、左子树、右子树
     *  5.节点的度：可以称为子树的个数
     *  6.树的度：所有节点度中的最大值
     *  7.叶子节点：度为0的节点
     *  8.非叶子节点：度不为0的节点
     *  9.层数：根节点在第1层，根节点的子节点在第2层，以此类推（有些可能以0开始）
     *  10.节点的深度：从根节点到当前节点的唯一路径上的节点总数
     *  11.节点的高度：从当前节点到最远叶子节点的路径上的节点总数   （个人补充见解：该节点其子树下面的最远叶子节点！！！）
     *  12.树的深度：所有节点深度中的最大值
     *  13.树的高度：所有节点高度中的最大值
     *  14.树的高度 等于 树的深度
     *
     *  15.有序树：树中任意节点的子节点之间有顺序关系
     *  16.无序树：树中任意节点的子节点之间没有顺序关系，也称为自由树
     *  17.森林：由n棵互不相交的树组成的集合，（n >= 0）
     */



    /**
     * 二叉树：
     *
     *  1.每个节点的度最大为2（最多拥有两个子树）
     *  2.左子树和右子树是有顺序的
     *  3.即使某节点只有一颗子树，也要区分左右子树
     *  4.非空二叉树的 第i层，最多有2的i - 1次方 个节点 (i >= 1)
     *  5.高度为h的二叉树上，最多有2的h次方 - 1 个节点 (h >= 1)
     *  6.对于任何一颗非空二叉树，如果叶子节点的个数为n0，度为2的节点个数为n2，则有：n0 = n2 + 1树形结构
     *  7.假设度为1的节点个数为n1，那么二叉树的节点总数 n = n0 + n1 + n2
     *  8.二叉树的边数 T = (n1 + 2 * n2) ， (n - 1) ， (n0 + n1 + n2 - 1) 这三个公式都可以推算出来
     *  9.二叉树的第i层，节点数量为 2的i次方 - 1个
     *    h为二叉树高度，叶子节点数量为：2的h次方 - 1个
     *    总节点数 n = (2的h次方 - 1)，(2的0次方 + 2的1次方 + ..... + 2的h次方 - 1)
     *    二叉树高度 h = log2(n + 1)
     *
     * 10.平衡：当节点数量固定时，左右子树的高度越接近，这颗二叉树就越平衡（高度越低）
     *
     *
     *  真二叉树：所有节点的度要么为0，要么为2，也就是代表，要么没有，要么有两个叉
     *  满二叉树：所有节点的度要么为0，要么为2，且所有的叶子节点都在最后一层，（完完整整）
     *
     *  完全二叉树：叶子节点只会出现最后2层，且最后1层的叶子节点都靠左对齐，度为一的节点只有左子树，度为一的节点要么1个，要么0个，出现度为一的正确节点或者叶子节点之后，从左向右，之后的节点必须是叶子节点。
     *
     *  同样节点数量的二叉树，完全二叉树的高度最小
     *  树高度 h = floor(log2n) + 1
     *  假设完全二叉树的高度为h，那么最少有2的h - 1次方个节点，最多2的h次方 - 1个节点
     *
     *  假如有n个节点，节点有序编号，编号i的父节点编号为floor(i/2)，如果2i <= n，它的左子节点编号为2i，右子节点编号为2i + 1。
     *  2i > n则没有左子节点，2i + 1 > n则没有右子节点
     *
     *  叶子节点个数计算公式：floor((n + 1) / 2)
     *  非叶子节点个数计算公式: floor(n / 2)
     *  （完全二叉树从根节点到倒数第2层是一颗满二叉树）
     *
     *  同样高度的二叉树中，满二叉树的叶子节点数量最多、总节点数量最多。
     *  满二叉树一定是真二叉树，真二叉树不一定是满二叉树。
     *
     *
     *
     *
     *
     *  二叉树遍历分为4种常见遍历：前序遍历、中序遍历、后序遍历、层序遍历
     *  前序遍历：遍历顺序为 根节点 -> 前序遍历（左||右）子树 -> 前序遍历（左||右）子树
     *  中序遍历：遍历顺序为 中序遍历（左||右）子树 -> 根节点 -> 中序遍历（左||右）子树
     *  后序遍历: 遍历顺序为 后序遍历（左||右）子树 -> 后序遍历（左||右）子树 -> 根节点
     *  层序遍历：从上往下、从左往右依次访问每一个节点
     *
     *  前驱节点：中序遍历时的前一个节点，例如：中序遍历结果为 [1、2、3、4、5、6、7、8、9、10]，[7]的前驱节点是[6]，
     *  寻找公式：{node.left != null 然后 node.left.right.right......，直到节点n的右为空，前驱节点为节点n}，如果前面未找到，
     *  则 {node.parent != null 然后 node.parent.parent.parent.....，直到节点n是父节点的右子树 == 节点n，前驱节点为节点n}，否则没有前驱
     *  (二叉搜索树：该节点左子树最大的，没有左子树，层层父节点小于该节点的)
     *
     *  后驱节点：中序遍历时的后一个节点，例如：中序遍历结果为 [1、2、3、4、5、6、7、8、9、10]，[7]的后驱节点是[8]，
     *  寻找公式与上面其反之：{node.right != null 然后 node.right.left.left......，直到节点n的左为空，后驱节点为节点n}，如果前面未找到，
     *  则 {node.parent != null 然后 node.parent.parent.parent.....，直到节点n是父节点的左子树 == 节点n，后驱节点为节点n}，否则没有后驱
     *  (二叉搜索树：该节点右子树最小的，没有右子树，层层父节点大于该节点的))
     */


    /**
     * 二叉搜索树:
     * 任意一个节点的值都大于其左子树所有节点的值
     * 任意一个节点的值都小于其右子树所有节点的值
     * 它的左右子树也是一颗二叉搜索树
     * 存储的元素必须具备可比较性
     *
     * 时间复杂度：O(logn) 添加删除搜索，树的高度多少，最坏的复杂度为多少。
     *
     * 一百万个节点 最低高度为20，也就是最多查20次，搜索效率非常高
     */

    /**
     * 平衡二叉搜索树：(Balanced Binary Search Tree)
     * 一颗达到适度平衡的二叉搜索树，可以称之为（自）平衡二叉搜索树
     * 用尽量少的调整次数达到适度平衡即可，常见的有：AVL树、红黑树
     *
     *
     *
     * AVL树：
     * 平衡因子：某节点的左右子树的高度差（左子树高度减去右子树高度的值）
     * 每个节点的平衡因子只可能是1、0、-1（绝对值 <= 1，如果超过1，称之为失衡）
     * 也就是每个节点的左右子树高度差不超过1
     * 添加删除搜索的时间复杂度是O(logn)
     * 失衡节点的父节点-祖先节点都可能会失衡，都可能会被影响，非失衡节点的父节点-祖先节点不受影响
     *
     *
     * r = 旋转节点，rl = 旋转节点的左子树，rr = 旋转节点的右子树
     *
     * LL情况 右旋（单旋） LL代表：左子树的左子树导致失衡 left-left 也就是左边的左边导致失衡，使用右旋保证平衡
     * 操作公式为 r.left = rl.right; rl.right = r;  （相对）rl就变了这棵子树的根节点了
     * 也就是旋转节点的左子树 = 原左子树的右子树，原左子树的右子树 = 旋转节点，这棵子树的（相对）根节点 = 原左子树
     * 也要对应维护 每个参与变化节点的parent属性更新、r和rl的高度更新
     *
     * RR情况 左旋（单旋） RR代表：右子树的右子树导致失衡 right-right 也就是右边的右边导致失衡，使用左旋保证平衡
     * 操作公式为 r.right = rr.left; rr.left = r;  （相对）rr就变了这棵子树的根节点了
     * 也就是旋转节点的右子树 = 原右子树的左子树，原右子树的左子树 = 旋转节点，这棵子树的（相对）根节点 = 原右子树
     * 也要对应维护 每个参与变化节点的parent属性更新、ib和ibr的高度更新
     *
     * LR情况 因为是LR，所以需要找单向情况，L后面是R，不是单向，应该看R和后面，后面没了，默认是一样的R情况，所以对R的那个子树进行RR左旋转，然后就是LL情况，再对第一个L的那个子树LL右旋转。 也就是 先RR左旋转 再LL右旋转（双旋转）
     * RL情况 先LL右旋转 再RR左旋转（双旋转）
     *
     * 判断是什么失衡情况 是看失衡节点的最高子树L or R，然后看L or R的 最高子树，最后确定 LL LR RR RL
     *
     *
     * 添加节点：可能会导致所有祖先节点都失衡，只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡，只需要O(1)次调整
     * 删除节点：只可能会导致父节点或祖先节点失衡（只会有一个节点失衡），让 父/祖先 节点恢复平衡后，可能会导致更高层的祖先节点失衡，最多需要O(logn)次调整
     *
     * 平均时间复杂度：
     * 搜索：O(logn)
     * 添加：O(logn)，仅需O(1)次旋转操作
     * 删除：O(logn)，最多需要O(logn)次旋转操作
     *
     */

    /**
     * B树：（Balanced Tree）
     * 是一种平衡的多路搜索树，多用于文件系统、数据库的实现（不是二叉的，是多叉的）
     * 1.  1个节点可以存储超过2个元素，可以拥有超过2个子节点
     * 2.拥有二叉搜索树的一些性质
     * 3.平衡，每个节点的所有子树高度一致
     * 4.比较矮
     *
     * m阶B树，（m >= 2 至少大于等于2）代表这颗B树的子节点最多的为m，一棵B树的一个节点有6个节点，是最多子节点的节点，就是6阶B树
     *
     * ceil为向上取整
     * 根节点元素个数x的范围为 1 <= x <= m - 1，也就是1 ~ m-1 个
     * 非根节点元素个数x的范围为 ceil(m/2) - 1 <= x <= m - 1，也就是 ceil(m/2) - 1 ~ m-1 个
     *
     * 节点的最大子节点数y = x + 1
     * 根节点的节点数y范围为  2 <= y <= m 也就是 2 ~ m个
     * 非根节点的节点数y范围为  ceil(m/2) <= y <= m 也就是 ceil(m/2) ~ m个
     *
     * m = 2，1 <= y >= 2，是二阶B树，因此可以称为(1~2)树、1-2树 （类似于二叉树）
     * m = 3，2 <= y >= 3，是三阶B树，因此可以称为(2~3)树、2-3树
     * m = 4，2 <= y >= 4，是四阶B树，因此可以称为(2~4)树、2-3-4树
     * m = 5，3 <= y >= 5，是五阶B树，因此可以称为(3~5)树、3-4-5树
     * m = 6，3 <= y >= 6，是六阶B树，因此可以称为(3~6)树、3-4-5-6树
     * m = 7，4 <= y >= 7，是七阶B树，因此可以称为(4~7)树、4-5-6-7树
     *
     * 数据库一般是200~300阶，一个节点能存储200-300个元素
     *
     * B树和二叉搜索树，在逻辑上是等价的
     * 多代节点合并，可以获得一个超级节点（元素不止一个的节点）
     * 2代合并的超级节点，最多拥有4个子节点（最少是4阶B树）
     * 3代合并的超级节点，最多拥有8个子节点（最少是8阶B树）
     * n代合并的超级节点，最多拥有2的n次方个子节点（最少是2的n次方阶B树）
     *
     * m阶B树，最多需要log2m 代合并
     *
     *
     * 搜索：先从节点内部从小到大搜索元素，如果命中，搜索结束，如果未命中，再去对应的子节点里搜索元素，以此类推
     *
     * 添加：新添加的元素必然是添加到叶子节点里，插入的节点元素数如果超出m阶限制，这种现象称为上溢（overflow）
     *
     * 上溢解决办法：
     * 上溢节点的元素个数必定等于m，建设上溢节点最中间元素的位置为k
     * 将k位置的元素向上与父节点合并，将上溢节点的[0,k-1] 和 [k+1,m-1]位置的元素分裂成2个子节点，（也就是向上合并，左右分裂）
     * 一次分裂完毕后，可能导致父节点上溢，依然以此类推解决上溢，最坏情况一直分裂到根节点
     *
     * 删除：
     * 如果要删除的元素在叶子节点中，直接删除即可
     * 如果要删除的元素在非叶子节点中，1.先找到（前驱/后继）元素，将值覆盖到所需删除元素的值 2.再把（前驱/后继）元素删除。
     * （非叶子节点的前驱或者后继元素，一定在叶子节点中，真正的删除元素都是发生在叶子节点中）
     * 如果叶子节点删除掉一个元素，导致元素个数低于最低限制，这种想象被称为下溢（underflow）
     *
     * 下溢解决办法：
     * 下溢节点的元素个数必定等于ceil(m/2) - 2
     *
     * 如果下溢节点临近的（对应父元素其下的）兄弟节点，最少有ceil(m/2)个元素，可以向其借一个元素，建设父节点最中间的元素为b，兄弟节点的最 大/小 元素为a
     * 将父节点的元素b插入到下溢节点 0或者max 的位置（最小 / 大位置），将兄弟节点的元素a替代父节点元素b的位置，将元素a的右节点，变换到b元素的左节点
     * 这种操作其实就是旋转
     *
     * 如果下溢节点临近的兄弟节点，只有有ceil(m/2) - 1个元素，没法借了
     * 将父节点的元素b挪下来跟左右子节点进行合并
     * 合并后的元素个数等于ceil(m/2) + ceil(m/2) - 2，不会超过m - 1
     * 这个操作可能会导致父节点也下溢，以此类推解决上溢，先找兄弟节点，不行，再看父节点
     *
     *
     *
     *
     * 上溢到根节点（根节点也上溢），会导致B树高度 + 1
     * 下溢到根节点（根节点也下溢），会导致B树高度 - 1
     *
     * 4阶B树的性质：
     * 所有节点能存储的元素个数都为 1~3
     * 所有非叶子节点的子节点个数都为 2~4
     *
     */


    /**
     * 红黑树：
     * 也是一种自平衡的二叉搜索树，以前也叫做平衡二叉B树
     *
     * 1.节点必须是RED 或者 BLACK
     * 2.根节点必须是BLACK
     * 3.叶子节点（是指空节点、外部节点）都是BLACK
     * 4.RED节点的子节点都是BLACK，  RED节点的父节点都是BLACK，从根节点到叶子节点的所有路径上不可能出现2个连续的RED节点，只可能是相隔的
     * 5.从任一节点到叶子节点的所有路径都包含相同数目的BLACK节点（计数不包含叶子节点，也就是空节点）
     * 保证以上五条性质就能保证平衡，才是红黑树
     *
     * sibling兄弟节点、parent父节点、uncle叔父节点（parent的兄弟节点）、grand祖父节点（parent的父节点）
     *
     * 红黑树 和 4阶B树 具有等价性
     * BLACK节点与它的RED子节点合并到一起，就能形成一个B树节点 （从左到右合并，从小到大）
     * 红黑树的BLACK节点个数 与 4阶B树的节点总树，相等
     *
     *
     * 添加：
     * 建议新添加的节点默认为RED，这样能够让红黑树的性质尽快满足
     * 如果添加的是根节点，染成BLACK即可
     * 在红黑树中，添加有12种情况，4种情况性质全部满足，8种不满足性质4
     *
     *
     * 有4种情况：parent是BLACK节点，不需要做任何额外处理，性质都满足
     *
     *
     *
     * 有8种情况：parent是RED节点，需要处理，不满足性质4
     * f = 破坏规则的节点，（大部分是新添加的红色节点）
     *
     * uncle节点是BLACK的4种情况 LL RR LR RL
     * LL RR：新添加破坏规则的节点是祖父节点的 Left - left 或者 Right - Right
     * 操作公式为 将parent染成BLACK，grand染成RED，再对grand进行单旋操作（左 / 右 旋转），再维护旋转后的关系     因为要达成 BLACK节点与它的RED子节点合并到一起，成为一个B树节点
     * 也可以先单旋，再进行按照特质对应从根开始染色黑到红
     *
     * LR RL：新添加破坏规则的节点是祖父节点的 Left - Right 或者 Right - Left
     * 操作公式为 将f染成BLACK，grand染成RED，再对parent进行单旋操作（左 / 右 旋转） ，再对grand进行单旋操作（左 / 右 旋转），再维护旋转后的关系 ，也就是双旋操作
     * 也可以先双旋，再进行按照特质对应从根开始染色黑到红
     *
     * uncle节点是RED的4种情况，会导致上溢
     * LL RR LR RL 上溢：新添加破坏规则的节点是祖父节点的 Left - left 或者 Right - Right 或者 Left - Right 或者 Right - Left，并且会导致合并上溢的
     * 操作公式为 将parent、uncle染成BLACK，grand向上合并，也就是grand看成新添加的节点处理，染为红色，再次进行添加的12种判断情况做出相应修复操作，以此类推递归进行
     * grand向上合并时，可能继续发生上溢，以新添加节点的情况类推递归修复，若持续到根节点，只需将根节点染成BLACK
     *
     *
     *
     *
     */
}
