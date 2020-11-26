package LeetCode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/word-search-ii/
 *
 * 思路简述：构造一个字符串树, 然后 DFS 寻找匹配的路径
 */
public class WordSearchII {
    private final int[][] DIRECT = new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        CharTree root = new CharTree((char) 0, null);
        for (String word : words) {
            buildCharTree(root, word);
        }
        List<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (root.containSon(c)) {
                    visited[i][j] = true;
                    dfs(board, i, j, visited, root.getSon(c), res);
                    visited[i][j] = false;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, boolean[][] visited, CharTree charTree, List<String> res) {
        if (charTree.isEndOfWord()) {
            res.add(charTree.getWord());
        }
        int xLen = board.length, yLen = board[0].length;
        for (int[] direct : DIRECT) {
            int ii = i + direct[0];
            int jj = j + direct[1];
            if (ii < 0 || ii >= xLen || jj < 0 || jj >= yLen || visited[ii][jj]) {
                continue;
            }
            if (charTree.containSon(board[ii][jj])) {
                visited[ii][jj] = true;
                dfs(board, ii, jj, visited, charTree.getSon(board[ii][jj]), res);
                visited[ii][jj] = false;
            }
        }
    }

    private void buildCharTree(CharTree root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            root.addSon(c);
            root = root.getSon(c);
        }
        root.setEndFlag();
    }

    private static class CharTree {
        private char c;
        private boolean endOfWord;
        private CharTree father;
        private Map<Character, CharTree> sons;

        public CharTree(char c, CharTree father) {
            this.c = c;
            this.father = father;
            this.endOfWord = false;
            this.sons = new HashMap<>();
        }

        public void addSon(char c) {
            sons.putIfAbsent(c, new CharTree(c, this));
        }

        public boolean containSon(char c) {
            return sons.containsKey(c);
        }

        public CharTree getSon(char c) {
            return sons.get(c);
        }

        public char getC() {
            return c;
        }

        public CharTree getFather() {
            return father;
        }

        public void setEndFlag() {
            this.endOfWord = true;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public String getWord() {
            StringBuilder sb = new StringBuilder();
            CharTree ct = this;
            while (ct.getFather() != null) {
                sb.append(ct.getC());
                ct = ct.getFather();
            }
            // 相同字符串仅能获取一次
            this.endOfWord = false;
            return sb.reverse().toString();
        }
    }
}
