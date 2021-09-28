package algorithms.recursion;

import datastructure.TimeTool;

// 斐波那契数
public class FibNumber {

    // 递归方式
    // 时间复杂度：O(2^n)，每一次调用都会分叉调用2次
    // 空间复杂度：O(n)，递归深度O(n - 1) * O(1) = O(n)
    public static int fib0(int n) {
        if (n <= 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }


    // 优化，用数组保留计算结果，避免大量重复计算，减少递归次数，自顶向下
    // 时间复杂度：O(n)，每个只会算1次，不会分叉调用2次
    // 空间复杂度：O(n)，公式是 O(n) + O(n) * O(1) = O(2n) = O(n)
    public static int fib1(int n){
        if (n <= 2) return 1;
        // n + 1为了方便，直接n作为下标取出结果
        int [] array = new int[n + 1];
        // 配合作为递归基
        array[1] = array[2] = 1;
        return fib1(n, array);
    }
    public static int fib1(int n, int [] array) {
        // 有结果，不用再次计算，递归基
        if (array[n] == 0) {
            array[n] = fib1(n - 1, array) + fib1(n - 2, array);
        }
        return array[n];
    }


    // 优化，去递归，自底向上
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    static int fib2(int n){
        if (n <= 2) return 1;
        int [] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }



    // 优化，使用滚动数组
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    static int fib3(int n) {
        if (n <= 2) return 1;
        // 每次就用了两个数组元素，所以用滚动数组
        int [] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            // 3%2 = 2%2 + 1%2   array[1] = array[0] + array[1]
            // 4%2 = 3%2 + 2%2   array[0] = array[1] + array[0]
            // 5%2 = 4%2 + 3%2   array[1] = array[0] + array[1]
            // 模2 可以优化成位运算 & 1，相当于取二进制的最后一位
            // 每次只用数组2个元素，滚动替换计算
            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
        }
        return array[n % 2];
    }


    // 优化，直接使用2个变量
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static int fib4(int n) {
        if (n <= 2) return 1;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    // 优化，使用数学公式
    // Math.pow 时间复杂度：O(logn)
    static int fib5(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }

    public static void main(String[] args) {
        TimeTool.checkTime("fib0", () -> System.out.println(fib0(45)));
        TimeTool.checkTime("fib1", () -> System.out.println(fib1(45)));
        TimeTool.checkTime("fib2", () -> System.out.println(fib2(45)));
        TimeTool.checkTime("fib3", () -> System.out.println(fib3(45)));
        TimeTool.checkTime("fib4", () -> System.out.println(fib4(45)));
        TimeTool.checkTime("fib5", () -> System.out.println(fib5(45)));
    }

}
