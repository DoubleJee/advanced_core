package datastructure.tree.binary;

import java.util.Comparator;

// 平衡二叉搜索树 基础于二叉搜索树
public abstract class BalancedBinarySearchTree<E extends Comparable> extends BinarySearchTree<E>{

    public BalancedBinarySearchTree() {
        this(null);
    }

    public BalancedBinarySearchTree(Comparator comparator) {
        super(comparator);
    }


    // 平衡二叉搜索树 公有行为抽离出来，左旋转，右旋转，统一旋转


    /**
     * 下列公式说明：
     *
     * r = 旋转节点，rl = 旋转节点的左子树，rr = 旋转节点的右子树，
     * rrs = 旋转节点的替代子树（是rl，rr的其中一个），res = 旋转过程的交换子树（是rrs左子树、右子树的其中一个）
     *
     */

    // 左旋 r.right = rr.left; rr.left = r
    protected void rotateLeft(Node<E> node){
        // 旋转节点的替代子树
        Node<E> rrsNode = node.right;
        // 旋转过程的交换子树
        Node<E> resNode = rrsNode.left;
        // r.right = rr.left
        node.right = resNode;
        // rr.left = r
        rrsNode.left = node;
        // 交换之后的操作（维护变化节点的parent和AVLTree高度、RBTree染色之类的）
        afterRotate(node,rrsNode,resNode);
    }

    // 右旋 r.left = rl.right; rl.right = r
    protected void rotateRight(Node<E> node){
        // 旋转节点的替代子树
        Node<E> rrsNode = node.left;
        // 旋转过程的交换子树
        Node<E> resNode = rrsNode.right;
        // r.left = rl.right
        node.left = resNode;
        // rl.right = r
        rrsNode.right = node;
        // 交换之后的操作（维护变化节点的parent和AVLTree高度、RBTree染色之类的）
        afterRotate(node,rrsNode,resNode);

        // 下面是方便理解的逻辑
//        Node<E> left = node.left;
//        // r.left = rl.right
//        node.left = left.right;
//        // rl.right = r
//        left.right = node;
//
//        // 更新r的左子树的parent
//        if (node.left != null){
//            node.left.parent = node;
//        }
//        // 更新rl的父节点
//        left.parent = node.parent;
//        // 如果rl的父节点为空 则rl是树的根节点
//        if (left.parent == null){
//            root = left;
//        }else {
//            // 更新rl父节点的（左/右） 子树
//            if (node.isLeftChildOfParent()){
//                left.parent.left = left;
//            }else {
//                left.parent.right = left;
//            }
//        }
//
//        // 更新r的父节点
//        node.parent = left;
//
//        // 更新r和rl的高度
//        node.updateHeight();
//        left.updateHeight();
    }


    // 旋转完的后置操作，维护变化节点的parent指向，子类可以根据其特殊性新增行为，如更新高度，对应染色
    protected void afterRotate(Node<E> rNode,Node<E> rrsNode,Node<E> resNode){

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

//        // 更新r和rrs的高度
//        updateHeight(rNode);
//        updateHeight(rrsNode);
    }

    /**
     *  统一旋转（把平衡节点的群组关系抽象出来 a,b,c,d,e,f,g）
     *  (并且是从小到大，abc d efg ，分别是三个子树：abc子树，efg子树，bdf子树 )
     *
     *  把旋转涉及的节点抽象出来(a,b,c,d,e,f,g)(并且是从小到大，abc d efg ，分别是三个子树：abc子树，efg子树，bdf子树 )来实现统一旋转 适合实际发布代码
     *
     */
    protected void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g){

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

//        // 更新各个子树根高度
//        updateHeight(b);
//        updateHeight(f);
//        updateHeight(d);
    }



}
