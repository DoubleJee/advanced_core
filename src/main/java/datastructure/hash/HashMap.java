package datastructure.hash;

import datastructure.linear.queue.Queue;
import datastructure.other.map.Map;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;

    private static final boolean BLACK = true;

    // 大小 = 所有红黑树节点的总数量
    private int size;

    // 哈希表
    private Node<K, V>[] table;

    // 哈希表默认容量，最好是2的n次方，可以方便取模运算优化成位运算，默认是4次方，16
    private static final int DEFAULT_CAPACITY = 1 << 4;

    // 标准填充因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        // 清除每个红黑树的根节点就行了
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        // 扩容
        resize();
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = new Node<>(key, value, null);
            afterPut(root);
            table[index] = root;
            size++;
            return null;
        }

        // 哈希冲突
        // 添加新的节点添加到红黑树上面
        Node<K, V> parent = null;
        Node<K, V> node = root;
        int cmp;

        // 新添加的key
        K k1 = key;
        // 新添加key的hash值
        int h1 = hash(k1);
        Node<K, V> result;
        // 完整扫描
        boolean searched = false;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;

            // hash值比较，用以确认桶内的红黑树寻找方向
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;

                // 对象是否相等equals，确认是否已存在能替换
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;

                // 更新！！！类型不同，也当作是无法寻找方向的情况，扫描处理

//                // 类型不同，根据类名确认桶内红黑树寻找方向
//            } else if (k1 != null && k2 != null && k1.getClass() != k2.getClass()) {
//                String k1ClsName = k1.getClass().getName();
//                String k2ClsName = k2.getClass().getName();
//                cmp = k1ClsName.compareTo(k2ClsName);

                // 类型相同，具备可比较性，根据自带比较方法确认桶内红黑树寻找方向（比较得是不等于的情况，如果是等于则和equals冲突，等于当做是无法寻找方向的情况）
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {

                // 没有任何方式来确认桶内红黑树寻找方向了，也就是无法寻找方向，只能进行一次完整扫描，看看能不能找到节点equals情况
            } else if (!searched) {

                // 进行扫描，扫描到了就将结果保存，准备替换该节点值
                if (node.right != null && (result = node(node.right, k1)) != null
                        || node.left != null && (result = node(node.left, k1)) != null){
                    node = result;
                    cmp = 0;
                }else {
                    // 扫描不到，整个桶内的红黑树没有相等的节点，说明是新增，没有任何方式来确认插入方向，只能找个随机不唯一的方式来确认，那就是利用内存地址来比大小（在用内存地址这种方式前，会有一次完整扫描，不用担心插入重复），来决定插入的方向，内存地址没有负数所以不会溢出，不会出现异常
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }


                // 进行了一次完整扫描了，将标志修改，不需要再次扫描整个红黑树
                searched = true;


                // 如上，进行过了完整扫描，并且也没有找到，此后的遇到的每个节点，都利用内存地址来比较，确认插入方向
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }



            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                // 可以不覆盖因为相等，hash值是一样的
