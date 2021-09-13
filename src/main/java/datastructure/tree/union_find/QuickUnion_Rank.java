package datastructure.tree.union_find;

// 基于Rank高度的优化，保证平衡 快并集
public class QuickUnion_Rank extends QuickUnion {

    // 记录每个集合的高度
    private int [] ranks;

    public QuickUnion_Rank(int capacity) {
        super(capacity);

        // 每个集合初始化状态，高度都是1
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }


    @Override
    public void union(int v1, int v2) {
        // 查找v1、v2所在集合
        int r1 = find(v1);
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // 高度低的树 嫁接到 高度高的树
        if (ranks[r1] < ranks[r2]) {
            parents[r1] = r2;
        } else if (ranks[r2] < ranks[r1]) {
            parents[r2] = r1;
        } else {
            // 高度一样，嫁接后，高度+1
            parents[r1] = r2;
            ranks[r2]++;
            ranks[r1] = 0;
        }
    }
}
