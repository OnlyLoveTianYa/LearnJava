package leetcode.array.find_median_sorted_arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。 示例 1: nums1 = [1,
 * 3] nums2 = [2] 中位数是 2.0 示例 2: nums1 = [1, 2] nums2 = [3, 4] 中位数是 (2 + 3)/2 = 2.5
 *
 * @author : OnlyLoveTianYa
 */
public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] nums1 = {3};
    int[] nums2 = {1, 2};
    System.out.println(s.findMedianSortedArrays(nums1, nums2));
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    if (n == 0 && m == 0) {
      return -1;
    }
    if (n == 0) {
      return ((double) (nums2[(m - 1) / 2] + nums2[m / 2])) / 2;

    }
    if (m == 0) {
      return ((double) (nums1[(n - 1) / 2] + nums1[n / 2])) / 2;
    }
    if (n > m) {
      return findMedianSortedArrays(nums2, nums1);
    }
    int l1 = 0, r1 = 0, c1, l2 = 0, r2 = 0, c2, lo = 0;
    //数组虚拟加了#，使数组长度为2n+1 恒为奇数
    int hi = 2 * n;
    while (lo <= hi) {
      c1 = (lo + hi) / 2;//进行2分
      c2 = m + n - c1;
      l1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];//除2为了映射到原对象
      r1 = (c1 == 2 * n) ? Integer.MAX_VALUE : nums1[c1 / 2];
      l2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
      r2 = (c2 == 2 * m) ? Integer.MAX_VALUE : nums2[c2 / 2];
      if (l1 > r2) {
        hi = c1 - 1;
      } else if (l2 > r1) {
        lo = c1 + 1;
      } else {
        break;
      }
    }
    return ((double) (Integer.max(l1, l2) + Integer.min(r1, r2))) / 2;
  }
}
