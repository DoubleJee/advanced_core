package datastructure.other.map;

import datastructure.tree.BinaryTree;
import datastructure.tree.binary.RBTree;

import java.util.concurrent.atomic.AtomicBoolean;
// Tree映射 红黑树实现
public class TreeMap<K, V> implements Map<K, V> {

    private RBTree<Node<K, V>> tree = new RBTree<>();

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
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key必须不能为空");
        }
        Node<K, V> node = new Node<>(key, value);
        BinaryTree.Node<Node<K, V>> targetNode = tree.node(node);
        if (targetNode != null) {
            targetNode.element.key = key;
            V oldValue = targetNode.element.value;
            targetNode.element.value = value;
            return oldValue;
        } else {
            tree.add(node);
        }
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) return null;
        Node<K, V> node = new Node<>(key, null);
        BinaryTree.Node<Node<K, V>> targetNode = tree.node(node);
        if (targetNode != null) {
            return targetNode.element.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) return null;
        Node<K, V> node = new Node<>(key, null);
        BinaryTree.Node<Node<K, V>> targetNode = tree.node(node);
        if (targetNode != null) {
            tree.remove(node);
            return targetNode.element.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) return false;
        Node<K, V> node = new Node<>(key, null);
        BinaryTree.Node<Node<K, V>> targetNode = tree.node(node);
        return targetNode != null;
    }

    @Override
    public boolean containsValue(V value) {
        AtomicBoolean result = new AtomicBoolean(false);
        tree.levelOrder(new BinaryTree.Visitor<Node<K, V>>() {
            private boolean stop = false;
            @Override
            public boolean stop() {
                return stop;
            }

            @Override
            public void visit(Node<K, V> element) {
                if (element.value == null && value == null || element.value.equals(value)) {
                    result.set(true);
                    stop = true;
                }
            }
        });
        return result.get();
    }

    @Override
    public void traversal(Visitor<K,V> visitor) {
        tree.inorder(new BinaryTree.Visitor<Node<K, V>>() {
            @Override
            public boolean stop() {
                return visitor.stop();
            }

            @Override
            public void visit(Node<K, V> element) {
                visitor.visit(element.key,element.value);
            }
        });
    }
}
