package datastructure.tree.binary;

import java.util.Comparator;

// 红黑树 基础于二叉搜索树
public class RBTree<E extends Comparable> extends BalancedBinarySearchTree<E>{

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

    @Override
    protected void afterAdd(Node<E> node) {

        Node<E> parent = node.parent;

        // 添加的是根节点 或者 上溢到根节点
        if (parent == null){
            black(node);
            return;
        }

        // 如果添加节点的父节点是BLACK，则不处理
        if (isBlack(parent)) return;

        // 父节点为RED，需要维持红黑树的性质
        Node<E> grand = parent.parent;
        Node<E> uncle = parent.sibling();

        // uncle节点为红色 操作公式：将parent、uncle染成BLACK，grand向上合并当成新添加的元素递归修复
        if (isRed(uncle)){

            black(parent);
            red(grand);
            black(uncle);

            // 递归去修复
            afterAdd(grand);
            return;
        }


        // uncle节点为黑色 根据情况对应旋转一次即可
        if (node.isLeftChildOfParent()){
            // LL
            if (parent.isLeftChildOfParent()){
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);

//                // parent染成BLACK，grand染成RED
//                black(parent);
//                red(grand);
//                rotateRight(grand);
            } else {
                // RL
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);

//                // f染成BLACK，grand染成RED
//                black(node);
//                red(grand);
//                rotateRight(parent);
//                rotateLeft(grand);
            }
        }else if (node.isRightChildOfParent()){
            // RR
            if (parent.isRightChildOfParent()) {
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);

//                // parent染成BLACK，grand染成RED
//                black(parent);
//                red(grand);
//                rotateLeft(grand);
            }else {
                // LR
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);

//                // f染成BLACK，grand染成RED
//                black(node);
//                red(grand);
//                rotateLeft(parent);
//                rotateRight(grand);
            }
        }


    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
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

        @Override
        public String toString() {
            return (color == RED ? "R" : "B") + "_" + super.toString();
        }
    }
}
