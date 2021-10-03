package LeetCode.java.DataStruct;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 题目描述：https://leetcode.com/problems/lfu-cache/
 *
 * 思路简述：使用优先队列，get和put可能会涉及到priority的调整，所以不是O(1)的时间
 *         可以参考 LRUCache 使用双向链表，对于不同的count需要记录其首位的节点位置
 */
public class LFUCache {

    class CacheItem {
        private int key;
        private int value;
        private int count;
        private int version;
    }

    private final Map<Integer, CacheItem> map = new HashMap<>();
    private PriorityQueue<CacheItem> queue;
    private final int capacity;
    private int version = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        if (capacity > 0) {
            this.queue = new PriorityQueue<>(capacity, (o1, o2) -> {
                if (o1.count == o2.count) {
                    return (int) (o1.version - o2.version);
                }
                return o1.count - o2.count;
            });
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            CacheItem item = map.get(key);
            queue.remove(item);
            item.version = version++;
            item.count = item.count + 1;
            queue.offer(item);
            return item.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        long current = System.currentTimeMillis();
        CacheItem item;
        if (map.containsKey(key)) {
            item = map.get(key);
            queue.remove(item);
            item.value = value;
            item.version = version++;
            item.count = item.count + 1;
        } else {
            item = new CacheItem();
            item.key = key;
            item.value = value;
            item.count = 1;
            item.version = version++;
            if (queue.size() == capacity) {
                CacheItem removeItem = queue.poll();
                map.remove(removeItem.key);
            }
            map.put(key, item);
        }
        queue.offer(item);
    }
}
