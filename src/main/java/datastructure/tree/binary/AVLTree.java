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
                rotate(ubNode,(AVLNode<E>) second.left,second,(AVLNode<E>) second.right,first,(AVLNode<E>) first.right,ubNode,(AVLNode<E>) ubNode.right);
            }else {
                // LR
                rotate(ubNode,(AVLNode<E>) first.left,first,(AVLNode<E>) second.left,second,(AVLNode<E>) second.right,ubNode,(AVLNode<E>) ubNode.right);
            }
        }else {
            // R
            // RL
            if (second.isLeftChildOfParent()){
                rotate(ubNode,(AVLNode<E>) ubNode.left,ubNode,(AVLNode<E>) second.left,second,(AVLNode<E>) second.right,first,(AVLNode<E>) first.right);
            }else {
                // RR
                rotate(ubNode,(AVLNode<E>) ubNode.left,ubNode,(AVLNode<E>) first.left,first,(AVLNode<E>) second.left,second,(AVLNode<E>) second.right);
            }
        }


    }


    /**
     * 下列公式说明：r = 旋转节点，rl = 旋转节点的左子树，rr = 旋转节点的右子树
     */

    // 左旋 r.right = rr.left; rr.left = r
    private void rotateLeft(AVLNode<E> node){
        // 旋转节点的替代子树
        AVLNode<E> rrsNode = (AVLNode<E>)node.right;
        // 旋转过程的交换子树
        AVLNode<E> resNode = (AVLNode<E>)rrsNode.left;
        // r.right = rr.left
        node.right = resNode;
        // rr.left = r
        rrsNode.left = node;
        // 交换之后的操作（维护变化节点的parent和高度）
        afterRotate(node,rrsNode,resNode);
    }

    // 右旋 r.left = rl.right; rl.right = r
    private void rotateRight(AVLNode<E> node){
        AVLNode<E> left = (AVLNode<E>)node.left;
        // r.left = rl.right
        node.left = left.right;
        // rl.right = r
        left.right = node;

        // 更新r的左子树的parent
        if (node.left != null){
            node.left.parent = node;
        }
        // 更新rl的父节点
        left.parent = node.parent;
        // 如果rl的父节点为空 则rl是树的根节点
        if (left.parent == null){
            root = left;
        }else {
            // 更新rl父节点的（左/右） 子树
            if (node.isLeftChildOfParent()){
                left.parent.left = left;
            }else {
                left.parent.right = left;
            }
        }

        // 更新r的父节点
        node.parent = left;

        // 更新r和rl的高度
        node.updateHeight();
        left.updateHeight();
    }

    /**
     *  下列公式说明：r = 旋转节点，rrs = 旋转节点的替代子树（是rl，rr的其中一个），res = 旋转过程的交换子树（是rrs左子树、右子树的其中一个）
     */

    private void afterRotate(AVLNode<E> rNode,AVLNode<E> rrsNode,AVLNode<E> resNode){

        // 更新rrs的父节点
        rrsNode.parent = rNode.parent;

        // 更新rrs父节点的（左/右）子树，如果rrs的父节点为空 则rrs是树的根节点
        if (rNode.isLeftChildOfParent()){
            rrsNode.parent.left = rrsNode;
        }else if (rNode.isRightChildOfParent()){
            rrsNode.parent.right = rrsNode;
        }else {
            root = rrsNode;
        }

        // 更新r的父节点
        rNode.parent = rrsNode;

        // 更新res的parent
        if (resNode != null){
            resNode.parent = rNode;
        }

        // 更新r和rrs的高度
        updateHeight(rNode);
        updateHeight(rrsNode);
    }

    /**
     *  统一旋转（把平衡节点的群组关系抽象出来 a,b,c,d,e,f,g）
     *  (并且是从小到大，abc d efg ，分别是三个子树：abc子树，efg子树，bdf子树 )
     */
    private void rotate(AVLNode<E> r,
                        AVLNode<E> a, AVLNode<E> b, AVLNode<E> c,
                        AVLNode<E> d,
                        AVLNode<E> e, AVLNode<E> f, AVLNode<E> g){

        // bdf子树
        d.left = b;
        d.right = f;
        d.parent = r.parent;
        if (r.isLeftChildOfParent()){
            r.parent.left = d;
        }else if (r.isRightChildOfParent()){
            r.parent.right = d;
        }else {
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
