package datastructure.tree.binary;

import datastructure.linear.queue.Queue;
import datastructure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

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

    }

    public boolean contains(E element) {
        return false;
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

    // 当前节点的高度等于其子节点的高度 + 1
    private int recursionHeight(Node<E> node){
        if (node == null) return 0;
        // 递归找到最高子节点的高度 然后 + 1
        return 1 + Math.max(recursionHeight(node.left),recursionHeight(node.right));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return toString(root,sb,"");
    }

    // 前序遍历输出
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
    }
}
