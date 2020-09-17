package datastructure.hash;

import datastructure.other.map.Map;
import datastructure.tree.BinaryTree;

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
        // 添加新的节点到红黑树上面
        Node<K, V> parent = null;
        Node<K, V> node = root;
        int cmp = 0;
        // 要放入key的hash值
        int h1 = key == null ? 0 : key.hashCode();
        do {

            parent = node;
            // 比较
            cmp = compare(key, node.key, h1, node.hash);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
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
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }


    // 根据key计算出对应的索引（在桶数组的位置）
    private int index(K key) {
        // key为null，默认为0，会放在桶的0的下标位置
        int hashCode = key == null ? 0 : key.hashCode();
        // 因为不知道key实现的hashCode方法是否均匀，为了保险起见，使哈希散布均匀，再进行一次运算处理
        hashCode = hashCode ^ (hashCode >>> 16);
        // 取模，用位运算优化
        return hashCode & (table.length - 1);
    }

    // 用于已存在的红黑树节点得到 其所在的索引
    private int index(Node<K,V> node) {
        int hashCode = node.hash;
        hashCode = hashCode ^ (hashCode >>> 16);
        return hashCode & (table.length - 1);
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
        if (k1 != null && k2 != null){
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            // 按照类名比较
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) return result;

            // 是同一种类型并且具备可比较性，交给他们自己比较
            if (k1 instanceof Comparable){
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // hash值相等，同一种类型，不具备可比较性
        // k1为null，k2不为null || k1不为null，k2为null

        // 上面这种情况，hash值相等，对象不相等equals，也不具备可比较性，只能使用内存地址来比较
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    // 寻找节点
    private Node<K,V> node(K key){
        // 根据索引找到节点
        Node<K,V> node = table[index(key)];
        int h1 = key == null ? 0 : key.hashCode();
        int cmp;
        while (node != null){
            cmp = compare(key,node.key,h1,node.hash);
            if (cmp == 0){
                return node;
            }else if (cmp < 0){
                node = node.left;
            }else {
                node = node.right;
            }
        }
        return null;
    }

    private V remove(Node<K,V> node){
        if (node == null) return null;
        // 长度--
        size--;

        V oldValue = node.value;

        // 度为2的节点
        if (node.hasTwoChild()){
            // 找到前驱节点
            Node<K,V> predecessor = predecessor(node);
            // 用前驱节点的值覆盖，度为2节点的值
            node.key = predecessor.key;
            node.value = predecessor.value;
            node.hash = predecessor.hash;
            // 删除前驱节点
            node = predecessor;
        }

        // 删除node节点

        // 替代节点 是删除节点的子节点
        Node<K,V> alternateNode = node.left != null ? node.left : node.right;

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
        }else if (node.parent == null){
            // 叶子节点，并且是根节点，直接设置根节点为NULL
            table[index] = null;
        }else {
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
    private Node<K,V> predecessor(Node<K,V> node){
        if (node == null) return null;

        // 前驱
        Node<K,V> pre = node.left;

        // 前驱节点在左子树当中 （left.right.right.right......)
        if (pre != null){
            while (pre.right != null){
                pre = pre.right;
            }
            return pre;
        }

        // 前驱节点在其父、祖父中 (parent.parent.parent....... && parent.right = node)
        while (node.parent != null && node.parent.right != node){
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
            this.hash = key == null ? 0 : key.hashCode();
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
