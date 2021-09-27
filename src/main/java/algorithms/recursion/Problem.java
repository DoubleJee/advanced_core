package algorithms.recursion;

public class Problem {

    public static void main(String[] args) {
        System.out.println(sum(10));
        System.out.println(sum0(10));
        System.out.println(sum1(10));

    }


    /**
     *  1. 求 1 + 2 + 3 + ... + n-1 + n 的和，n > 0
     */

    // 递归方式：时间复杂度：O(n)，空间复杂度：O(n)
    // 求 1 ~ n 相加的和
    static int sum(int n) {
        // 边界条件、递归基
        if (n <= 1) return 1;
        return n + sum(n - 1);
    }

    // 非递归方式：时间复杂度：O(n)，空间复杂度：O(1)
    static int sum0(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    // 公式方式：时间复杂度：O(1)，空间复杂度：O(1)
    static int sum1(int n){
        if (n <= 1) return n;
        return (1 + n) * n >> 1;
    }


}
