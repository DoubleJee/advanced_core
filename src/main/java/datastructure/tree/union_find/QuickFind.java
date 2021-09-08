package datastructure.tree.union_find;

import datastructure.tree.UnionFind;

// 快查 并查集
public class QuickFind implements UnionFind {

    // 使用数组来表示并查集   （索引代表元素，值代表父元素）
    private int [] parents;

    public QuickFind(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be > 0");

        parents = new int[capacity];
        // 初始化状态，每个元素一开始独占一个集合
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

    }

    // 查找v所在的集合 （根节点代表）
    public int find(int v) {
        rangeCheck(v);
        // 每个元素的父节点就是根节点
        return parents[v];
    }

    // 合并v1和v2所在的集合 （默认左边的跟随右边的）
    public void union(int v1, int v2) {
        // 查找v1所在集合
        int r1 = find(v1);
        // 查找v2所在集合
        int r2 = find(v2);
        // 在同一个集合，不需要合并
        if (r1 == r2) return;

        // 将所有在r1集合的，合并到r2集合
        for (int i = 0; i < parents.length; i++) {
            // r1集合元素全部指向r2根节点，完成合并，控制到只有两层
            if (parents[i] == r1) {
                parents[i] = r2;
            }
        }
        // O(n)
    }

    // 查看v1和v2是否所在一个集合
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }


    // 检查操作值，是否大于范围
    private void rangeCheck(int v){
        if (v < 0 || v >= parents.length) throw new IllegalArgumentException("v is out of bounds");
    }
}
