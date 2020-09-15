package datastructure.other.set;

import datastructure.tree.BinaryTree;
// 集合 存放的元素不重复
public interface Set<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(BinaryTree.Visitor<E> visitor);

}
