package datastructure.tree.union_find;

// 基于Rank + 路径压缩（Path Compression）的优化，降低高度 快并集
public class QuickUnion_Rank_PC extends QuickUnion_Rank {

    public QuickUnion_Rank_PC(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        if (parents[v] != v) {
            // 如果不是根节点，则将向上寻找路径的所有节点都指向根节点
            // 将自己的父节点改为根节点
            parents[v] = find(parents[v]);
        }
        // 返回自己的父节点（根节点）
        return parents[v];
    }
}
