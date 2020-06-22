package datastructure.tree.binary;

import java.util.Comparator;

// AVL 平衡二叉树 继承于二叉搜索树
public class AVLTree<E extends Comparable> extends BalancedBinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // 平衡 更新高度
                updateHeight(node);
            }else {
                // 失衡 恢复平衡
                reBalance(node);
                // 恢复好了最低高度的失衡节点整棵树就平衡了
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // 平衡 更新高度
                updateHeight(node);
            }else {
                // 失衡 恢复平衡
                // 删除后的恢复平衡可能会让父节点..祖父节点失衡，产生连锁失衡效应，所以需要循环继续恢复平衡
                reBalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    // 节点是否平衡 计算公式：平衡因子只可能是1、0、-1也就是绝对值 <= 1
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    // 更新高度
    private void updateHeight(Node<E> node){
        ((AVLNode)node).updateHeight();
    }

    // 恢复平衡
    private void reBalance(Node<E> node){
        // 失衡节点
        AVLNode<E> ubNode = (AVLNode<E>) node;
        // 寻找失衡节点的最高子树 用来判断属于哪种旋转情况 LL RR LR RL
        AVLNode<E> first = ubNode.tallerChild();
        // 寻找失衡节点的最高子树的最高子树
        AVLNode<E> second = first.tallerChild();

        // L
        if (first.isLeftChildOfParent()){
            // LL
            if (second.isLeftChildOfParent()){
                rotateRight(ubNode);
            }else {
                // LR
                rotateLeft(first);
                rotateRight(ubNode);
            }
        }else {
            // R
            // RL
            if (second.isLeftChildOfParent()){
                rotateRight(first);
                rotateLeft(ubNode);
            }else {
                // RR
                rotateLeft(ubNode);
            }
        }


    }


    // 恢复平衡 统一选择操作 把旋转涉及的节点抽象出来(a,b,c,d,e,f,g)(并且是从小到大，abc d efg ，分别是三个子树：abc子树，efg子树，bdf子树 )来实现统一旋转 适合实际发布代码
    private void reBalance2(Node<E> node){
        // 失衡节点
        AVLNode<E> ubNode = (AVLNode<E>) node;
        // 寻找失衡节点的最高子树 用来判断属于哪种旋转情况 LL RR LR RL
        AVLNode<E> first = ubNode.tallerChild();
        // 寻找失衡节点的最高子树的最高子树
        AVLNode<E> second = first.tallerChild();

        // L
        if (first.isLeftChildOfParent()){
            // LL
            if (second.isLeftChildOfParent()){
                rotate(ubNode, second.left,second, second.right,first, first.right,ubNode, ubNode.right);
            }else {
                // LR
                rotate(ubNode, first.left,first, second.left,second, second.right,ubNode, ubNode.right);
            }
        }else {
            // R
            // RL
            if (second.isLeftChildOfParent()){
                rotate(ubNode, ubNode.left,ubNode, second.left,second, second.right,first, first.right);
            }else {
                // RR
                rotate(ubNode, ubNode.left,ubNode, first.left,first, second.left,second, second.right);
            }
        }


    }


    @Override
    protected void afterRotate(Node<E> rNode, Node<E> rrsNode, Node<E> resNode) {
        // 修正完变化节点的parent指向关系
        super.afterRotate(rNode, rrsNode, resNode);
        // 更新r和rrs的高度
        updateHeight(rNode);
        updateHeight(rrsNode);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        // 更新各个子树根高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    class AVLNode<E> extends Node<E>{
        // 高度
        int height = 1;

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        // 平衡因子
        int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        void updateHeight(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        // 高度最高的子树
        AVLNode<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return (AVLNode<E>)left;
            if (rightHeight > leftHeight) return (AVLNode<E>)right;
            return isLeftChildOfParent() ? (AVLNode<E>)left : (AVLNode<E>)right;
        }

    }
}
