package datastructure.tree.union_find;

import datastructure.tree.UnionFind;

// 抽象并查集 公共属性、逻辑
public abstract class AbstractUnionFind implements UnionFind {

    // 使用数组来表示并查集   （索引 代表元素，值 代表父元素）
    protected int [] parents;

    public AbstractUnionFind(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be > 0");

        parents = new int[capacity];
        // 初始化状态，每个元素一开始独占一个集合
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }


    // 查看v1和v2是否所在一个集合
    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    // 检查操作值，是否大于范围
    protected void rangeCheck(int v){
        if (v < 0 || v >= parents.length) throw new IllegalArgumentException("v is out of bounds");
    }
}
