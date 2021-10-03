package LeetCode.java.DataStruct;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/lru-cache/
 *
 * 思路简述：map 和 double linklist
 */
public class LRUCache {

    /**
     * 标记节点
     */
    private final ItemNode head = new ItemNode();

    private ItemNode tail;

    private final Map<Integer, ItemNode> map = new HashMap();

    private int size;

    private final int capacity;

    static class ItemNode {
        private int key;
        private int value;
        private ItemNode prev, next;
    }

    public LRUCache(int capacity) {
        this.tail = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ItemNode node = map.get(key);
            updateNode(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ItemNode node = map.get(key);
            node.value = value;
            updateNode(node);
        } else {
            if (size == capacity) {
                map.remove(tail.key);
                tail = tail.prev;
                tail.next = null;
                size--;
            }
            ItemNode node = new ItemNode();
            node.key = key;
            node.value = value;
            node.next = head.next;
            if (head.next != null) {
                head.next.prev = node;
            } else {
                tail = node;
            }
            node.prev = head;
            head.next = node;

            size++;
            map.put(key, node);
        }
    }

    /**
     * 访问或者更新缓存时，更新LRU链表
     * @param node
     */
    private void updateNode(ItemNode node) {
        if (head.next != node) {
            node.prev.next = node.next;
            if (node.next == null) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }
}
