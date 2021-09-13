package datastructure.tree.union_find;

import datastructure.AssertTool;
import datastructure.TimeTool;
import datastructure.tree.UnionFind;

public class UnionFindTest {

    static final int COUNT = 10000000;

    public static void main(String[] args) {
        testTime(new QuickUnion_Size(COUNT));
        testTime(new QuickUnion_Rank(COUNT));
        testTime(new QuickUnion_Rank_PC(COUNT));
        testTime(new QuickUnion_Rank_PS(COUNT));
        testTime(new QuickUnion_Rank_PH(COUNT));

    }

    static void testTime(UnionFind uf) {
        uf.union(0, 1);
        uf.union(0, 4);
        uf.union(0, 3);
        uf.union(3, 2);
        uf.union(3, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(8, 9);
        uf.union(9, 11);

        AssertTool.test(uf.isSame(0, 4));
        AssertTool.test(!uf.isSame(0, 6));
        uf.union(5, 7);
        AssertTool.test(uf.isSame(0, 6));

        TimeTool.checkTime(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < COUNT; i++) {
                uf.union((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
            }

            for (int i = 0; i < COUNT; i++) {
                uf.isSame((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
            }
        });
    }
}
