package datastructure.hash;

import java.util.Objects;

// 有记录添加顺序的HashMap
@SuppressWarnings("unchecked")
public class LinkedHashMap<K, V> extends HashMap<K, V> {

    // 头节点
    private LinkedNode<K, V> first;
    // 尾节点
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        // 清空头尾节点
        first = null;
        last = null;
    }

    @Override
    public boolean containsValue(V value) {
        LinkedNode<K,V> node = first;
        while (node != null){
            if (Objects.equals(node.value,value)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K,V> node = new LinkedNode(key, value, parent);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    @Override
    protected void afterRemove(Node<K, V> willRemoveNode, Node<K, V> realRemoveNode) {
        LinkedNode<K,V> willRemoveLinkedNode = (LinkedNode<K,V>)willRemoveNode;
        LinkedNode<K,V> realRemoveLinkedNode = (LinkedNode<K,V>)realRemoveNode;

        // 理应删除节点和实际删除节点不一样
        // 交换 理应删除节点 和 实际删除节点 在链表中的位置（防止添加顺序异常混乱）
        if (willRemoveLinkedNode != realRemoveLinkedNode){

            LinkedNode<K,V> tmp;

            // 交换prev
            tmp = willRemoveLinkedNode.prev;
            willRemoveLinkedNode.prev = realRemoveLinkedNode.prev;
            realRemoveLinkedNode.prev = tmp;
            if (willRemoveLinkedNode.prev == null){
                first = willRemoveLinkedNode;
            } else {
                willRemoveLinkedNode.prev.next = willRemoveLinkedNode;
            }

            if (realRemoveLinkedNode.prev == null){
                first = realRemoveLinkedNode;
            } else {
                realRemoveLinkedNode.prev.next = realRemoveLinkedNode;

            }

            // 交换next
            tmp = willRemoveLinkedNode.next;
            willRemoveLinkedNode.next = realRemoveLinkedNode.next;
            realRemoveLinkedNode.next = tmp;
            if (willRemoveLinkedNode.next == null){
                last = willRemoveLinkedNode;
            } else {
                willRemoveLinkedNode.next.prev = willRemoveLinkedNode;
            }
            if (realRemoveLinkedNode.next == null){
                last = realRemoveLinkedNode;
            } else {
                realRemoveLinkedNode.next.prev = realRemoveLinkedNode;

            }
        }


        // 删除链表节点
        LinkedNode<K,V> prev = realRemoveLinkedNode.prev;
        LinkedNode<K,V> next = realRemoveLinkedNode.next;

        if (prev == null){
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null){
            last = prev;
        } else {
            next.prev = prev;
        }
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        LinkedNode<K,V> node = first;
        while (node != null && !visitor.stop()){
            visitor.visit(node.key,node.value);
            node = node.next;
        }
    }

    private class LinkedNode<K, V> extends Node<K, V> {
        private LinkedNode<K, V> prev;
        private LinkedNode<K, V> next;
        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

}
