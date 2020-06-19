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


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        RBNode<E> rbNode = new RBNode<>(element, parent);
        if (root == null){
            black(rbNode);
        }
        return rbNode;
    }

//    @Override
//    protected void afterAdd(Node<E> node) {
//        if (isBlack(node.parent)){
//            return;
//        }
//
//        if (isRed(node.parent.sibling())){
//            loopRepair(node);
//        }else {
//            rotateRepair(node);
//        }
//    }
//
//
//    private void loopRepair(Node<E> node){
//        while (isRed(node) && isRed(node.parent.sibling())){
//            black(node.parent);
//            if (root != node.parent.parent){
//                red(node.parent.parent);
//            }
//            black(node.parent.sibling());
//            if (node.parent != null){
//                node = node.parent.parent;
//            }
//        }
//    }
//
//    private void rotateRepair(Node<E> node){
//        if (node.isLeftChildOfParent()){
//            // LL
//            if (node.parent.isLeftChildOfParent()){
//                rotate(node.parent.parent,node.left,node,node.right,node.parent,node.parent.right,node.parent.parent,node.parent.parent.right);
//            } else {
//                // RL
//                rotate(node.parent.parent,node.parent.parent.left,node.parent.parent,node.left,node,node.right,node.parent,node.parent.right);
//            }
//        }else if (node.isRightChildOfParent()){
//            // RR
//            if (node.parent.isRightChildOfParent()) {
//                rotate(node.parent.parent,node.parent.parent.left,node.parent.parent,node.parent.left,node.parent,node.left,node,node.right);
//            }else {
//                // LR
//                rotate(node.parent.parent,node.parent.left,node.parent,node.left,node,node.right,node.parent.parent,node.parent.parent.right);
//            }
//        }
//    }

    @Override
    protected void afterAdd(Node<E> node) {
        // 如果添加节点的父节点是BLACK，则不处理
        if (isBlack(node.parent)){
            return;
        }

        // 父节点为RED，需要修复
        while (isRed(node) && isRed(node.parent)){
            // 修复
            repair(node);
            if (node.parent != null){
                node = node.parent.parent;
            }
        }

    }


    // 修复
    private void repair(Node<E> node){
        // 如果uncle节点是红色，递归染色
        if (isRed(node.parent.sibling())){

            black(node.parent);
            if (root != node.parent.parent){
                red(node.parent.parent);
            }
            black(node.parent.sibling());

        }else {
            // 如果uncle节点是黑色，根据情况对应旋转一次即可
            if (node.isLeftChildOfParent()){
                // LL
                if (node.parent.isLeftChildOfParent()){
                    rotate(node.parent.parent,node.left,node,node.right,node.parent,node.parent.right,node.parent.parent,node.parent.parent.right);
                } else {
                    // RL
                    rotate(node.parent.parent,node.parent.parent.left,node.parent.parent,node.left,node,node.right,node.parent,node.parent.right);
                }
            }else if (node.isRightChildOfParent()){
                // RR
                if (node.parent.isRightChildOfParent()) {
                    rotate(node.parent.parent,node.parent.parent.left,node.parent.parent,node.parent.left,node.parent,node.left,node,node.right);
                }else {
                    // LR
                    rotate(node.parent.parent,node.parent.left,node.parent,node.left,node,node.right,node.parent.parent,node.parent.parent.right);
                }
            }
        }
    }

    private void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {

        // bdf子树
        d.left = b;
        d.right = f;
        d.parent = r.parent;
        if (r.isLeftChildOfParent()) {
            r.parent.left = d;
        } else if (r.isRightChildOfParent()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        b.parent = d;
        f.parent = d;

        // abc子树
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;

        // efg子树
        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;

        //染色
        black(d);

        black(a);
        red(b);
        black(c);

        black(e);
        red(f);
        black(g);
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
