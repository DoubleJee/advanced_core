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


    // 中序遍历 从上往下、从左往右依次访问每一个节点
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

    public int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
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
    }
}
