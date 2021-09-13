package datastructure.tree.union_find;

// 基于Rank + 路径减半（Path Halving）的优化，降低高度，成本相对低 快并集
public class QuickUnion_Rank_PH extends QuickUnion_Rank {

    public QuickUnion_Rank_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        // 向上寻找路径每隔一个节点都指向其祖父节点
        while (parents[v] != v) {
            // 指向祖父节点
            parents[v] = parents[parents[v]];

            // 隔一个节点向上寻找
            v = parents[v];
        }

        // 最后找到根节点
        return v;
    }
}
