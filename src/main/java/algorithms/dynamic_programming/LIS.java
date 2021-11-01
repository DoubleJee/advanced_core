package algorithms.dynamic_programming;

// 最长上升子序列
public class LIS {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 3, 7, 101, 18}));
    }

    /**
     * dp(i) = 以nums[i]结尾的最长上升子序列长度
     * dp(0) = 1
     * dp(i) = max{dp(j)} + 1，j的范围 = [0,i)，nums[j] < nums[i] 可以组成一个更长的上升子序列，如果j中没有任何一个小于自己符合的，默认dp(i) = 1
     *
     * 最终的解 max{dp(0), ... , dp(nums.length - 1)}，  所有可能结尾的最优选择中最大的
     * 时间复杂度：O(n^2)，空间复杂度：O(n)
     */
    static int lengthOfLIS(int[] nums){
        if (nums == null || nums.length < 1) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // 默认前面没有小于自己符合的
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                // 找小于自己符合的，取最长的那个 + 1
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }

            max = Math.max(max, dp[i]);
        }
        return max;
    }



    // 二分搜索 + 牌顶法 时间复杂度：O(nlogn)
    static int lisBinary(int [] nums) {
        if (nums == null || nums.length < 1) return 0;

        // 牌堆数量
        int len = 0;
        // 牌顶数组
        int[] top = new int[nums.length];


        // 遍历每张牌，寻找第一个大于等于自己的牌顶，并放上去替换成为这个牌堆的新牌顶，如果没有，则创建一个新的牌堆并将自己设为牌顶
        // 牌顶之间是有序的，可以使用二分搜索，查找第一个大于等于自己的牌顶位置
        for (int num : nums) {      // n
            int begin = 0;
            int end = len;
            // 寻找第一个大于等于num的牌顶位置
            while (begin < end) {   // logn
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }

            // 覆盖牌顶
            top[begin] = num;
            // 检查是否是新建一个了牌堆     （如果找到的位置是超出的，就是新建了）
            if (begin == len) len++;
        }

        // 最后牌堆数量就是最长上升子序列长度
        return len;
    }
}