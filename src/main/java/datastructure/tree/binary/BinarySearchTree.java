package datastructure.tree.binary;

import datastructure.tree.AbstractBinaryTree;
import java.util.Comparator;

// 二叉搜索树
public class BinarySearchTree<E extends Comparable> extends AbstractBinaryTree<E> {
    // E extends Comparable是自定义的，一般可以不需要指定泛型E必须继承Comparable

    // 比较器
    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        // 添加的第一个节点
        if (root == null) {
            root = createNode(element,null);
            size++;
            afterAdd(root);
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

        Node<E> newNode = createNode(element,parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        afterAdd(newNode);
    }

    @Override
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

        afterRemove(node);
    }

    @Override
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

    public Node<E> getRoot(){
        return root;
    }

    // 模板方法 增加之后附加的动作
    protected void afterAdd(Node<E> node){

    }
    // 模板方法 删除之后附加的动作
    protected void afterRemove(Node<E> node){

    }
    // 工厂方法 创建节点
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element, parent);
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
}
