package datastructure.other.set;

import datastructure.other.map.Map;
import datastructure.other.map.TreeMap;
import datastructure.tree.BinaryTree;

// Map集合 TreeMap实现（红黑树）
public class MapSet<E> implements Set<E> {

    private Map<E,E> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element,null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, E>() {
            @Override
            public boolean stop() {
                return visitor.stop();
            }

            @Override
            public void visit(E key, E value) {
                visitor.visit(key);
            }
        });
    }
}
