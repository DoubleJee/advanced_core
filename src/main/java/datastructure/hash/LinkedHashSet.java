package datastructure.hash;

import datastructure.other.map.Map;
import datastructure.other.set.Set;
import datastructure.tree.BinaryTree;

public class LinkedHashSet<E> implements Set<E> {

    private Map<E,Object> linkedHashMap = new LinkedHashMap<>();

    @Override
    public int size() {
        return linkedHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedHashMap.isEmpty();
    }

    @Override
    public void clear() {
        linkedHashMap.clear();
    }

    @Override
    public boolean contains(E element) {
        return linkedHashMap.containsKey(element);
    }

    @Override
    public void add(E element) {
        linkedHashMap.put(element,null);
    }

    @Override
    public void remove(E element) {
        linkedHashMap.remove(element);
    }

    @Override
    public void traversal(BinaryTree.Visitor<E> visitor) {
        linkedHashMap.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean stop() {
                return visitor.stop();
            }

            @Override
            public void visit(E key, Object value) {
                visitor.visit(key);
            }
        });
    }
}
