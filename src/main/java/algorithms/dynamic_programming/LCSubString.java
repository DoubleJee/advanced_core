package algorithms.dynamic_programming;

// 最长公共子串
public class LCSubString {

    public static void main(String[] args) {
        System.out.println(lcs2("ABCBA", "BABCA"));
    }

    /**
     * dp(i, j) = 以str1[i - 1]、str2[j - 1]结尾的最长公共子串长度       （因为dp需要记录有另外一个子串的长度为0的情况，所以dp[1, 0] 代表 str1的第1个元素和str2的第0个元素结尾，也就是dp(i, j) = str1[i - 1]，str2[j - 1]）
     * dp(i, 0)、dp(0、j) = 0      （另外一个子串的长度为0，肯定没有公共子串）
     *
     * 如果str1[i - 1] == str2[j - 1]，那么 dp(i, j) = dp(i - 1, j - 1) + 1      第i个元素和第j个元素相等，就拿以它俩前面结尾的最长公共子串长度 + 1
     * 如果str1[i - 1] != str2[j - 1]，那么 dp(i, j) = 0                         第i个元素和第j个元素不相等，那么以它俩结尾的最长公共子串长度一定为0，因为它俩是尾部都不相等，前面的不用找了，没有意义，构不成公共子串
     *
     * 最终的解 max{dp(1,1), ..., dp(str1.length,str2.length)}，     所有可能结尾的最优选择中最大的
     */

    // 动态规划 时间、空间复杂度：O(n * m)
    static int lcs(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) return 0;

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = dp[0][0];
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                // 相等，前个结尾公共子序列长度 + 1
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不相等，它俩结尾最长公共子序列长度为0
                    dp[i][j] = 0;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }


    // 一维数组短列优化 时间复杂度：O(n * m)，空间复杂度：O(min{n, m})
    static int lcs2(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) return 0;

        char[] rowChars = chars1, colChars = chars2;
        if (chars1.length < chars2.length) {
            rowChars = chars2;
            colChars = chars1;
        }
        // 长度短的作为一维数组长度
        int[] dp = new int[colChars.length + 1];
        int max = dp[0];
        for (int i = 1; i <= rowChars.length; i++) {
            // 因为求状态只会用到上一行数据，不会用到同一行的数据，可以从后往前求状态，去除leftTop变量
            int leftTop = dp[0];
            for (int j = 1; j <= colChars.length; j++) {
                int tempLeftTop = leftTop;
                leftTop = dp[j];
                if (rowChars[i - 1] == colChars[j - 1]) {
                    dp[j] = tempLeftTop + 1;
                } else {
                    // 一维数组要复用 必须设置为0， 陷阱
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }

        return max;
    }
}
