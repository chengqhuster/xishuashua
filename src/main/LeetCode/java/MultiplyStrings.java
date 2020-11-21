package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/multiply-strings/
 *
 * 思路简述：按乘法规则实现
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int minLen = Math.min(num1.length(), num2.length());
        String shorter = num1.length() == minLen ? num1 : num2;
        String longer = num1.length() == minLen ? num2 : num1;
        String[] temp = new String[shorter.length()];
        for (int i = shorter.length() - 1; i >= 0; i--) {
            temp[i] = moveLeft(multiply(longer, shorter.charAt(i) - '0'), shorter.length() - 1 - i);
        }
        String res = "0";
        for (int i = 0; i < temp.length; i++) {
            res = add(res, temp[i]);
        }
        return res;
    }

    private String moveLeft(String num, int n) {
        StringBuilder res = new StringBuilder(num);
        for (int i = 0; i < n; i++) {
            res.append(0);
        }
        return res.toString();
    }

    private String multiply(String num, int n) {
        if (n == 0) {
            return"0";
        }
        int flag = 0;
        char[] store = new char[num.length()];
        for (int i = num.length() - 1; i >= 0; i--) {
            int sum = (num.charAt(i) - '0') * n + flag;
            flag = sum / 10;
            store[i] = (char) (sum % 10 + '0');
        }
        if (flag > 0) {
            return flag + String.valueOf(store);
        } else {
            return String.valueOf(store);
        }
    }

    private String add(String num1, String num2) {
        String numRevert1 = new StringBuilder(num1).reverse().toString();
        String numRevert2 = new StringBuilder(num2).reverse().toString();
        char[] store = new char[Math.max(numRevert1.length(), numRevert2.length())];
        int flag = 0;
        for (int i = 0; i < store.length; i++) {
            int a = numRevert1.length() > i ? numRevert1.charAt(i) - '0' : 0;
            int b = numRevert2.length() > i ? numRevert2.charAt(i) - '0' : 0;
            int sum = a + b + flag;
            flag = sum / 10;
            store[i] = (char) (sum % 10 + '0');
        }
        StringBuilder res = new StringBuilder(String.valueOf(store));
        if (flag > 0) {
            res.append(flag);
        }
        return res.reverse().toString();
    }
}
