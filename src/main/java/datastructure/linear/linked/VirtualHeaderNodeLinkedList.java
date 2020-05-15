package datastructure.linear.linked;

import datastructure.linear.AbstractList;
import datastructure.linear.List;

public class VirtualHeaderNodeLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node<E> first;

    public VirtualHeaderNodeLinkedList(){
        first = new Node<>(null,null);
    }

    @Override
    public void add(int index, E element) {
        Node<E> prev = index == 0 ? first : getNode(index - 1);
        prev.next = new Node<>(element, prev.next);
        size++;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        E oldElement = null;
        Node<E> prev = index == 0 ? first : getNode(index - 1);
        Node<E> indexNode = prev.next;
        prev.next = indexNode.next;
        oldElement = indexNode.element;
        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first.next;
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
        //首节点为空的话，链表的元素全部会被回收
        first.next = null;
    }

    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size:").append(size).append(", [");
        Node<E> node = first.next;
//        while (node != null) {
//            stringBuilder.append(node.element);
//            node = node.next;
//        }
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(node.element);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
