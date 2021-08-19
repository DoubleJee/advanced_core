package datastructure.tree.multi;

import datastructure.hash.HashMap;
import datastructure.other.map.Map;

// Trie 字典树、多叉树、单词查找树
public class Trie<V> {

    private int size;

    private Node<V> root = new Node<>(null, null);

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear(){
        size = 0;
        root.getChildren().clear();
    }

    // 是否包含某个字符串
    public boolean contains(String key) {
        Node<V> node = node(key);
        // 有这个节点并且是一个完整单词
        return node != null && node.word;
    }

    public V add(String key, V value) {
        keyCheck(key);
//        if (root == null) {
//            // 初始化根节点
//            root = new Node<>();
//            root.children = new HashMap<>();
//        }
//
//        Node<V> node = root;
//        int len = key.length();
//        for (int i = 0; i < len; i++) {
//            char c = key.charAt(i);
//            if (node.children == null) {
//                node.children = new HashMap<>();
//            }
//            Node<V> nextNode = node.children.get(c);
//            if (nextNode == null) {
//                nextNode = new Node<>();
//                node.children.put(c, nextNode);
//            }
//
//            node = nextNode;
//        }
//
//
//        // 如果已经存在这个单词
//        if (node.word) {
//            V oldValue = node.value;
//            node.value = value;
//            return oldValue;
//        }
//
//        // 新增单词
//        node.word = true;
//        node.value = value;
//        size++;
//        return value;

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> nextNode = node.getChildren().get(c);

            // 如果没有这个字符节点，则新建它并建立好关系
            if (nextNode == null) {
                nextNode = new Node<>(c, node);
                node.getChildren().put(c, nextNode);
            }

            node = nextNode;
        }

        // 之前存在这个单词
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 新增单词
        node.word = true;
        node.value = value;
        size++;
        return null;

    }

    public V get(String key) {
        Node<V> node = node(key);
        // 不是单词节点value为null
        return node != null ? node.value : null;
    }

    public V remove(String key){
        Node<V> node = node(key);
        // 没有这个单词
        if (node == null || !node.word) return null;

        V oldValue = node.value;

        // 单词节点有其他子节点
        if (!node.getChildren().isEmpty()) {
            node.word = false;
            node.value = null;
            size--;
            return oldValue;
        }


        // 单词节点没有其他子节点
        Node<V> parent;
        while ((parent = node.parent) != null) {
            // 从父节点中删除
            parent.getChildren().remove(node.character);

            // 父节点是单词节点，或者父节点有其他子节点，代表父节点是其他单词的前缀，不用继续向上删除，结束
            if (parent.word || !parent.getChildren().isEmpty()) break;

            node = parent;
        }

        size--;
        return oldValue;
    }

    // 是否存在指定前缀
    public boolean startWith(String prefix) {
        Node<V> node = node(prefix);
        return node != null;
    }

    // 找到一个key的最后一个节点（结尾节点）
    private Node<V> node(String key){
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();

//        // 从根节点依次往下查找   （每一层一个字符）
//        for (int i = 0; i < len; i++) {
//            // 如果还有下一个字符需要查找，但是当前节点已经是空，或者子节点是空，就没找到，返回null
//            if (node == null || node.children == null || node.children.isEmpty()) {
//                return null;
//            }
//            char c = key.charAt(i);
//            node = node.children.get(c);
//        }
//
//        return node;

        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if (node == null) return null;
        }

        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private static class Node<V> {
        Character character;
        V value;

        // 是否为单词的结尾（是否是一个完整的单词，单词节点）
        boolean word;

        // 父节点
        Node<V> parent;
        // 子节点们
        Map<Character, Node<V>> children;

        public Node(Character character, Node<V> parent) {
            this.character = character;
            this.parent = parent;
        }

        Map<Character, Node<V>> getChildren() {
            return children == null ? children = new HashMap<>() : children;
        }
    }


}
