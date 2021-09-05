package com.migrantworker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个线段数组，合并其中相交的线段。例如
 * [[1, 5], [2,8], [10, 15], [17, 20], [16, 30]]
 * 输出
 * [[1,8], [10, 15], [16, 30]]
 */
public class MergeSegments2 {

    static class Node {
        int x1;
        int x2;
        Node next;
        public Node() {

        }

        public Node(int[] segment) {
            this.x1 = segment[0];
            this.x2 = segment[1];
        }

    }

    /**
     * 比较两个节点！
     * @param node1
     * @param node2
     * @return -1： node1整体在node2左边, 1, node2整体在node2右边， 0，有重叠
     */
    private static int compare(Node node1, Node node2) {
        if (node1.x2 < node2.x1)
            return -1;
        if (node1.x1 > node2.x2)
            return 1;
        return 0;
    }

    /**
     * 合并2个线段节点
     * @param node1
     * @param node2
     * @return
     */
    private static Node union(Node node1, Node node2) {
        Node node = new Node();
        node.x1 = Math.min(node1.x1, node2.x1);
        node.x2 = Math.max(node1.x2, node2.x2);
        return node;
    }

    /**
     * 对所有重叠的线段进行合并，假设每个线段数据均为合法。
     * 算法：
     * 首先思考2个线段之间的关系有几种可能性？3种
     * 1. 线段a在线段b的左边，无相交
     * 2. 线段a和线段b相交
     * 3. 线段a在线段的右边，无相交
     * 维护一个有序单向链表。每个节点存储一个线段，这些节点里的线段互不相交。
     * 从原始数据取出线段，不断往这个链表里添加或合并，同时保持节点互不相交。最终就可以得到结果。
     *
     * 题外拓展：本质是个排序问题，初步想到二叉树来降低时间复杂度，还没想透彻。先交作业了。
     *
     * @param segments
     * @return
     */
    public static int[][] merge2(int[][] segments) {
        if (segments == null)
            return null;

        Node firstNode = new Node();
        int size = 0;
        for (int i = 0; i < segments.length; i++) {
            if (i == 0) {
                firstNode.next = new Node(segments[i]);
                size = 1;
            } else {
                Node node = new Node(segments[i]);
                Node previousNode = firstNode;
                //在链表中找到合适的地方插入
                while (previousNode.next != null) {
                    int result = compare(node, previousNode.next);
                    if (result < 0) {
                        //新加入的节点在当前的节点左侧，则插入链表
                        Node temp = previousNode.next;
                        previousNode.next = node;
                        node.next = temp;
                        size++;
                        //右边的所有节点不可能跟这个新节点重叠，直接退出
                        break;
                    } else if (result == 0) {
                        //新加入的节点跟当前节点有重叠，将当前节点合并到新节点中
                        //继续循环跟下个节点比较
                        node = union(node, previousNode.next);
                        previousNode.next = previousNode.next.next;
                        size--;
                    } else {
                        //新加入的节点整体在当前节点右侧，继续比对下个节点
                        previousNode = previousNode.next;
                    }
                }
                //此时新节点在所有节点的右侧，直接加到队尾
                if (previousNode.next == null) {
                    previousNode.next = node;
                    size++;
                }
            }
        }

        //输出为2维数组
        int[][] ret = new int[size][];
        Node currentNode = firstNode.next;
        int i = 0;
        while (currentNode != null) {
            ret[i] = new int[]{currentNode.x1, currentNode.x2};
            currentNode = currentNode.next;
            i++;
        }

        return ret;
    }

    /**
     * 暴力算法
     * @param segments
     * @return
     */
    public static int[][] merge1(int[][] segments) {
        if (segments == null || segments.length == 0)
            return null;
        List<int[]> retList = doMerge(Arrays.asList(segments));
        int[][] ret = new int[retList.size()][];
        return retList.toArray(ret);
    }

    private static List<int[]> doMerge(List<int[]> segments) {
        if (segments.size() == 1)
            return segments;
        List<int[]> ret = new ArrayList<>();
        int[] firstSegment = segments.get(0);
        ret.add(new int[]{firstSegment[0], firstSegment[1]});
        for (int i = 1; i < segments.size(); i++) {
            int[] segment = segments.get(i);
            boolean merged = false;
            for (int j = 0; j < ret.size(); j++) {
                int[] retSegment = ret.get(j);
                if ((segment[0] >= retSegment[0] && segment[0] <= retSegment[1] || (segment[1] >= retSegment[0] && segment[1] <= retSegment[1]))) {
                    //如果相交,则合并
                    retSegment[0] = Math.min(segment[0], retSegment[0]);
                    retSegment[1] = Math.max(segment[1], retSegment[1]);
                    merged = true;
                    break;
                }
            }

            //不相交，加入返回列表
            if (!merged)
                ret.add(new int[]{segment[0], segment[1]});
        }

        if (ret.size() == segments.size())
            //一趟下来没有任何合并发生，就返回
            return ret;
        else
            //有合并发生，递归再做一遍
            return doMerge(ret);
    }


    public static void main(String[] args) {
        System.out.println("直观暴力算法");
        System.out.println(Arrays.deepToString(merge1(new int[][]{{1,3},{2,6},{15,18},{8,10},{10,11},{7,8},{4,5}})));
        System.out.println(Arrays.deepToString(merge1(new int[][]{{1,3}})));
        System.out.println(Arrays.deepToString(merge1(new int[][]{{1,1000},{10,100},{9,10},{79,90},{999,1000}})));
        System.out.println(Arrays.deepToString(merge1(new int[][]{{1,2},{3,4},{5,6},{7,8},{9,10}})));
        System.out.println(Arrays.deepToString(merge1(new int[][]{{9,10},{7,8},{5,6}})));
        System.out.println(Arrays.deepToString(merge1(new int[][]{{9, 10},{7, 13},{1,8}})));
        System.out.println("更优的有序列表插入算法");
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1,3},{2,6},{15,18},{8,10},{10,11},{7,8},{4,5}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1,3}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1,1000},{10,100},{9,10},{79,90},{999,1000}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1,2},{3,4},{5,6},{7,8},{9,10}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{9,10},{7,8},{5,6}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{9,10},{7, 13},{1,8}})));
    }
}
