package datastructure.tree.binary;

import java.util.Comparator;

// 红黑树 基础于二叉搜索树
public class RBTree<E extends Comparable> extends BinarySearchTree<E>{

    private static final boolean RED = false;

    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator comparator) {
        super(comparator);
    }


    // 染色
    private Node<E> color(Node<E> node,boolean color){
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    // 染红色
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    // 染黑色
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    // 节点颜色
    private boolean colorOf(Node<E> node){
        // 如果是null的话 代表空节点
        if (node == null) return BLACK;
        return ((RBNode<E>)node).color;
    }

    // 是否红色节点
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    // 是否黑色节点
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }


    class RBNode<E> extends Node<E>{
        private boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
