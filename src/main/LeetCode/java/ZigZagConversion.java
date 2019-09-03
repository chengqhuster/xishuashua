package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/zigzag-conversion/
 *
 * 思路简述：直接构造
 *
 */

public class ZigZagConversion {
   public String convert(String s, int numRows) {
       if (numRows == 1) {
           return s;
       }
       int len = s.length();
       int width = getTableWidth(len, numRows);
       char[][] grid = new char[numRows][width];
       int index = 0, state = 0, i = 0, j = 0;
       while (index < len) {
           grid[i][j] = s.charAt(index);
           if (i == 0) {
               state = 1;
           } else if (i == numRows - 1) {
               state = 0;
           }
           if (state == 1) {
               i++;
           } else {
               i--;
               j++;
           }
           index++;
       }
       StringBuilder res = new StringBuilder();
       for (char[] chars : grid) {
           for (char c : chars) {
               if (c !=- 0) {
                   res.append(c);
               }
           }
       }
       return res.toString();
   }

   private int getTableWidth(int total, int height) {
       int period = height + height - 2;
       return (total/period + 1) * (height - 1);
   }
}
