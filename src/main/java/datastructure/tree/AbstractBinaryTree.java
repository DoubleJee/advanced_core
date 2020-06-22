package datastructure.tree;

import datastructure.linear.queue.Queue;

// 抽象二叉树，描述公共逻辑
public abstract class AbstractBinaryTree<E extends Comparable> implements BinaryTree<E>{

    protected int size;

    // 根节点
    protected Node<E> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return node(element) != null;
    }

    @Override
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new Queue<>();
        Node<E> node = root;

        // 是否需要往后都是叶子节点
        boolean leaf = false;
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            node = queue.deQueue();
            // 需要往后都是叶子节点
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null){
                queue.enQueue(node.left);
            }else if (node.right != null){
                // 出现了右对齐的子节点，子节点需要靠左对齐， node.left == null && node.right != null
                return false;
            }

            if (node.right != null){
                queue.enQueue(node.right);
            }else{
                // 出现度为1的节点 或者 叶子节点，往后的都需要是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    @Override
    public int height() {
        if (root == null) return 0;
        // 树高度
        int height = 0;
        // 每一层的节点数
        int levelNodeSize = 1;
        Node<E> node = root;
        Queue<Node<E>> queue = new Queue<>();
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            node = queue.deQueue();
            // 每次取出当前层的节点 就--
            levelNodeSize --;
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
            // 如果当前层节点为0 说明要进入下一层了
            if (levelNodeSize == 0){
                // 下一层的节点数 是上一层执行完，放入的所有节点数，队列的长度
                levelNodeSize = queue.size();
                // 高度++
                height++;
            }
        }
        return height;
    }

    // 递归计算树高度
    public int recursionHeight(){
        return recursionHeight(root);
    }
    private int recursionHeight(Node<E> node){
        // 当前节点的高度等于其子节点的高度 + 1
        if (node == null) return 0;
        // 递归找到最高子节点的高度 然后 + 1
        return 1 + Math.max(recursionHeight(node.left),recursionHeight(node.right));
    }


    @Override
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    @Override
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root,visitor);
    }

    @Override
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root,visitor);
    }

    @Override
    public void levelOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        levelOrder(root, visitor);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    // 获取节点
    protected abstract Node<E> node(E element);

    // 寻找前驱节点
    public Node<E> predecessor(Node<E> node){
        if (node == null) return null;

        // 前驱
        Node<E> pre = node.left;

        // 前驱节点在左子树当中 （left.right.right.right......)
        if (pre != null){
            while (pre.right != null){
                pre = pre.right;
            }
            return pre;
        }

        // 前驱节点在其父、祖父中 (parent.parent.parent....... && parent.right = node)
        while (node.parent != null && node.parent.right != node){
            node = node.parent;
        }

        return node.parent;
    }

    // 寻找后驱节点
    public Node<E> successor(Node<E> node){

        if (node == null) return null;

        // 后驱
        Node<E> post = node.right;

        // 后驱节点在右子树当中 （right.left.left.left......)
        if (post != null){
            while (post.left != null){
                post = post.left;
            }
            return post;
        }


        // 后驱节点在其父、祖父中 (parent.parent.parent....... && parent.left = node)
        while (node.parent != null && node.parent.left != node){
            node = node.parent;
        }

        return node.parent;

    }

    // 前序遍历 根节点 -> 前序遍历左子树 -> 前序遍历右子树
    protected void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right,visitor);
    }

    // 中序遍历 中序遍历左子树 -> 根节点 -> 中序遍历右子树
    protected void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        inorder(node.left,visitor);
        if (visitor.stop()) return;
        visitor.visit(node.element);
        inorder(node.right,visitor);
    }

    // 后序遍历 后序遍历左子树 -> 后序遍历右子树 -> 根节点
    protected void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        postorder(node.left,visitor);
        postorder(node.right,visitor);
        if (visitor.stop()) return;
        visitor.visit(node.element);
    }

    // 层序遍历 从上往下、从左往右依次访问每一个节点
    protected void levelOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        Queue<Node<E>> queue = new Queue<>();
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            node = queue.deQueue();
            if (visitor.stop()) return;
            visitor.visit(node.element);
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
        }
    }
}
