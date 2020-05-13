package datastructure.list.linked;

import datastructure.list.List;

public class LinkedListTest {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(2, 3);
        System.out.println(linkedList);
        System.out.println(linkedList.get(4));
        linkedList.set(4, 8);
        linkedList.remove(0);
        System.out.println(linkedList);
        linkedList.remove(linkedList.size() - 1);
        System.out.println(linkedList);
        linkedList.add(linkedList.size(), 10);
        System.out.println(linkedList);
        linkedList.add(0, 100);
        System.out.println(linkedList);
        System.gc();

    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "," + (next == null ? "null" : next.toString());
        }

        public static void main(String[] args) {
            ListNode listNode1 = new ListNode(1);
            ListNode listNode2 = new ListNode(2);
            listNode1.next = listNode2;
            ListNode listNode3 = new ListNode(3);
            listNode2.next = listNode3;
            ListNode listNode4 = new ListNode(4);
            listNode3.next = listNode4;
            ListNode listNode5 = new ListNode(5);
            listNode4.next = listNode5;
            System.out.println(listNode1);

            deleteNode(listNode3);
            System.out.println(listNode1);

//            ListNode listNode = reversalNode(listNode1);
//            System.out.println(listNode);
            listNode5.next = listNode1;
            boolean ring = isRing(listNode1);
            System.out.println(ring);

        }
    }

    //删除当前节点。单向链表
    public static void deleteNode(ListNode listNode) {
        listNode.val = listNode.next.val;
        listNode.next = listNode.next.next;
    }

    //反转链表
    public static ListNode reversalNode(ListNode head) {
        if (head == null || head.next == null) return head;

        //当前节点的上一个节点
        ListNode prev = null;
        while (head != null) {
            ListNode oldNext = head.next;
            //反转
            head.next = prev;
            //上一个节点等于当前节点
            prev = head;
            if (oldNext == null) {
                break;
            }
            //节点等于原本的下一个节点
            head = oldNext;
        }
        return head;
    }

    //判断链表是不是环形 快慢指针来比较
    public static boolean isRing(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != null){
            System.out.println(slow.val + "==" + fast.val);
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;

    }

}
