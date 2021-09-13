package datastructure.tree.union_find;

// 基于Size的优化，保证平衡 快并集
public class QuickUnion_Size extends QuickUnion {

    // 记录每个集合的size
    private int [] sizes;

    public QuickUnion_Size(int capacity) {
        super(capacity);

        // 每个集合初始化状态，元素数量都是1
        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        // 查找v1、v2所在集合
        int r1 = find(v1);
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // 元素少的树 嫁接到 元素多的树
        if (sizes[r1] < sizes[r2]) {
            parents[r1] = r2;
            sizes[r2] += sizes[r1];
            sizes[r1] = 0;
        } else {
            parents[r2] = r1;
            sizes[r1] += sizes[r2];
            sizes[r2] = 0;
        }
    }
}
