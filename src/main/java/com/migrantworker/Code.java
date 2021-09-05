package com.migrantworker;

import java.util.concurrent.locks.ReentrantLock;

public class Code {
    public static class Node {
        int value;
        Node prev;
        Node next;
    }

    public static class BiList {
        Node head;

        public BiList() {

        }

        public void add(Node node) {
            if (node == null)
                return;
            if (head == null) {
                head = node;
                return;
            }
            Node curNode = head;
            while(curNode != null) {
                if (node.value >= curNode.value && (node.value < curNode.next.value ||  curNode.next == null)) {
                    Node nextNode = curNode.next;
                    curNode.next = node;
                    node.next = nextNode;
                    node.prev = curNode;
                    if (nextNode != null) {
                        nextNode.prev = node;
                    }

                    break;
                }

                curNode = curNode.next;
            }
        }

        public void remove(Node node) {
            if (node == null)
                return;
            if (head == null)
                return;

            Node curNode = head;
            while(curNode != null) {
                if (node.value == curNode.value) {
                    Node nextNode = curNode.next;
                    Node preNode = curNode.prev;
                    if (preNode == null) {
                        head = nextNode;
                        break;
                    }
                    if (nextNode == null) {
                        preNode.next = null;
                        curNode.prev = null;
                    }

                }
                curNode = curNode.next;
            }
        }
    }

    public static void main(String[] args) {
     //shuan
    }
}
