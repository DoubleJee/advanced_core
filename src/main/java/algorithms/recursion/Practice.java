package algorithms.recursion;

// 递归练习
public class Practice {


    /**
     * 求和：求 1 + 2 + 3 + ... + n-1 + n 的和，n > 0
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



    /**
     * 爬楼梯：n阶楼梯，假设一步只能走1步或者2步，求走n阶有多少种走法
     * 时间复杂度：O(2^n)，空间复杂度：O(n)
     */
    static int climbStair(int n) {
        // 1阶有1种走法，2阶有2种走法，递归基
        if (n <= 2) return n;
        // n = n - 1阶 + n - 2阶
        return climbStair(n - 1) + climbStair(n - 2);
    }



    /**
     * 汉诺塔：将n个碟子，从p1挪动到p3，怎么挪小的永远的在上面
     * 时间复杂度：O(2^n)，空间复杂度：O(n)
     */
    static void hanoi(int n, String p1, String p2, String p3){
        // n是1，直接挪到p3 递归基
        if (n == 1) {
            hanoiMove(n, p1, p3);
            return;
        }
        // 将n - 1个盘子挪到p2
        hanoi(n - 1, p1, p3, p2);
        // 将自己挪到p3
        hanoiMove(n, p1, p3);
        // 将n - 1个盘子挪到p3
        hanoi(n - 1, p2, p1, p3);
    }
    static void hanoiMove(int no, String from, String to) {
        System.out.println("将" + no + "号盘子，从" + from + "挪到了" + to);
    }


    public static void main(String[] args) {
        System.out.println(sum1(10));
        System.out.println(climbStair(10));
        hanoi(5, "A", "B", "C");
    }
}
