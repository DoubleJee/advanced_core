package datastructure.tree.union_find;

// 基于Rank + 路径分裂（Path Spliting）的优化，降低高度，成本相对低 快并集
public class QuickUnion_Rank_PS extends QuickUnion_Rank {

    public QuickUnion_Rank_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        // 将向上寻找路径的每个节点都指向其祖父节点
        while (parents[v] != v) {
            int parent = parents[v];
            // 指向其祖父节点（父节点的父节点）
            parents[v] = parents[parent];
            // 向上寻找
            v = parent;
        }

        // 最后找到根节点
        return v;
    }
}
