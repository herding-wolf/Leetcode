package leetcode;

import org.junit.Test;

/**
 * @author luochong
 * @date 2019/8/5 13:34
 */
public class Dome4 {

    @Test
    public void test() {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums2 = { 3, 4, 5, 6};
        double response = new Solution().findMedianSortedArrays(nums1, nums2);
        System.out.println(response);
    }

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 让长数组在前面
            if (nums1.length < nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            // 查询长数组中位数左右下标
            int[] medianIndex = findMedianIndex(nums1);
            // 长数组中位数值
            double medianValue = (nums1[medianIndex[0]] + nums1[medianIndex[1]]) / 2;
            // 如果短数组为空则返回长数组中位数值
            if (nums2.length == 0) {
                return medianValue;
            }

            // 查询长数组中位数值在短数组左右下标
            int[] medianIndex2 = findMedianIndex(nums2, medianValue);
            boolean isLeftMove = (medianIndex2[0] + 1) < (nums2.length - medianIndex2[1]);
            int diffLength = Math.abs((medianIndex2[0] + 1) - (nums2.length - medianIndex2[1]));
            double leftValue = 0, rightValue = 0;
            if (nums1.length % 2 == 1) {
                diffLength--;
                medianIndex[0]--;
                leftValue = nums1[medianIndex[0]];
                rightValue = nums1[medianIndex[1]];
            }
            if (medianIndex2[1] == medianIndex2[0]) {
                diffLength--;
                medianIndex2[0]--;
                leftValue = nums2[medianIndex2[0]] > leftValue ? nums2[medianIndex2[0]] : leftValue;
                rightValue = nums2[medianIndex2[1]];
            }

            if (diffLength < 0) {
                return medianValue;
            } else if (diffLength == 0) {
                return (leftValue + rightValue) / 2;
            }

            while (diffLength > 0) {
                if (isLeftMove) {

                } else {
                    if (nums2[medianIndex2[0]] >= nums1[medianIndex[0]]) {
                        rightValue = nums2[medianIndex2[0]];
                        leftValue = nums1[medianIndex[0]];
                        medianIndex2[0]--;
                    } else {
                        rightValue = nums1[medianIndex[0]];
                        leftValue = nums2[medianIndex2[0]];
                        medianIndex[0]--;
                    }
                }
                diffLength--;
            }

            return (leftValue + rightValue) / 2;
        }

        public int[] findMedianIndex(int[] nums) {
            int index = nums.length / 2;
            if (nums.length % 2 == 0) {
                return new int[]{index - 1, index};
            } else {
                return new int[]{index, index};
            }
        }

        public int[] findMedianIndex(int[] nums, double value) {
            return findMedianIndex(nums, 0, nums.length - 1, value);
        }

        public int[] findMedianIndex(int[] nums, int start, int end, double value) {
            if (end == start) {
                if (nums[start] == value) {
                    return new int[]{start, end};
                } else if (start == 0) {
                    return new int[]{-1, 0};
                } else if (end == nums.length){
                    return new int[]{end, end + 1};
                } else {
                    throw new RuntimeException("--------bug---------");
                }
            }
            int median = (start + end) / 2;
            if (nums[median] == value) {
                return new int[]{median, median};
            } else if (nums[median] > value) {
                if (nums[median - 1] < value) {
                    return new int[]{median - 1, median};
                }
                return findMedianIndex(nums, start, median - 1, value);
            } else {
                if (nums[median + 1] > value) {
                    return new int[]{median, median + 1};
                }
                return findMedianIndex(nums, median + 1, end, value);
            }
        }
    }
}


