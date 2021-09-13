package datastructure.tree.union_find;

// 快查 并查集
public class QuickFind extends  AbstractUnionFind{

    public QuickFind(int capacity) {
        super(capacity);
    }

    public int find(int v) {
        rangeCheck(v);
        // 每个元素的父节点就是根节点
        return parents[v];
    }

    // 将v1所在集合的所有元素，都嫁接到v2的根节点上
    public void union(int v1, int v2) {
        // 查找v1、v2所在集合
        int r1 = find(v1);
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // 将所有在r1集合的，合并到r2集合
        for (int i = 0; i < parents.length; i++) {
            // r1集合所有元素（包括根节点）父节点全部指向r2根节点，完成合并，都是直属关系，控制高度最高为2
            if (parents[i] == r1) {
                parents[i] = r2;
            }
        }
        // O(n)
    }
}
