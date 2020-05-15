package datastructure.linear.linked;

import datastructure.linear.AbstractList;
import datastructure.linear.List;

public class LinkedList<E> extends AbstractList<E> implements List<E> {

    // 头节点
    private Node<E> first;
    // 尾结点
    private Node<E> last;

    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    @Override
    public void add(int index, E element) {
        if (index == size){
            // 向尾部添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            // 第一次添加
            if (oldLast == null){
                first = last;
            }else {
                oldLast.next = last;
            }
        }else {
            // 正常情况
            Node<E> next = getNode(index);
            Node<E> perv = next.prev;
            Node<E> node = new Node<>(perv, element, next);
            next.prev = node;

            // index == 0
            if (perv == null){
                first = node;
            }else {
                perv.next = node;
            }
        }
        size++;
    }

    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * 复杂度：
     *  最好情况：O(1)
     *  最坏情况：O(n)
     *  平均情况：O(n)
     */
    @Override
    public E remove(int index) {
        E oldElement = null;
        Node<E> removeNode = getNode(index);
        oldElement = removeNode.element;

        Node<E> prev = removeNode.prev;
        Node<E> next = removeNode.next;

        // 删除头部
        if (prev == null){
            first = next;
        }else {
            prev.next = next;
        }

        // 删除尾部
        if (next == null){
            last = prev;
        }else {
            next.prev = prev;
        }

        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return EXCEPTION_NOT_FIND;
    }

    @Override
    public void clear() {
        size = 0;
        //首尾节点为空的话，链表的元素全部会被回收
        first = null;
        last = null;
    }

    private Node<E> getNode(int index) {
        checkIndex(index);
        if (index < size / 2){
            //正向寻找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            //逆向寻找
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
        // 效率提高一半

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size:").append(size).append(", [");
        Node<E> node = first;
//        while (node != null) {
//            stringBuilder.append(node.element);
//            node = node.next;
//        }
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(node);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null){
                sb.append(prev.element);
            }else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null){
                sb.append(next.element);
            }else {
                sb.append("null");
            }

            return sb.toString();
        }
    }
}
