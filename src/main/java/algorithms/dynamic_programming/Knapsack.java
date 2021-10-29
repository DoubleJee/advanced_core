package algorithms.dynamic_programming;

// 01背包问题
public class Knapsack {

    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;

        System.out.println(maxValueExactly(values, weights, capacity));
    }

    /**
     * dp(i, j) = 有前i件物品可选、最大承重为j时的最大总价值
     * dp(i, 0)、dp(0, j) = 0    （没有物品可选 或 最大承重为0无法放东西，最大总价值都为0）
     *
     *
     * k为物品编号
     * 如果不选择第i件物品 = dp(i - 1, j)
     * 如果选择第i件物品   = dp(i - 1, j - weight[k]) + value[k]
     * 如果 j < weight[k]，则这个物品无法放入背包，属于不选择第i件物品的情况
     *
     * dp(i, j) = max{dp(i - 1, j), dp(i - 1, j - weight[k]) + value[k]}，      选和不选的情况（总价值）挑最大的
     */

    // 动态规划
    static int maxValue(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[][] dp = new int[values.length + 1][capacity + 1];

        // 从只有一件物品和1容量开始状态转移 （简单子问题）
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[values.length][capacity];
    }

    // 一维数组优化
    static int maxValue1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        // 根据研究的二维表格，每次求状态时只需要用到上一行数据，之前的数据都没用了，因此使用一维数组
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            // 倒序计算，因为只需要用到上一行数据，没有本行数据，这里的倒序为了方便我们使用一维数组的替换 （正序替换会导致数据错乱）
            for (int j = capacity; j >= 1; j--) {
                if (j < weights[i - 1]) {
                    // 上一行（上面）
                    dp[j] = dp[j];
                } else {
                    // 上一行（上面） 和 上一行前面某个 （上前）
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }


    // 一维数组代码逻辑优化
    static int maxValue2(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        // 基于对一维数组优化的观察，再次优化，倒序计算，发现承重放不下第i个物品的时候就直接为不选择情况，继承上一行（上面）那个值
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                // 可选择情况， 上一行（上面） 和 上一行前面某个（上前）
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[capacity];
    }

    // 01背包恰好装满 变种问题
    // 返回-1，代表无法恰好装满，返回正数代表恰好装满的最大总价值
    static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[] dp = new int[capacity + 1];

        // 只需要修改初始状态，dp(0, j) = -∞负无穷、dp(i, 0) = 0          （负无穷是为了选和不选的情况对比价值没有歧义，例如 max{5 + -1, -1} ）
        for (int j = 1; j <= capacity; j++) {
            dp[j] = Integer.MIN_VALUE;
        }

        // 状态为负数，代表无法凑满
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                // 可选择情况， 上一行（上面）能恰好凑满最大值 和 上一行前面某个（上前）能恰好凑满最大值;    如果都为负数，则代表选和不选都无法恰好凑满
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }

        // 最后解如果为负数，无法凑满，整数，恰好装满的最大总价值
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

}