//                node.hash = h1;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        afterPut(newNode);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new Queue<>();
        for (Node<K, V> root : table) {
            if (root == null) continue;
            queue.enQueue(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.deQueue();
                if (Objects.equals(node.value, value)) return true;
                if (node.left != null) queue.enQueue(node.left);
                if (node.right != null) queue.enQueue(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;
        Queue<Node<K, V>> queue = new Queue<>();
        for (Node<K, V> root : table) {
            if (root == null) continue;
            queue.enQueue(root);
            while (!queue.isEmpty()) {
                if (visitor.stop()) return;
                Node<K, V> node = queue.deQueue();
                visitor.visit(node.key, node.value);
                if (node.left != null) queue.enQueue(node.left);
                if (node.right != null) queue.enQueue(node.right);
            }
        }

    }

    // 扩容
    private void resize(){
        // 填充因子 <= 0.75 不需要扩容挪动
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;

        // 扩容为原来的2倍
        Node<K,V> [] oldTable = table;
        table = new Node[oldTable.length << 1];

        // 遍历所有节点一一挪动，不是新建节点
        Queue<Node<K, V>> queue = new Queue<>();
        for (Node<K, V> root : oldTable) {
            if (root == null) continue;
            queue.enQueue(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.deQueue();
                if (node.left != null) queue.enQueue(node.left);
                if (node.right != null) queue.enQueue(node.right);

                // 将左右节点放入之后，再挪动
                moveNode(node);
            }
        }
    }


    // 挪动节点
    private void moveNode(Node<K,V> moveNode){
        // 将要挪动节点的关系全部斩断，变为初始新增的节点一样
        moveNode.parent = null;
        moveNode.left = null;
        moveNode.right = null;
        moveNode.color = RED;

        // 重新计算索引
        int index = index(moveNode.key);

        Node<K, V> root = table[index];
        if (root == null) {
            root = moveNode;
            afterPut(root);
            table[index] = root;
            return;
        }

        // 哈希冲突
        Node<K, V> parent;
        Node<K, V> node = root;
        int cmp;

        // 要挪动节点的key
        K k1 = moveNode.key;
        // 新要挪动key的hash值
        int h1 = hash(k1);
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;

            // 按照put的规则，来确认方向

            // hash值比较，用以确认桶内的红黑树挪动方向
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
                // 类型相同，具备可比较性，根据自带比较方法确认桶内红黑树挪动方向，并且要是不等于0情况，等于0按照不知道如何挪动情况处理
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
            } else {
                    // 没有任何方式来确认挪动方向，只能找个随机不唯一的方式来确认，那就是利用内存地址来比大小，来决定挪动的方向，内存地址没有负数所以不会溢出，不会出现异常
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }

        } while (node != null);

        // 看看挪动到父节点的哪个位置
        moveNode.parent = parent;
        if (cmp > 0) {
            parent.right = moveNode;
        } else {
            parent.left = moveNode;
        }
        afterPut(moveNode);
    }

    // 根据key计算出对应的索引（在桶数组的位置）
    private int index(K key) {
        // 取模，用位运算优化
        return hash(key) & (table.length - 1);
    }

    // hash函数 扰动计算
    private int hash(K key){
        // key为null，默认为0，会放在桶的0的下标位置
        int hashCode = key == null ? 0 : key.hashCode();
        // 因为不知道key实现的hashCode方法是否均匀，为了保险起见，使哈希散布均匀，再进行一次运算处理，也叫做扰动计算
        hashCode = hashCode ^ (hashCode >>> 16);
        return hashCode;
    }

    // 用于已存在的红黑树节点得到 其所在的索引
    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    // 哈希冲突，key比较
    private int compare(K k1, K k2, int h1, int h2) {
        // 比较hash值
        int result = h1 - h2;
        if (result != 0) return result;

        // hash值相等，判断key是否相等equals
        if (Objects.equals(k1, k2)) return 0;

        // hash值相等，两个key不相等equals，借助其他方式来比较
        // 判断类名
        if (k1 != null && k2 != null) {
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            // 按照类名比较
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) return result;

            // 是同一种类型并且具备可比较性，交给他们自己比较
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // hash值相等，同一种类型，不具备可比较性
        // k1为null，k2不为null || k1不为null，k2为null

        // 上面这种情况，hash值相等，对象不相等equals，也不具备可比较性，只能使用内存地址来比较，看向左还是向右
        // 内存地址比较有随机不确定性，会导致结果不稳定
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    // 寻找节点
    private Node<K, V> node(K key) {
        // 根据索引找到根节点
        Node<K, V> root = table[index(key)];
        return node(root, key);
    }

    // 根据key寻找该节点与子树有没有，key相等的节点
    private Node<K, V> node(Node<K, V> node, K k1) {
        if (node == null) return null;
        int h1 = hash(k1);
        Node<K, V> result;
        int cmp;
        do {
            int h2 = node.hash;
            K k2 = node.key;

            // 按照put的规则，来确认方向

            // hash值直接比较，用以确认桶内的红黑树寻找方向  （因为使用减法，可能会溢出，从而导致异常结果） （hash不相同，对象一定不相等）
                if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;

                // hash值相同，进一步判断key对象是否相等equals，用以命中目标  （hash相同，对象不一定相等）
            } else if (Objects.equals(k1, k2)) {
                return node;

                // 更新！！！类型不同，也当作是无法寻找方向的情况，扫描处理

//                // hash相同，key对象不相等equals，类型不同，根据类名确认桶内红黑树寻找方向
//            } else if (k1 != null && k2 != null && k1.getClass() != k2.getClass()) {
//                String k1ClsName = k1.getClass().getName();
//                String k2ClsName = k2.getClass().getName();
//                cmp = k1ClsName.compareTo(k2ClsName);
//                if (cmp > 0) {
//                    node = node.right;
//                } else if (cmp < 0) {
//                    node = node.left;
//                }

                // hash相同，key对象不相等equals，类型相同，并且具备可比较性，根据自带比较方法确认桶内红黑树寻找方向（比较得是不等于的情况，如果是等于则和equals冲突，等于当做是无法寻找方向的情况）
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;

                // hash相同，key对象不相等equals，类型相同，并且不具备可比较性，没有任何方式来确认桶内红黑树寻找方向了，也就是无法寻找方向，只能进行一次完整红黑树扫描，看看能不能找到节点equals情况，找到了就返回，找不到就结束
            } else if (node.left != null && (result = node(node.left, k1)) != null) {
                return result;
            } else {
                    // 扫描，往左边找
                node = node.right;
            }
        } while (node != null);

        return null;
    }

    private V remove(Node<K, V> node) {
        if (node == null) return null;
        // 长度--
        size--;

        V oldValue = node.value;

        // 度为2的节点
        if (node.hasTwoChild()) {
            // 找到前驱节点
            Node<K, V> predecessor = predecessor(node);
            // 用前驱节点的值覆盖，度为2节点的值
            node.key = predecessor.key;
            node.value = predecessor.value;
            node.hash = predecessor.hash;
            // 删除前驱节点
            node = predecessor;
        }

        // 删除node节点

        // 替代节点 是删除节点的子节点
        Node<K, V> alternateNode = node.left != null ? node.left : node.right;

        int index = index(node);

        // 度为1的节点  将子节点（替代节点）的parent指向删除节点的父节点，将父节点的左或右子树指向（根据原本的指向关系来决定）删除节点的子节点（替代节点）
        if (alternateNode != null) {
            // 替代节点的父节点 指向 删除节点的父节点
            alternateNode.parent = node.parent;

            // 度为1 并且是根节点
            if (node.parent == null) {
                // 根节点直接指向删除节点的下级
                table[index] = alternateNode;
            } else if (node.parent.left == node) {
                node.parent.left = alternateNode;
            } else {
                node.parent.right = alternateNode;
            }
        } else if (node.parent == null) {
            // 叶子节点，并且是根节点，直接设置根节点为NULL
            table[index] = null;
        } else {
            // 叶子节点，并且不是根节点，将父节点指向删除节点的关系置为null
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

        }

        afterRemove(node);
        return oldValue;
    }


    /**
     * 红黑树代码
     */

    // 寻找前驱节点
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) return null;

        // 前驱
        Node<K, V> pre = node.left;

        // 前驱节点在左子树当中 （left.right.right.right......)
        if (pre != null) {
            while (pre.right != null) {
                pre = pre.right;
            }
            return pre;
        }

        // 前驱节点在其父、祖父中 (parent.parent.parent....... && parent.right = node)
        while (node.parent != null && node.parent.right != node) {
            node = node.parent;
        }

        return node.parent;
    }

    // 增加后修复红黑树性质
    private void afterPut(Node<K, V> node) {

        Node<K, V> parent = node.parent;

        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)) return;

        Node<K, V> grand = parent.parent;
        Node<K, V> uncle = parent.sibling();

        if (isRed(uncle)) {

            black(parent);
            red(grand);
            black(uncle);

            // 递归去修复
            afterPut(grand);
            return;
        }


        if (node.isLeftChildOfParent()) {
            if (parent.isLeftChildOfParent()) {
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }
        } else if (node.isRightChildOfParent()) {
            if (parent.isRightChildOfParent()) {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            } else {
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        }


    }

    // 删除后修复红黑树性质
    private void afterRemove(Node<K, V> node) {
        if (isRed(node)) return;
        if (!node.isRightChildOfParent() && !node.isLeftChildOfParent()) {
            Node<K, V> replaceNode = node.left == null ? node.right : node.left;
            if (isRed(replaceNode)) {
                black(replaceNode);
                return;
            }
        }


        Node<K, V> parent = node.parent;
        if (parent == null) return;

        Node<K, V> sibling = node.isLeftChildOfParent() || node.parent.left == null ? node.parent.right : node.parent.left;
        if (isBlack(sibling)) {
            if (isRed(sibling.left)) {
                if (sibling.isLeftChildOfParent()) {
                    rotateRight(parent);

                    black(sibling.left);
                    color(sibling, parent.color);
                    black(parent);


                } else {
                    rotateRight(sibling);
                    rotateLeft(parent);

                    black(sibling);
                    color(sibling.left, parent.color);
                    black(parent);
                }
            } else if (isRed(sibling.right)) {
                if (sibling.isLeftChildOfParent()) {
                    rotateLeft(sibling);
                    rotateRight(parent);

                    black(sibling);
                    color(sibling.right, parent.color);
                    black(parent);


                } else {
                    rotateLeft(parent);

                    black(sibling.right);
                    color(sibling, parent.color);
                    black(parent);
                }
            } else {
                red(sibling);
                if (isBlack(parent)) {
                    afterRemove(parent);
                    return;
                }
                black(parent);
            }

        } else {
            if (sibling.isLeftChildOfParent()) {
                rotateRight(parent);

            } else {
                rotateLeft(parent);
            }

            red(parent);
            black(sibling);
            afterRemove(node);
        }

    }

    // 左旋转
    private void rotateLeft(Node<K, V> node) {
        Node<K, V> rrsNode = node.right;
        Node<K, V> resNode = rrsNode.left;
        node.right = resNode;
        rrsNode.left = node;
        afterRotate(node, rrsNode, resNode);
    }

    // 右旋转
    private void rotateRight(Node<K, V> node) {
        Node<K, V> rrsNode = node.left;
        Node<K, V> resNode = rrsNode.right;
        node.left = resNode;
        rrsNode.right = node;
        afterRotate(node, rrsNode, resNode);
    }


    // 旋转后置操作，维护节点关系
    private void afterRotate(Node<K, V> rNode, Node<K, V> rrsNode, Node<K, V> resNode) {
        rrsNode.parent = rNode.parent;

        if (rNode.isLeftChildOfParent()) {
            rrsNode.parent.left = rrsNode;
        } else if (rNode.isRightChildOfParent()) {
            rrsNode.parent.right = rrsNode;
        } else {
            // root = rrsNode，root = 旋转节点rNode的hash计算出来的索引值，根据索引找到table里的root，他们计算都是同一个索引
            table[index(rNode)] = rrsNode;
        }

        rNode.parent = rrsNode;

        if (resNode != null) {
            resNode.parent = rNode;
        }
    }

    // 统一旋转
    private void rotate(Node<K, V> r,
                        Node<K, V> a, Node<K, V> b, Node<K, V> c,
                        Node<K, V> d,
                        Node<K, V> e, Node<K, V> f, Node<K, V> g) {

        // bdf子树
        d.left = b;
        d.right = f;
        d.parent = r.parent;
        if (r.isLeftChildOfParent()) {
            r.parent.left = d;
        } else if (r.isRightChildOfParent()) {
            r.parent.right = d;
        } else {
            // root = d，root = 旋转节点r的hash计算出来的索引值，根据索引找到table里的root，他们计算都是同一个索引
            table[index(r)] = d;
        }
        b.parent = d;
        f.parent = d;

        // abc子树
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;

        // efg子树
        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;
        //染色
        black(d);

        black(a);
        red(b);
        black(c);

        black(e);
        red(f);
        black(g);

    }

    // 染色
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    // 染红色
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    // 染黑色
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    // 节点颜色
    private boolean colorOf(Node<K, V> node) {
        // 如果是null的话 代表空节点
        if (node == null) return BLACK;
        return node.color;
    }

    // 是否红色节点
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    // 是否黑色节点
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }


    // 红黑树节点
    class Node<K, V> {
        private boolean color = RED;
        // 每个节点key的hash值
        int hash;
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hashCode = key == null ? 0 : key.hashCode();
            // 扰动计算
            this.hash = hashCode ^ (hashCode >>> 16);
            this.value = value;
            this.parent = parent;
        }

        // 是否叶子节点
        public boolean isLeaf() {
            return left == null && right == null;
        }

        // 是否同时具有两个子树（子节点）
        public boolean hasTwoChild() {
            return left != null && right != null;
        }

        // 是否是父节点的左子节点
        public boolean isLeftChildOfParent() {
            return parent != null && parent.left == this;
        }

        // 是否是父节点的右子节点
        public boolean isRightChildOfParent() {
            return parent != null && parent.right == this;
        }

        // 得到兄弟节点
        public Node<K, V> sibling() {
            if (isLeftChildOfParent()) {
                return parent.right;
            } else if (isRightChildOfParent()) {
                return parent.left;
            } else {
                return null;
            }
        }
    }
}
