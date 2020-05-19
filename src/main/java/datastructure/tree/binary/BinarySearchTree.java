package datastructure.tree.binary;

import datastructure.linear.queue.Queue;
import datastructure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

// 二叉搜索树
public class BinarySearchTree<E extends Comparable> implements BinaryTreeInfo {

    private int size;

    // 根节点
    private Node<E> root;

    // 比较器
    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        // 添加的第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 添加的不是第一个节点
        Node<E> parent = null;
        Node<E> node = root;
        int cmp = 0;

        while (node != null) {

            parent = node;

            // 比较
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 相等
                node.element = element;
                return;
            }
        }

        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node){
        if (node == null) return;
        // 长度--
        size --;

        // 度为2的节点
        if (node.hasTwoChild()){
            // 找到前驱节点
            Node<E> predecessor = predecessor(node);
            // 用前驱节点的值覆盖，度为2节点的值
            node.element = predecessor.element;
            // 删除前驱节点
            node = predecessor;
        }

        // 删除node节点

        // 替代节点 是删除节点的子节点
        Node<E> alternateNode = node.left != null ? node.left : node.right;

        // 度为1的节点  将子节点（替代节点）的parent指向删除节点的父节点，将父节点的左或右子树指向（根据原本的指向关系来决定）删除节点的子节点（替代节点）
        if (alternateNode != null) {
            // 替代节点的父节点 指向 删除节点的父节点
            alternateNode.parent = node.parent;

            // 度为1 并且是根节点
            if (node.parent == null) {
                // 根节点直接指向删除节点的下级
                root = alternateNode;
            } else if (node.parent.left == node) {
                node.parent.left = alternateNode;
            } else {
                node.parent.right = alternateNode;
            }
        }else if (node.parent == null){
            // 叶子节点，并且是根节点，直接设置根节点为NULL
            root = null;
        }else {
            // 叶子节点，并且不是根节点，将父节点指向删除节点的关系置为null
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

        }
    }


    public boolean contains(E element) {
        return node(element) != null;
    }

    public Node<E> node(E element){
        Node<E> node = root;
        while (node != null){
            int com = compare(element,node.element);
            if (com == 0) {
                return node;
            }else if (com > 0) {
                node = node.right;
            }else{
                node = node.left;
            }
        }
        return null;
    }

    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    // 前序遍历 根节点 -> 前序遍历左子树 -> 前序遍历右子树
    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right,visitor);
    }

    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root,visitor);
    }

    // 中序遍历 中序遍历左子树 -> 根节点 -> 中序遍历右子树
    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        inorderTraversal(node.left,visitor);
        if (visitor.stop()) return;
        visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }

    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root,visitor);
    }

    // 后序遍历 后序遍历左子树 -> 后序遍历右子树 -> 根节点
    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop()) return;
        postorderTraversal(node.left,visitor);
        postorderTraversal(node.right,visitor);
        if (visitor.stop()) return;
        visitor.visit(node.element);
    }


    // 层序遍历 从上往下、从左往右依次访问每一个节点
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        levelOrderTraversal(root, visitor);
    }

    public void levelOrderTraversal(Node<E> node, Visitor<E> visitor) {
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

    // 比较
    public int compare(E e1, E e2) {
        // 比较器
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 元素本身可比较
        return e1.compareTo(e2);
    }

    public void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
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
        return ((Node<E>) node).element;
    }


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

    public Node<E> getRoot(){
        return root;
    }


    // 是否是完全二叉树
    public boolean isComplete(){
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

    // 迭代计算树高度
    public int height(){
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return toString(root,sb,"");
    }

    // 使用前序遍历打印 二叉搜索树
    private String toString(Node<E> node,StringBuilder sb,String prefix){
        if (node == null) return sb.toString();
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left,sb,prefix + "L-");
        toString(node.right,sb,prefix + "R-");
        return sb.toString();
    }

    public interface Visitor<E> {
        boolean stop();

        void visit(E element);
    }

    public static class Node<E> {
        E element;
        // 左节点
        Node<E> left;
        // 右节点
        Node<E> right;
        // 父节点
        Node<E> parent;

        public Node(E element, Node<E> parent) {
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
    }
}
