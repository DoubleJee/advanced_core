package datastructure.other.set;

import datastructure.tree.BinaryTree;
import datastructure.tree.binary.RBTree;

// Tree集合 红黑树实现
public class TreeSet<E extends Comparable> implements Set<E> {

    private BinaryTree<E> tree = new RBTree<E>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        // 树已经实现了，重复元素，不增加
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        tree.inorder(visitor);
    }
}
