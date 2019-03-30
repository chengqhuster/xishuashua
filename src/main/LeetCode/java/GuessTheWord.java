package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/guess-the-word/
 *
 * 思路简述：https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
 *          随机以及MinMax的思想
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuessTheWord {
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (String a : wordlist) {
                int count = 0;
                for (String b : wordlist) {
                    if (matchCount(a, b) == 0) {
                        count++;
                    }
                }
                map.put(a, count);
            }
            int minCount = 1000;
            String key = null;
            for (String s : wordlist) {
                if (map.get(s) < minCount) {
                    minCount = map.get(s);
                    key = s;
                }
            }
            int x = master.guess(key);
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist) {
                if (matchCount(key, w) == x) {
                    wordlist2.add(w);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }

    private int matchCount(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) == b.charAt(i)) {
                matches ++;
            }
        }
        return matches;
    }

    public class Master {
        public int guess(String s) {
            return 0;
        }
    }
}
