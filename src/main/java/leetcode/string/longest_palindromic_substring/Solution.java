package leetcode.string.longest_palindromic_substring;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author : OnlyLoveTianYa
 */
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestPalindrome("ababababa"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        if (s.length() == 2) {
            if (s.substring(0, 1).equals(s.substring(1, 2))) {
                return s;
            }
            return s.substring(0, 1);
        }
        char[] chars = s.toCharArray();
        //存放前两位数字
        char[] previous = new char[2];
        previous[0] = chars[0];
        previous[1] = chars[1];
        //存放AXA的回文种子的中间坐标即X的坐标
        List<Integer> AXASeeds = new ArrayList<>();
        //存放BB的回文种子的中间坐标即第一个B的坐标
        List<Integer> BBSeeds = new ArrayList<>();
        if (previous[0] == previous[1]) {
            BBSeeds.add(0);
        }
        for (int i = 2; i < chars.length; i++) {
            //AXA种子
            if (chars[i] == previous[0]) {
                AXASeeds.add(i - 1);
            }
            //BB种子
            if (chars[i] == previous[1]) {
                BBSeeds.add(i - 1);
            }
            previous[0] = previous[1];
            previous[1] = chars[i];
        }
        //种子成长次数
        int AXAnums = 0;
        int BBnums = 0;
        //种子成长之后的集合
        List<Integer> AXASeedsGrow = new ArrayList<>();
        List<Integer> BBSeedsGrow = new ArrayList<>();

        boolean AXAContinue;
        do {
            AXAContinue = false;
            for (int i = 0; i < AXASeeds.size(); i++) {
                //种子成长一次
                if (AXASeeds.get(i) - AXAnums - 2 >= 0 && AXASeeds.get(i) + AXAnums + 2 < chars.length) {
                    if (chars[AXASeeds.get(i) - AXAnums - 2] == chars[AXASeeds.get(i) + AXAnums + 2]) {
                        AXASeedsGrow.add(AXASeeds.get(i));
                    }
                }
            }
            //成长一次后的种子不为空
            if (!AXASeedsGrow.isEmpty()) {
                AXAnums++;
                AXAContinue = true;
                AXASeeds = AXASeedsGrow;
                AXASeedsGrow = new ArrayList<>();
            }
        } while (AXAContinue);
        boolean BBContinue;
        do {
            BBContinue = false;
            for (int i = 0; i < BBSeeds.size(); i++) {
                //种子成长一次
                if (BBSeeds.get(i) - BBnums - 1 >= 0 && BBSeeds.get(i) + BBnums + 2 < chars.length) {
                    if (chars[BBSeeds.get(i) - BBnums - 1] == chars[BBSeeds.get(i) + BBnums + 2]) {
                        BBSeedsGrow.add(BBSeeds.get(i));
                    }
                }
            }

            //成长一次后的种子不为空
            if (!BBSeedsGrow.isEmpty()) {
                BBSeeds = BBSeedsGrow;
                BBnums++;
                BBContinue = true;
                BBSeedsGrow = new ArrayList<>();
            }
        } while (BBContinue);

        if (AXASeeds.isEmpty() && BBSeeds.isEmpty()) {
            return s.substring(0, 1);
        }

        if (BBnums > AXAnums) {
            Integer index = BBSeeds.get(0);
            StringBuilder sb = new StringBuilder();
            for (int i = index - BBnums; i <= index + BBnums + 1; i++) {
                sb.append(chars[i]);
            }
            return sb.toString();
        } else {
            if (AXASeeds.isEmpty()) {
                Integer index = BBSeeds.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = index - BBnums; i <= index + BBnums + 1; i++) {
                    sb.append(chars[i]);
                }
                return sb.toString();

            } else {
                Integer index = AXASeeds.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = index - AXAnums - 1; i <= index + AXAnums + 1; i++) {
                    sb.append(chars[i]);
                }
                return sb.toString();
            }
        }
    }
}
