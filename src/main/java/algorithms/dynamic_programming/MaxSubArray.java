package algorithms.dynamic_programming;

// 最大连续子序列和 动态规划解法
public class MaxSubArray {


    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums));

    }

    /**
     * dp(i) = 以nums[i]结尾的最大连续子序列和
     * dp(0) = nums[0]
     * dp(i) = max {dp(i - 1) + nums[i], nums[i]}，  当前结尾最优解，是前一个结尾最优解加上自己，或者不加
     *
     * 最终的解是 max{dp(0),...,dp(nums.length - 1)}
     * 空间、时间复杂度：O(n)
     */
    static int maxSubArray(int [] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        // 初始状态
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // dp[i] 要么以是前一个结尾加上自己（连续），要么是自己 （不和前面的连续）
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 优化空间复杂度O(1)  每次只需要用到上一个的解，再前面的没作用了
    static int maxSubArray2(int [] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            // 前一个小于等于0，加上对我只会更少或者不变，就不加
            if (dp <= 0) {
                dp = nums[i];
            } else {
                // 前一个大于0，加上会让我变大
                dp = dp + nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }
}
