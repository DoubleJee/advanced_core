package datastructure.tree.union_find;

public class UnionFindTest {

    public static void main(String[] args) {
        QuickFind uf = new QuickFind(12);

        uf.union(0,1);
        uf.union(0,4);
        uf.union(0,3);
        uf.union(3,2);
        uf.union(3,5);

        uf.union(6,7);

        uf.union(8,10);
        uf.union(8,9);
        uf.union(9,11);

        System.out.println(uf.find(3) == 5);
        System.out.println(uf.isSame(0, 4));
        System.out.println(uf.isSame(0, 6));

        uf.union(5,7);

        System.out.println(uf.isSame(0, 6));


    }
}
