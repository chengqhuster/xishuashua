package LeetCode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 题目描述：https://leetcode.com/problems/jump-game-iii/
 *
 * 思路简述：bfs + memory
 */
public class JumpGameIII {

    public boolean canReach(int[] arr, int start) {

        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (arr[index] == 0) {
                return true;
            }
            if (index + arr[index] < arr.length && visited.add(index + arr[index])) {
                queue.offer(index + arr[index]);
            }
            if (index - arr[index] >= 0 && visited.add(index - arr[index])) {
                queue.offer(index - arr[index]);
            }
        }
        return false;
    }
}
