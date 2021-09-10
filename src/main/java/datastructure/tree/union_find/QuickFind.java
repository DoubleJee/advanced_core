package datastructure.tree.union_find;

// 快查 并查集
public class QuickFind extends  AbstractUnionFind{

    public QuickFind(int capacity) {
        super(capacity);
    }

    // 查找v所在的集合 （根节点代表）
    public int find(int v) {
        rangeCheck(v);
        // 每个元素的父节点就是根节点
        return parents[v];
    }

    // 合并v1和v2所在的集合 （默认左边的跟随右边的）（左边根节点和其子节点，全部指向右边根节点，合并）
    public void union(int v1, int v2) {
        // 查找v1所在集合
        int r1 = find(v1);
        // 查找v2所在集合
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // 将所有在r1集合的，合并到r2集合
        for (int i = 0; i < parents.length; i++) {
            // r1集合元素（包括根节点）父节点全部指向r2根节点，完成合并，都是直属关系，控制高度最高为2
            if (parents[i] == r1) {
                parents[i] = r2;
            }
        }
        // O(n)
    }
}
