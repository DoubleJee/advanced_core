package datastructure.other.set;

import datastructure.linear.List;
import datastructure.linear.linked.LinkedList;
import datastructure.tree.BinaryTree;
// List集合 用链表实现
public class ListSet<E> implements Set<E> {

    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // 重复元素不增加
        if (contains(element)) return;
        list.add(element);
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.EXCEPTION_NOT_FIND){
            list.remove(index);
        }
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.stop()) return;
            visitor.visit(list.get(i));
        }
    }
}
