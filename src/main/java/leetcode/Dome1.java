package leetcode;

import java.util.Arrays;

/**
 * @author luochong
 * @date 2019/8/2 10:59
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 */
public class Dome1 {

    public static void main(String[] args) {
        int[] nums = new int[]{230,863,916,585,981,404,316,785,88,12,70,435,384,778,887,755,740,337,86,92,325,422,815,650,920,125,277,336,221,847,168,23,677,61,400,136,874,363,394,199,863,997,794,587,124,321,212,957,764,173,314,422,927,783,930,282,306,506,44,926,691,568,68,730,933,737,531,180,414,751,28,546,60,371,493,370,527,387,43,541,13,457,328,227,652,365,430,803,59,858,538,427,583,368,375,173,809,896,370,789};
        int target = 542;
        System.out.println(Arrays.toString(new Solution().twoSum(nums, target)));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int indexArrayMax = 2047;
            int[] indexArray = new int[indexArrayMax + 1];
            int diff;
            for (int i=0; i<nums.length; i++) {
                diff = target - nums[i];
                if (indexArray[diff & indexArrayMax] != 0) {
                    return new int[]{indexArray[diff & indexArrayMax] - 1, i};
                }
                indexArray[nums[i] & indexArrayMax] = i + 1;
            }
            return null;
        }
    }
}
