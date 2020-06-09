package datastructure.tree;


import datastructure.tree.printer.BinaryTreeInfo;

// 二叉树接口
public interface BinaryTree<E extends Comparable> extends BinaryTreeInfo {

    /**
     * 大小
     */
    int size();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 清理
     */
    void clear();

    /**
     * 添加元素
     */
    void add(E element);

    /**
     * 移除元素
     */
    void remove(E element);


    /**
     * 包含元素
     */
    boolean contains(E element);

    /**
     * 是否完全二叉树
     */
    boolean isComplete();

    /**
     * 高度
     */
    int height();

    /**
     * 前序遍历
     */
    void preorder(Visitor<E> visitor);

    /**
     * 中序遍历
     */
    void inorder(Visitor<E> visitor);

    /**
     * 后序遍历
     */
    void postorder(Visitor<E> visitor);

    /**
     * 层序遍历
     */
    void levelOrder(Visitor<E> visitor);

    interface Visitor<T> {
        boolean stop();

        void visit(T element);
    }

    class Node<E> {
        public E element;
        // 左节点
        public Node<E> left;
        // 右节点
        public Node<E> right;
        // 父节点
        public Node<E> parent;

        public Node(E element,Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        // 是否叶子节点
        public boolean isLeaf(){
            return left == null && right == null;
        }

        // 是否同时具有两个子树（子节点）
        public boolean hasTwoChild(){
            return left != null && right != null;
        }

        // 是否是父节点的左子节点
        public boolean isLeftChildOfParent(){
            return parent != null && parent.left == this;
        }

        // 是否是父节点的右子节点
        public boolean isRightChildOfParent(){
            return parent != null && parent.right == this;
        }
    }
}
