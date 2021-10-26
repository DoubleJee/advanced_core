package algorithms.dynamic_programming;

// 最长公共子序列
public class LCS {

    public static void main(String[] args) {
        System.out.println(lcs4(new int[]{1, 3, 9, 8}, new int[]{1, 9, 10}));
    }

    /**
     * dp(i, j) = nums1前i个元素和nums2前j个元素的最长公共子序列长度
     * dp(i, 0) = dp(0, j) = 0      （因为另外一个子序列的长度为0，肯定没有公共子序列）
     *
     * 如果nums1[i - 1] == nums2[j - 1]，那么 dp(i, j) = dp(i - 1, j - 1) + 1                第i个元素和第j个元素相等，就拿除去它俩的最长公共子序列 + 1，就是dp(i - 1, j - 1) + 1
     * 如果nums1[i - 1] != nums2[j - 1]，那么 dp(i, j) = max{dp(i - 1, j), dp(i, j - 1)}     第i个元素和第j个元素不相等，那就看看包含i的最长公共子序列 和 包含j的最长公共子序列，哪个大取哪个; 之前的加上第i个对比的（i, j - 1）、之前的加上第j个对比的（i - 1, j），都是i,j的前一个小规模状态
     */


    // 递归实现
    // 空间复杂度：O(k)，k = min{n,m}，因为边界条件的原因，就是两个序列长度最小值
    // 时间复杂度：O(2^n)，每次递归都会再递归2次，指数级增长
    static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }
    static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        // 边界条件
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcs1(nums1, i, nums2, j - 1),
                lcs1(nums1, i - 1, nums2, j));
    }


    // 动态规划
    // 时间、空间复杂度：O(n * m); n、m为两个序列的长度
    static int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 初始状态 (i, 0)，(0, j) 都为0， 所以 i、j 从1开始
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }

    // 滚动数组优化 空间复杂度：O(m)
    static int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        // 根据研究的表格，每次求状态的时候只需要用到两行数据，上一行和当前行，之前的数据都没用了，所以使用滚动数组  （求每个状态使用其左斜、前面、上面三个值，共两行数据）
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            // 上一行、当前行
            int prevRow = (i - 1) % 2;
            int row = i % 2;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 上一行退一格（左斜） + 1
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {
                    // 当前行退一格（前面），上一行（上面）
                    dp[row][j] = Math.max(dp[row][j - 1], dp[prevRow][j]);
                }
            }
        }

        return dp[nums1.length % 2][nums2.length];
    }

    // 一维数组优化 空间复杂度：O(m)
    static int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        // 根据研究的表格，每次求状态的时候只需要用到上一行数据，之前的数据都没用了，因此使用一维数组，再加一个leftTop变量用来存储要被覆盖的左上角 （求每个状态使用其左斜、前面、上面三个值，左斜和上面是上一行数据，再加上其前一个状态的值）
        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            // 左斜   （存储会被覆盖找不到的左斜）
            int leftTop = dp[0];
            for (int j = 1; j <= nums2.length; j++) {
                // 左斜值副本
                int tempLeftTop = leftTop;
                // 左斜更新    （当前dp[j]未被计算覆盖，是下一个的左斜，也就是上一行j，是下一行j + 1的左斜）
                leftTop = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 上一行退一格（左斜） + 1
                    dp[j] = tempLeftTop + 1;
                } else {
                    // 当前行退一格（前面），上一行（上面）
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
            }
        }

        return dp[nums2.length];
    }

    // 短列一维数组优化 空间复杂度：O(min{n,m})
    static int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        // 基于一维数组优化，可以选择长度短的作为列（数）来看，来节省一维数组的长度
        int[] rowsNums = nums1, colsNums = nums2;
        if (nums1.length < nums2.length) {
            rowsNums = nums2;
            colsNums = nums1;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowsNums.length; i++) {
            int leftTop = dp[0];
            for (int j = 1; j <= colsNums.length; j++) {
                int tempLeftTop = leftTop;
                leftTop = dp[j];
                if (rowsNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = tempLeftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
            }
        }

        return dp[colsNums.length];
    }

}
