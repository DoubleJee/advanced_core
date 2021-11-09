package datastructure.other.skip_list;

import java.util.Comparator;

// 跳表
public class SkipList<K, V> {

    // 借鉴Redis的设定，最大层数为32层
    private static int MAX_LEVEL = 32;

    // 借鉴Redis的设定，抛硬币，四分之一的几率，继续增加层数
    private static double P = 0.25;

    // 大小
    private int size;

    // 虚拟头节点
    private Node<K, V> first;

    // 有效层数
    private int level;

    // 比较器
    private Comparator<K> comparator;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        // 初始化头节点，默认最大层级
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 查询
    public V get(K key) {
        keyCheck(key);

        // 从最顶层首节点开始搜索
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0 ; i--) {
            int cmp = -1;

            // 当前元素 小于 查找元素 并且 没到当前层级的尾部    （层级尾部可以当作一个非常大的元素，它 大于 查找元素）
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                // 继续向右寻找       （要查找的元素在当前元素的右边）
                node = node.nexts[i];
            }

            // 当前元素 等于 查找元素，查询到了
            if (cmp == 0) return node.nexts[i].value;

            // 当前元素 大于 查找元素 或者 达到当前层级的尾部，代表目标元素在上一个元素和该元素之间，返回上一个元素节点，转入下一层进行搜索   （因为都是拿的node.next进行比较，所以node就是上一个元素节点）
        }

        // 到最后一层还要往下，没有再下面的层级了，区间没有元素，则没找到
        return null;
    }

    // 添加
    public V put(K key, V value) {
        keyCheck(key);

        // 要添加元素的各层前驱节点
        Node<K, V>[] prevs = new Node[level];

        // 从最顶层首节点开始，查找每一层的前驱节点，用于添加，并查询该元素是否已经存在
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0 ; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                node = node.nexts[i];
            }
            // 该元素节点已经存在
            if (cmp == 0) {
                // 直接替换
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }

            // 记录每层的前驱节点
            prevs[i] = node;
        }



        // 抛硬币决定新节点的层级
        int newLevel = randomLevel();
        // 创建新节点
        Node<K, V> newNode = new Node<>(key, value, newLevel);

        // 放入各层
        for (int i = 0; i < newLevel; i++) {
            // 如果新节点层数高于有效层数，那么在高出的这些层，它的前驱节点就是first    （因为在那些层它是唯一的元素）
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }

        // 更新有效层数   （层数可能变高）
        level = Math.max(newLevel, level);

        size++;
        return null;
    }

    // 删除
    public V remove(K key) {
        keyCheck(key);

        // 要删除元素的各层前驱节点
        Node<K, V>[] prevs = new Node[level];

        // 从最顶层首节点开始，查找每一层的前驱节点，用于删除，并查询该元素是否存在
        boolean exist = false;
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0 ; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = cmp(key, node.nexts[i].key)) > 0){
                node = node.nexts[i];
            }
            // 该元素节点存在
            if (cmp == 0) exist = true;

            // 记录每层的前驱节点
            prevs[i] = node;
        }

        // 该元素节点不存在，则不用删除，结束
        if (!exist) return null;


        // 要删除节点就是，找到的最后一层前驱节点的next （最底层）
        Node<K, V> removeNode = node.nexts[0];

        // 删除各层     （删除节点所在的那些层）
        for (int i = removeNode.nexts.length - 1; i >= 0; i--) {
            prevs[i].nexts[i] = prevs[i].nexts[i].nexts[i];   // prevs[i].nexts[i] = removeNode.nexts[i];

            // 更新有效层数   （层数可能变低）
            if (first.nexts[i] == null) {
                // 如果删除了这个节点，就只有first 和 null，则这层就可以去除，有效层数--
                level--;
            }
        }

        size--;
        return removeNode.value;
    }


    // 抛硬币随机生成层数
    private int randomLevel() {
        int newLevel = 1;
        // 抛硬币决定层数，几率P为四分之一
        while (size != 0 && newLevel < MAX_LEVEL && Math.random() < P) {
            newLevel++;
        }
        return newLevel;
    }

    // 比较
    private int cmp(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable<K>)k1).compareTo(k2);
    }

    // key检查
    private void keyCheck(K key) {
        if (key == null) throw new IllegalArgumentException(" key must not be null.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("层级：").append(level).append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            sb.append("first → ");
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i].value).append(" → ");
                node = node.nexts[i];
            }
            sb.append("null").append("\n");
        }
        return sb.toString();
    }

    private static class Node<K, V> {
        K key;
        V value;
        // 每一层的下一个节点
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }


    /**
     *  // 添加
     *     public V put(K key, V value) {
     *         keyCheck(key);
     *
     *         // 如果已经存在
     *         Node<K, V> findNode = getNode(key);
     *         if (findNode != null) {
     *             // 直接替换
     *             V oldValue = findNode.value;
     *             findNode.value = value;
     *             return oldValue;
     *         }
     *
     *         int level = 1;
     *         while (size != 0 && Math.random() > 0.5) {
     *             level++;
     *         }
     *
     *         // 更新有效层级
     *         if (level > this.level) {
     *             this.level = level;
     *         }
     *
     *         Node<K, V> node = first;
     *         Node<K, V> newNode = new Node<>(key, value, level);
     *
     *         // 放入每一层
     *         for (int i = level - 1; i >= 0; i--) {
     *             while (node.nexts[i] != null && cmp(key, node.nexts[i].key) > 0) {
     *                 node = node.nexts[i];
     *             }
     *
     *             Node<K, V> oldNext = newNode.nexts[i];
     *             newNode.nexts[i] = oldNext;
     *             node.nexts[i] = newNode;
     *         }
     *         size++;
     *         return value;
     *     }
     *
     *     // 删除
     *     public V remove(K key) {
     *         keyCheck(key);
     *
     *         // 如果不存在
     *         Node<K, V> findNode = getNode(key);
     *         if (findNode == null) {
     *             return null;
     *         }
     *
     *
     *         // 删除每一层
     *         Node<K, V> node = first;
     *         for (int i = findNode.level - 1; i >= 0; i--) {
     *             while (cmp(key, node.nexts[i].key) != 0) {
     *                 node = node.nexts[i];
     *             }
     *
     *             // 更新有效层级
     *             if (node == first && node.nexts[i].nexts[i] == null) {
     *                 level--;
     *             }
     *
     *             node.nexts[i] = node.nexts[i].nexts[i];
     *         }
     *
     *         size--;
     *         return findNode.value;
     *     }
     */
}
