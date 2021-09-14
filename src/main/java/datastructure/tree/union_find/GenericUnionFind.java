package datastructure.tree.union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 通用并查集 Quick Union + Rank + 路径减半Halving    （比较都是基于节点的值，判断根节点，是节点的值和父节点的值是否相等）
public class GenericUnionFind<V> {

    // Value和节点的映射
    private Map<V, Node<V>> nodes = new HashMap<>();

    // 初始化一个集合
    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }

    public V find(V v) {
        Node<V> root = findRoot(v);
        return root == null ? null : root.value;
    }

    public void union(V v1, V v2) {
        // 查找v1、v2所在集合
        Node<V> r1 = findRoot(v1);
        Node<V> r2 = findRoot(v2);
        // 有一个不存在的集合，则不需要合并
        if (r1 == null || r2 == null) return;

        // 在同一个集合，不需要合并
        if (Objects.equals(r1.value, r2.value)) return;

        // 基于rank的合并
        if (r1.rank < r2.rank) {
            r1.parent = r2;
            r1.rank = 0;
        } else if (r2.rank < r1.rank) {
            r2.parent = r1;
            r2.rank = 0;
        } else {
            // 高度一样，嫁接后，高度+1
            r1.parent = r2;
            r2.rank++;
            r1.rank = 0;
        }
    }

    public boolean isSame(V v1, V v2){
        return Objects.equals(find(v1), find(v2));
    }

    // 查找v所在集合的根节点
    private Node<V> findRoot(V v) {
        // 找到v对应的节点，通过节点的链，寻找根节点
        Node<V> node = nodes.get(v);
        if (node == null) return null;
        // 路径减半
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            // 从祖父继续向上寻找
            node = node.parent;
        }
        // 最后找到根节点
        return node;
    }

    private static class Node<V> {
        V value;
        Node<V> parent = this;
        // 高度
        int rank = 1;
        Node(V value){
            this.value = value;
        }
    }
}
