package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/rearrange-spaces-between-words/
 *
 * 思路简述：easy
 */
public class RearrangeSpacesBetweenWords {

    public String reorderSpaces(String text) {
        int wordCount = 0, blankCount = 0;
        boolean inWord = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                blankCount++;
                inWord = false;
            } else {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            }
        }

        int blankCountPerWord = wordCount == 1 ? 0 : blankCount / (wordCount - 1);
        char[] store = new char[text.length()];
        int pos = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != ' ') {
                store[pos++] = text.charAt(i);
                if (i + 1 == text.length() || text.charAt(i + 1) == ' ') {
                    wordCount--;
                    if (wordCount == 0) {
                        break;
                    }
                    for (int j = 0; j < blankCountPerWord; j++) {
                        store[pos++] = ' ';
                    }
                }
            }
        }
        while (pos < store.length) {
            store[pos++] = ' ';
        }

        return new String(store);
    }
}
