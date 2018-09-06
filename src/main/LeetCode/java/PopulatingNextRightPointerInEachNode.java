package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 *
 * 思路简述：类似按层输出的题目 BSF 宽度优先遍历 可用队列完成(两个队列结构，将每层分开)
 *          也可以只用一个队列，额外的两个length变量用于保存当前输出层剩余节点数(lenA)和
 *          下一层添加节点数(LenB)，LenA归零时交换两变量，进入下一层
 *
 */

import LeetCode.java.DataStruct.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointerInEachNode {
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
        Queue<TreeLinkNode> queueA = new LinkedList<>();
        Queue<TreeLinkNode> queueB = new LinkedList<>();
        queueA.offer(root);
        do {
            if(queueA.size() != 0) {
                queueChange(queueA, queueB);
            } else {
                queueChange(queueB, queueA);
            }
        }while(queueA.size() != 0 || queueB.size() != 0);
    }

    private void queueChange(Queue<TreeLinkNode> a, Queue<TreeLinkNode> b) {
        while(a.size() != 0) {
            TreeLinkNode lastNode  = a.poll();
            if(lastNode.left != null)
                b.offer(lastNode.left);
            if(lastNode.right != null)
                b.offer(lastNode.right);
            lastNode.next = a.peek();
        }
    }
}
