package leetcode.string.length_of_longest_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        assert 3 == s.lengthOfLongestSubstring("abcabcbb");
        assert 1 == s.lengthOfLongestSubstring("bbbbb");
        assert 3 == s.lengthOfLongestSubstring("pwwkew");
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maximumLength = 0;
        int nowMaximumLength = 0;
        //当前不重复字符串的数组起始下标
        int index = 0;
        //字符串池 存放字符串和其出现的位置，用来判断是否出现子串
        Map<Character, Integer> charPool = new HashMap<>(36);
        for (int i = 0; i < chars.length; i++) {
            //获取重复字符串中的前一个的数组下标，并刷新此字符串的下标
            Integer repeatedPosition = charPool.put(chars[i], i);
            //为空代表没有重复的字符串
            if (repeatedPosition == null) {
                nowMaximumLength++;
            } else {
                //发现重复字符串，根据当前不重复子串长度判断是否更新子串最长长度
                if (nowMaximumLength > maximumLength) {
                    maximumLength = nowMaximumLength;
                }
                //更新当前不重复子串长度
                nowMaximumLength = i - repeatedPosition;
                //从池中清除重复的子串之前的字符
                for (int t = index; t < repeatedPosition; t++) {
                    charPool.remove(chars[t]);
                }
                //更新当前不重复字符串的数组起始下标
                index = repeatedPosition + 1;
            }
        }
        return nowMaximumLength > maximumLength ? nowMaximumLength : maximumLength;
    }
}
