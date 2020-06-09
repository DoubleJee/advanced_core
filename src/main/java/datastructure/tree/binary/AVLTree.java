package datastructure.tree.binary;

import java.util.Comparator;

// AVL 平衡二叉树 继承于二叉搜索树
public class AVLTree<E extends Comparable> extends BinarySearchTree<E> {

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
        AVLNode<E> first = ((AVLNode<E>) node).tallerChild();
        AVLNode<E> second = first.tallerChild();

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

        AVLNode<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return (AVLNode<E>)left;
            if (rightHeight > leftHeight) return (AVLNode<E>)right;
            return isLeftChildOfParent() ? (AVLNode<E>)left : (AVLNode<E>)right;
        }

    }
}
