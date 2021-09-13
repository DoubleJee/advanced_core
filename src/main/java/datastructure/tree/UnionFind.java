package datastructure.tree;

// 并查集
public interface UnionFind {

    // 查找v所在的集合 （根节点代表）（默认左边的跟随右边的）
    int find(int v);

    // 合并v1和v2所在的集合
    void union(int v1, int v2);

    // 查看v1和v2是否所在一个集合
    boolean isSame(int v1, int v2);
}
