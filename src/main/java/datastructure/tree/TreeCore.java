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
     * 判断是什么失衡情况 是看失衡节点的最高子树L or R，然后看L or R的最高子树，最后确定 LL LR RR RL
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
     * 如果叶子节点删除掉一个元素，导致元素个数低于最低限制，这种现象被称为下溢（underflow）
     *
     * 下溢解决办法：
     * 下溢节点的元素个数必定等于ceil(m/2) - 2
     *
     * 如果下溢节点临近的（对应父元素其下的）兄弟节点，最少有ceil(m/2)个元素，可以向其借一个元素，建设父节点最中间的元素为b，兄弟节点的最 大/小 元素为a
     * 将父节点的元素b插入到下溢节点 0或者max 的位置（最小 / 大位置），将兄弟节点的元素a替代父节点元素b的位置，将元素a的右节点，变换到（移接为）b元素的左节点
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
     * grand向上合并时，可能继续发生上溢，以新添加节点的情况类推递归修复，若持续到根节点，需将根节点染成BLACK
     *
     *
     *
     *
     * 删除：
     * 红黑树等价于4阶B树，所有真正被删除的元素都是在叶子节点中
     *
     *
     * 有1种情况：删除的节点是RED节点，不需要做任何额外处理，性质都满足
     *
     *
     *
     *
     * 有3种情况：删除的节点是BLACK节点，可能需要处理，会导致不满足性质
     *
     * 删除度为2的BLACK节点 不需要处理，按照二叉搜索树的删除公式，会找到 前驱/后继 节点覆盖，然后删除前驱/后继，
     * 前驱/后继节点替代删除，永远不会出现删除度为2的情况，因此不用考虑这种情况，不存在
     *
     * 删除度为1的BLACK节点，如果用以替代的子节点是RED，将替代的子节点染为黑色
     *
     *
     * 删除BLACK叶子节点（下溢）
     * 为根节点，不处理
     *
     * sibling节点为BALCK，并且有一个或者两个RED子节点（向sibling，借一个）
     * 对parent - sibling - sibling的RED子节点 进行相应情况（LL RR LR RL）的旋转，将旋转中心节点继承parent的颜色，旋转后的左右节点染成黑色
     *
     * sibling节点为BALCK，没有RED子节点（parent向下合并）
     * 将sibling节点染成红色，parent节点染成黑色，如果parent原本就是黑色的，可能会再次导致下溢，要将parent当成要删除的元素，按照情况进行递归修复
     *
     * sibling节点为RED
     * 将sibling节点染成黑色，parent节点染成红色，对parent - sibling - 删除节点相对的sibling的远邻节点（就是真正要变为兄弟节点的它的另外一边的节点）（也可以理解为sibling的子节点中，和删除节点是相对方向的节点）进行相应情况（LL RR LR RL）的旋转，
     * 新的sibling就会是BALCK颜色，然后按照上面的列举的情况进行修复。
     * 相当于是旋转，然后得到正确的sibling黑色节点，再进行修复
     *
     *
     *
     *
     * 平衡：
     * 相比于AVL树，红黑树的平衡标准比较宽松，没有一条路径会大于其他路径的2倍，最多就是它的两倍
     * 是一种弱平衡，黑节点高度平衡
     *
     * 红黑树的最大高度 2 * log2(n + 1)，依然是 O(logn) 级别
     * 100W个节点，红黑树最大树高是40
     *
     * 复杂度（平均）：
     * 搜索：O(logn)
     * 添加：O(logn)，O(1)次的旋转操作
     * 删除：O(logn)，O(1)次的旋转操作，（统计最多是旋转3次）
     *
     */


    /**
     *
     * AVL树 VS 红黑树
     *
     * AVL树：平衡标准比较严格：每个左右子树高度不超过1
     * 最大高度是 1.44 * log2(n + 2) - 1.328 （100W个节点，AVL树最大树高是28）
     * 搜索、添加、删除都是O(logn)复杂度，其中添加仅需O(1)次旋转，删除最多需要O(logn)次旋转修复
     *
     * 红黑树：平衡标准比较宽松：没有一条路径会大于其他路径的2倍
     * 最大高度是 2 * log2(n + 1) (100w个节点，红黑树最大数高是40)
     * 搜索、添加、删除都是O(logn)复杂度，其中添加、删除都仅需O(1)次旋转
     *
     *
     * 搜索的次数远远大于插入和删除，选择AVL树
     * 搜索、插入、删除次数几乎差不多，选择红黑树
     *
     * 相比AVL树，红黑树牺牲了部分平衡性，以换取插入、删除操作时少量的旋转操作，整体来说性能优于AVL树
     * 红黑树的平均统计性能优于AVL，大部分实际应用中更多选择红黑树
     *
     */

    /**
     * 堆：
     * 也是一种树状的数据结构，常见的堆实现有：二叉堆、多叉堆、索引堆、二项堆、斐波那契堆、左倾堆、斜堆     （不要跟内存模型中的 “堆空间” 混淆）
     *
     * 堆的一个重要性质是：任意节点的值 总是 >= 或者 总是 <= 子节点的值
     * 如果任意节点的值总是 >= 子节点的值，称为：最大堆、大根堆、大顶堆
     * 如果任意节点的值总是 <= 子节点的值，称为：最小堆、小根堆、小顶堆
     *
     * 由此可见，堆中的元素必须具备可比较性（跟二叉搜索树一样）
     *
     */

    /**
     * 二叉堆：
     * 它的逻辑结构就是一颗完全二叉树，所以也叫做完全二叉堆
     * 鉴于完全二叉树的一些特性，二叉堆的底层结构一般用数组实现即可，用数组来表示二叉堆
     *
     * 用数组来实现二叉堆的方式：
     *
     * 索引i的规律 （n为元素数量）
     * 如果 i = 0，它是根节点
     * 如果 i > 0，它的父节点索引是 floor((i - 1) / 2)
     *
     * 如果 2i + 1 <= n - 1，它的左子节点索引为 2i + 1
     * 如果 2i + 1 > n - 1，它无左子节点
     *
     * 如果 2i + 2 <= n - 1，它的右子节点索引为 2i + 2
     * 如果 2i + 2 > n - 1，它无右子节点
     *
     *
     * 最大堆
     *
     * 添加：
     * 如果 Node > 父节点，与父节点交换位置
     * 如果 Node <= 父节点，或者Node没有父节点，退出循环
     * 时间复杂度：O(logn)，和树的高度有关
     * 这个过程，叫做上滤（Sift Up），保证堆的特性
     *
     * 删除：      （删除是只能删除根节点）
     * 用最后一个节点覆盖根节点
     * 删除最后一个节点
     * 如果 Node < 子节点，与最大的子节点交换  （任意子节点，可以直接比较最大的子节点）
     * 如果 Node >= 子节点，或者没有子节点了，退出循环 （所有子节点，可以直接比较最大的子节点）
     * 时间复杂度：O(logn)，和树的高度有关
     * 这个过程，叫做下滤（Sift Down），保证堆的特性
     *
     * 小公式：数组索引为非叶子节点总数 的节点开始都是叶子节点，没有子节点，完全二叉树非叶子节点个数计算公式: floor(n / 2)
     * 只要遇到第一个叶子节点，往后都是叶子节点
     *
     *
     * 批量建堆（Heapify）：就是给你一批无序的数据，去建立堆，有两种建堆方式
     * 自上而下的上滤：也就是从上往下，依次对节点上滤， 次数是所有节点的深度之和，时间复杂度是O(nlogn)，叶子节点多 深度是11（假设），可能会很深
     * 自下而上的下滤：也就是从下往上，依次对节点下滤， 次数是所有节点的高度之和，时间复杂度是O(n)，叶子节点多 但高度都是1
     * 自下而上的下滤效率高
     *
     * 通过改变比较方式，可以让最大堆变为最小堆
     *
     * 二叉堆可以高效率解决Top K问题
     * 时间复杂度是：O(nlogk)
     *
     */


    /**
     * 哈夫曼树
     *
     * 哈夫曼编码是现代压缩算法的基石
     *
     * 构建哈夫曼树：
     * 1.先计算出每个字母的出现频率（叫做权值）
     * 2.将这些权值作为根节点构建 n 颗二叉树，组成森林
     * 3.在森林中找出2个根节点最小的树合并，作为一颗新树的左右子树，新树的根节点为其左右子树根节点之和
     * 4.从森林中删除刚才选取的2棵树，并将新树加入森林
     * 5.重复上面的步骤3、4步骤，直到森林只剩下一颗树为止，这个树就被称为哈夫曼树
     *
     * 构建哈夫曼编码：
     * 哈夫曼树的left为0，right为1，可以得出每个字母对应的哈夫曼编码 （每个字母权值节点都是叶子节点，从根节点到它的路径，路径上左右方向代表0，1，依次记录，最后得出哈夫曼编码）
     *
     * 出现频率最高的编码最短，出现频率最低的编码最长，整体编码是相比最短的
     *
     *
     * 总结：
     * n个权值构建出来的哈夫曼树拥有 n 个叶子节点
     * 每一个哈夫曼编码都不是任何另外一个哈夫曼编码的前缀     （每个字母的编码就是根节点到它的路径，每个字母又都是叶子节点，路径都是唯一的，保证了不会互为前缀）（一个编码是另外一个编码的前缀，解析会有歧义不准确）
     * 带权路径长度：树中所有叶子节点的权值 乘以 其到根节点路径长度，最后相加；    与最终的哈夫曼编码长度成正比关系（因为最终生成的哈夫曼编码，就是每个字母出现次数 * 其编码长度，最后相加）
     * 哈夫曼树是带权路径长度最短的树，权值较大的离根节点比较近
     *
     */


    /**
     * Trie
     *
     * Trie也叫做字典树、前缀树、单词查找树
     * Trie搜索字符串的效率，主要跟搜索字符串的长度有关
     *
     * 存储方式：
     * Trie是一颗多叉树
     * 根节点不存储任何字符
     * 添加一个字符串的时候，取出每个字符，沿着根节点，单个字符往下添加
     * 删除一个字符串的时候，找到单词节点，依次往上删除，遇到父节点是单词节点，或者父节点有其他子节点，则证明这个父节点是其他单词的前缀，不能继续往上删除
     *
     */


    /**
     * 并查集：
     * 也是一种树状结构，也叫作 不相交集合、不相交集合森林，可以用来查询两个元素是否在一起，和进行合并
     *
     * 一开始所有的元素都独占一个集合，它们分别是各个集合的根节点，根节点代表这个集合  （这里的集合，不是指Set这种数据结构，是广义的数据集合）
     * 集合的元素都有父节点，根节点的父节点指向自己  （也代表它没父节点）
     *
     *
     * 有两个核心操作：
     * 查找：查找元素所在的集合
     * 合并：将两个元素所在的集合，合并为一个集合
     *
     *
     * 有两种实现思路：
     * Quick Find（快查并查集）：
     * 合并的时候，将v1所在集合的所有元素都指向v2的根节点，构建出来的并查集，每个集合的最高高度只有2，所有元素的父节点都直接是根节点
     *
     * 查找（Find） 操作的时间复杂度是O(1)，因为直接拿到元素的父节点，也就是根节点（根节点就代表这个集合）
     * 合并（Union）操作的时间复杂度是O(n)，因为合并时，要改变另外一个集合的所有元素的父节点为，要合并元素所在集合的根节点   （所有元素嫁接）
     *
     * Quick Union（快合并查集）：
     * 合并的时候，将v1的根节点指向v2的根节点，构建出来的并查集，每个集合是比较常规的树，高度深浅不一
     *
     * 查找（Find） 操作的时间复杂度是O(logn)，因为根据树的高度往上寻找根节点
     * 合并（Union）操作的时间复杂度是O(logn)，因为合并时，只需要找到两个元素所在集合的根节点，然后一个根节点的父节点，指向另外一个集合的根节点   （根节点嫁接）
     *
     * 不过Quick Union的查找（Find）、合并（union）操作可以通过优化，时间复杂度能达到 O(α(n))， α(n) < 5
     *
     *
     *
     * 优化Quick Union：
     * 在Union合并的过程中，可能会出现树不平衡的情况，甚至退化成链表，有两种优化方式
     * 1.基于size的优化：元素少的树 嫁接到 元素多的树  （也可能会存在树不平衡的问题）
     * 2.基于rank的优化：矮的树 嫁接到 高的树  （会相对平衡一点，还有优化的空间）
     * 但随着Union的次数越来越多，树的高度依然会越来越高
     *
     * 路径压缩：在Find时，使路径上的所有节点都指向根节点，从而降低高度
     * 不过实现成本高，有更优的两种做法，平均了高度和成本
     *
     * 路径分裂：在Find时，使路径上的每个节点都指向其祖父节点
     * 路径减半：在Find时，使路径上每隔一个节点就指向其祖父节点
     *
     * 使用 路径压缩、分裂或者减半 + 基于rank或者size的合并优化，可以确保Quick Union每个操作的均摊时间复杂度为 O(α(n))，α(n) < 5
     *
     * 建议使用：Quick Union + 基于rank的合并优化 + 路径减半或分裂
     *
     * 并查集也支持自定义对象，因为并查集本身就是树形结构，可以使用链表来表示树，再加上Map，从而实现并查集（链表+Map）
     */


    /**
     * B+树：
     * 是B树的变体，平衡多路搜索树，一个节点存储多个元素，常用于数据库和操作系统的文件系统中，Mysql数据库的索引就是基于B+树实现的
     *
     * 特点：
     * 1.分为内部节点（非叶子）、和叶子节点2种节点
     * 2.内部节点只存储key，不存储具体数据，而叶子节点存储key和具体数据
     * 3.所有的叶子节点形成一条有序链表
     *
     * m阶B+树
     * 非根节点的元素数量 x 为 ceil(m/2) <= x <= m
     *
     *
     * Mysql的索引底层为何使用B+树？
     * 索引文件，用于文件系统是存储在硬盘上的，读取和写入都是要经过IO操作，IO操作会比较费时，（磁盘移动磁头寻道），因此需要减少IO操作次数
     *
     * 为了减小IO操作数量，一般把一个节点的大小设计成最小读写单位的大小    （尽量一次载入节点耗费更少IO操作次数）
     * Mysql的存储引擎InnoDB的最小读写单位是16K
     *
     * 对比B树，B+树的优势是
     * 1.每个节点存储的key数量更多，树的高度更低      （因为一个节点可以分开更多叉，高度会因此更低）
     * 2.所有的具体数据都存在叶子节点上，所以每次查询都要查到叶子节点，查询速度比较稳定
     * 3.所有的叶子节点构成了一个有序链表，做区间查询时更方便     （比如要查询5 ~ 10，只需要搜索到5，然后跟着有序链表往后遍历，直到10结束，非常方便）
     *
     * B+树相较于其他树，节点更少，高度更矮，更平衡，代表寻找路径的节点更少，IO次数更少，因为节点可能不在连续的位置，多个节点意味着多次IO
     */

    /**
     * B*树：
     * 是B+树的变体：给内部节点增加了指向兄弟节点的指针
     *
     * m阶B*树
     * 非根节点的元素数量 x 为ceil(2 * m / 3) <= x <= m， 代表最低存储的元素更多
     */
}
