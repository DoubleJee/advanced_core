package algorithms.dynamic_programming;

// 找零钱问题
public class CoinChange {
    /**
     * 硬币面值为 25、20、5、1
     *
     * dp(n) = 凑够n分钱的最小硬币数
     * 第一枚硬币选择25，dp(n) = dp(n - 25) + 1
     * 第一枚硬币选择20，dp(n) = dp(n - 20) + 1
     *
     * dp(n) = min{dp(n - 25), dp(n - 20), dp(n - 5), dp(n - 1)} + 1
     *
     * dp(n) = 所有选择中的最小硬币数 + 1
     */



    public static void main(String[] args) {
        int[] faces = {25, 20, 5, 1};
        System.out.println(coins5(41, faces));
    }



    /**
     * 暴力递归 （自顶向下，出现重叠子问题：子问题进行了重复计算）
     */
    static int coins1(int n) {
        // 递归基， n = 25、20、5、1 直接求得解是1
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;

        // min{dp(n - 25), dp(n - 20), dp(n - 5), dp(n - 1)}
        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));

        // min + 1
        return Math.min(min1, min2) + 1;
    }


    /**
     * 记忆化搜索（自顶向下）
     */
    static int coins2(int n) {
        if (n < 1) return -1;
        // 数组存储子问题计算结果 记忆化
        int[] dp = new int[n + 1];

        // 递归基  和面值一样的直接得出解
        int[] faces = {25, 20, 5, 1};
        for (int face : faces) {
            if (n < face) continue;
            dp[face] = 1;
        }
        return coins2(n, dp);
    }
    static int coins2(int n, int[] dp) {
        // 递归基 不符合的凑法
        if (n < 0) return Integer.MAX_VALUE;
        // 未计算过
        if (dp[n] == 0) {
            // min{dp(n - 25), dp(n - 20), dp(n - 5), dp(n - 1)}
            int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
            int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
            // 将计算结果保存
            dp[n] = Math.min(min1, min2) + 1;
        }
        // 已经计算过
        return dp[n];
    }


    /**
     * 递推（自底向上，非递归、迭代）
     * 时间、空间复杂度：O(n)
     */
    static int coins3(int n) {
        if (n < 1) return -1;
        // 数组存储子问题计算结果
        int[] dp = new int[n + 1];
        int[] faces = {25, 20, 5, 1};

        // 从最小问题开始递推，将计算结果保存，用于求大问题的最优解
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            // dp(i) = min{dp(i - 25), dp(i - 20), dp(i - 5), dp(i - 1)} + 1
            for (int face : faces) {
                if (i < face) continue;
                min = Math.min(min, dp[i - face]);
            }
            // 将计算结果保存
            dp[i] = min + 1;
        }
        return dp[n];
    }


    /**
     * 递推 传入不同面值，无法凑齐为-1
     */
    static int coins4(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) return -1;
        // 数组存储子问题计算结果
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            // dp(i) = min{dp(i - x), dp(i - y), dp(i - z)} + 1
            for (int face : faces) {
                // 选了后无法凑够也跳过这个面值 （因为无法凑-1，与min比较，会有歧义）
                if (i < face || dp[i - face] < 0) continue;
                min = Math.min(min, dp[i - face]);
            }

            // 如果无法凑设置为-1，将计算结果保存
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : min + 1;
        }
        return dp[n];
    }

    /**
     * 递推 同上 + 打印凑的硬币
     */
    static int coins5(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) return -1;
        // 数组存储子问题计算结果
        int[] dp = new int[n + 1];
        // coins[n]是凑够n分钱的最后那枚硬币
        int[] coins = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int coin = -1;
            // dp(i) = min{dp(i - x), dp(i - y), dp(i - z)} + 1
            for (int face : faces) {
                // 选了后能凑够，才进行min比较 （因为无法凑-1，与min比较，会有歧义）
                if (i >= face && dp[i - face] >= 0 && min > dp[i - face] ) {
                    min = dp[i - face];
                    coin = face;
                }
            }

            // 将计算结果保存
            dp[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
            coins[i] = coin;
        }
        print(coins, n);
        return dp[n];
    }

    // 打印凑够n分钱的最小硬币组合
    static void print(int[] coins, int n) {
        System.out.print("[" + n + "] = ");
        while (n > 0) {
            System.out.print(coins[n] + " ");

            // 无法凑够则跳出循环
            if (coins[n] < 0) break;
            n -= coins[n];
        }
        System.out.println();
    }
}
