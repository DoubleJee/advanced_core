package datastructure.tree.union_find;

public class QuickUnion extends AbstractUnionFind {

    public QuickUnion(int capacity) {
        super(capacity);
    }

    /*
     * 索引是元素，数父组值是元素，根节点的父元素是自己
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        // 当前节点不是根节点，向上寻找
        while (parents[v] != v) {
            // 将父节点的值赋给v
           v = parents[v];
        }

        // 最后找到根节点
        return v;
    }

    // 将v1的根节点，嫁接到v2的根节点上
    @Override
    public void union(int v1, int v2) {
        // 查找v1、v2所在集合
        int r1 = find(v1);
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // r1根节点的父节点指向r2   （默认 左边根节点的父节点指向右边根节点，完成合并）
        parents[r1] = r2;
    }
}
