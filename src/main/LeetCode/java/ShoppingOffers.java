package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/shopping-offers/
 *
 * 思路简述：注意题目要求 You are not allowed to buy more items than you want, even if that would lower the overall price
 *           暴力的dfs，pos可减少计算量，复杂度很高 special.size() ^ sum(need)（所以题目约定了special大小和needs的和）
 *
 */

import java.util.ArrayList;
import java.util.List;

public class ShoppingOffers {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs, 0);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int min = getDirectBuyPrice(price, needs);
        for (int i = pos; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> next = new ArrayList<>();
            for (int j = 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) {
                    next = null;
                    break;
                }
                next.add(needs.get(j) - offer.get(j));
            }

            if (next != null) {
                min = Math.min(min, offer.get(offer.size() - 1) + dfs(price, special, next, i));
            }
        }
        return min;
    }

    private int getDirectBuyPrice(List<Integer> price, List<Integer> needs) {
        int res = 0;
        for (int i = 0; i < price.size(); i++) {
            res += price.get(i) * needs.get(i);
        }
        return res;
    }
}
