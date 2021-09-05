package com.migrantworker;

/**
 * 一个链表，翻转从m到N之间的子链表
 */
public class ReverseSublist {

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        ListNode notReversedHead = null;
        ListNode notReversedTail = null;
        ListNode reversedTail = null;
        int i = 1;
        while (head != null) {
            ListNode node = head;
            head = head.next;
            if (i < m) {
                //放到正序节点
                if (notReversedHead == null) {
                    notReversedHead = node;
                    notReversedTail = node;
                } else {
                    notReversedTail = node;
                }
            } else {
                //放到反序节点
                if (reversedTail == null) {
                    reversedTail = node;
                    notReversedTail.next = null;
                }
                node.next = notReversedTail.next;
                notReversedTail.next = node;
                if (i == n) {
                    reversedTail.next = head;
                    break;
                }
            }
            i++;
        }

        return notReversedHead;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(7, null);
        ListNode node2 = new ListNode(6, node1);
        ListNode node3 = new ListNode(5, node2);
        ListNode node4 = new ListNode(4, node3);
        ListNode node5 = new ListNode(3, node4);
        ListNode node6 = new ListNode(2, node5);
        ListNode node7 = new ListNode(1, node6);

        ListNode retNode = reverseBetween(node7, 2, 4);
        while (retNode != null) {
            System.out.println(retNode.val);
            retNode = retNode.next;
        }
    }
}
