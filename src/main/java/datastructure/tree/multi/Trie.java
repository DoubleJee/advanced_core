package datastructure.tree.multi;

import datastructure.hash.HashMap;
import datastructure.other.map.Map;

// Trie 字典树、多叉树、单词查找树
public class Trie<V> {

    private int size;

    private Node<V> root;

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear(){
        size = 0;
        root = null;
    }

    // 是否包含某个字符串
    public boolean contains(String key) {
        Node<V> node = node(key);
        // 有这个节点并且是一个完整单词
        return node != null && node.word;
    }

    public V add(String key, V value) {
        keyCheck(key);
        if (root == null) {
            // 初始化根节点
            root = new Node<>(null, null);
        }


        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;
            Node<V> findNode = emptyChildren ? null : node.children.get(c);

            // 如果没有这个字符节点，则新建它并建立好关系
            if (findNode == null) {
                findNode = new Node<>(c, node);
                // 放入字符节点之前，先初始化子节点容器（用时创建）
                if (emptyChildren) node.children = new HashMap<>();
                node.children.put(c, findNode);
            }

            // 继续往下
            node = findNode;
        }


//        Node<V> node = root;
//        int len = key.length();
//        for (int i = 0; i < len; i++) {
//            char c = key.charAt(i);
//            Node<V> findNode = node.getChildren().get(c);
//
//            // 如果没有这个字符节点，则新建它并建立好关系
//            if (findNode == null) {
//                findNode = new Node<>(c, node);
//                node.getChildren().put(c, findNode);
//            }
//
//            node = findNode;
//        }

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
        return node != null && node.word ? node.value : null;
    }

    public V remove(String key){
        Node<V> node = node(key);
        // 没有这个单词
        if (node == null || !node.word) return null;

        V oldValue = node.value;

        // 单词节点有其他子节点，直接将它变成字符节点
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            size--;
            return oldValue;
        }


        // 单词节点没有其他子节点，可以往上删除
        Node<V> parent;
        while ((parent = node.parent) != null) {
            // 从父节点中删除自己
            parent.children.remove(node.character);

            // 父节点是单词节点，或者父节点有其他子节点，代表父节点是其他单词的前缀，不能继续往上删除，结束
            if (parent.word || !parent.children.isEmpty()) break;

            // 继续往上
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

        // 从根节点依次每个字符往下查找
        for (int i = 0; i < len; i++) {
            // 查找字符，但是当前节点已经是空（上一次查找的结果没找到），或者子节点是空，代表没找到（没有必要往下找），返回null
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i);
            node = node.children.get(c);
        }


//        for (int i = 0; i < len; i++) {
//            char c = key.charAt(i);
//            node = node.getChildren().get(c);
//            if (node == null) return null;
//        }

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

//        Map<Character, Node<V>> getChildren() {
//            return children == null ? children = new HashMap<>() : children;
//        }
    }


}
