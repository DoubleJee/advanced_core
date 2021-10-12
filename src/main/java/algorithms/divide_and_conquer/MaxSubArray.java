package algorithms.divide_and_conquer;

// 最大连续子序列和
public class MaxSubArray {

    public static void main(String[] args) {
        int[] arrays = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(dacMaxSubArray(arrays));

    }

    // 暴力解法 O(n^3)
    static int violenceMaxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        for (int beign = 0; beign < nums.length; beign++) {
            for (int end = beign; end < nums.length; end++) {
                // 每个连续子序列的和
                int sum = 0;
                for (int i = beign; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // 暴力优化 O(n^2)
    static int violenceMaxSubArrayOp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        for (int beign = 0; beign < nums.length; beign++) {
            int sum = 0;
            for (int end = beign; end < nums.length; end++) {
                // 前面子序列和，用来加上当前这个整数，求出新子序列的和
                sum += nums[end];
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    // 分治解法
    static int dacMaxSubArray(int [] nums) {
        if (nums == null || nums.length == 0) return 0;
        return dacMaxSubArray(nums, 0, nums.length);
    }
    /**
     * 求[begin, end) 中最大连续子序列的和
     * 一分为二，最大和要么在左边部分，要么在右边部分，要么左右边界相连（两边都有）的部分
     */
    static int dacMaxSubArray(int [] nums, int begin, int end) {
        // 递归基，子序列个数小于2，那它就是最大子序列和
        if (end - begin < 2) return nums[begin];

        // 将序列一分为二
        int mid = (begin + end) >> 1;
        // 求左右两部分的最大连续子序列和
        int leftMax = dacMaxSubArray(nums, begin, mid);
        int rightMax = dacMaxSubArray(nums, mid, end);
        // 比较得到两部分间最大连续子序列和
        int maxLeftRight = Math.max(leftMax, rightMax);

        // 左右两部分边界相连的部分，这个子序列必须包含[mid - 1, mid]
        // 因此左边找到 [i, mid -1] 最大和部分，右边找到[mid, j] 最大和部分，左右之和相加得到左右边界相连最大和，[i, ···, mid - 1, mid, ···, j]

        // 从mid - 1向左边寻找连续最大和
        int leftLinkMax = nums[mid - 1];
        int leftLinkSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftLinkSum += nums[i];
            leftLinkMax = Math.max(leftLinkMax, leftLinkSum);
        }

        // 从mid向右边寻找连续最大和
        int rightLinkMax = nums[mid];
        int rightLinkSum = 0;
        for (int i = mid; i < end; i++) {
            rightLinkSum += nums[i];
            rightLinkMax = Math.max(rightLinkMax, rightLinkSum);
        }


        // 最后比较 左右边界相连最大和 与 左右部分最大和，得到整个序列最大连续子序列和
        return Math.max(leftLinkMax + rightLinkMax, maxLeftRight);
    }
}
