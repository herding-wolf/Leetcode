package leetcode;

import org.junit.Test;

/**
 * @author luochong
 * @date 2019/8/5 13:34
 */
public class Dome4 {

    @Test
    public void test() {
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3, 4};
        double response = new Solution().findMedianSortedArrays(nums1, nums2);
        System.out.println(response);
    }

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 让长数组在前面
            if (nums1.length < nums2.length) {
//                return this.findMedianSortedArrays(nums2, nums1);
                int[] tem = nums1;
                nums1 = nums2;
                nums2 = tem;
            }
            int m = nums1.length, n = nums2.length;
            int h = (m + n + 1) / 2, i = 0, j = h - i, li = 0, ri = n;
            while (li < ri) {
                i = (ri + li) / 2;
                j = h - i;
                if (nums2[i] < nums1[j - 1]) {
                    j = h - (li = ++i);
                } else {
                    ri = i;
                }
            }
            double leftValue = Math.max(i <= 0 ? 0 : nums2[i - 1], j <= 0 ? 0 : nums1[j - 1]);
            if ((m + n) % 2 == 1) {
                return leftValue;
            }
            double rightValue = Math.min(i >= n ? nums1[j] : nums2[i], j >= m ? nums2[i] : nums1[j]);
            return (leftValue + rightValue) / 2;
        }
    }
}


