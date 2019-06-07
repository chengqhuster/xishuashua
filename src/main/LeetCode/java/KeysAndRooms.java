package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/keys-and-rooms/
 *
 * 思路简述：dfs
 *
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return false;
        }
        int n = rooms.size();
        boolean[] state = new boolean[n];
        state[0] = true;
        Queue<Integer> queue = new LinkedList<>(rooms.get(0));
        while (!queue.isEmpty()) {
            int room = queue.poll();
            if (!state[room]) {
                state[room] = true;
                queue.addAll(rooms.get(room));
            }
        }
        for (boolean f : state) {
            if (!f) {
                return false;
            }
        }
        return true;
    }
}
