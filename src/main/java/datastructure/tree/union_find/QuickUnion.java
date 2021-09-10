package datastructure.tree.union_find;

public class QuickUnion extends AbstractUnionFind {

    public QuickUnion(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        // 当前节点不是根节点
        while (parents[v] != v) {
            // 将父节点的值赋给v
           v = parents[v];
        }

        // 最后找到根节点
        return v;
    }

    @Override
    public void union(int v1, int v2) {
        // 查找v1、v2所在集合
        int r1 = find(v1);
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // r1根节点的父节点指向r2   （左边根节点的父节点指向右边根节点，完成合并）
        parents[r1] = r2;
    }
}
