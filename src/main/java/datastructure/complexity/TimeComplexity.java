package datastructure.complexity;

public class TimeComplexity {

    public static void test1(int n) {
        // 1
        if (n > 10) {
            System.out.println("n > 10");
        } else if (n > 5) {
            System.out.println("n > 5");
        } else {
            System.out.println("n < 5");
        }
        // 1 + 4 + 4 + 4
        for (int i = 0; i < 4; i++) {
            System.out.println("test");
        }

        // 1 + 1 + 4 + 4 + 4 = 14次

        //时间复杂度：O(1)，不管n是几都是14次，可能1次，忽略常数
        //空间复杂度：O(1)，一个变量i
    }

    public static void test2(int n) {

        // 1 + 3n
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }

        //时间复杂度：O(n)，1常数和3系数都忽略，可能是N次
        //空间复杂度：O(1)，一个变量i
    }

    public static void test3(int n) {

        // 1 + 2n + n * (1 + 3n)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }

        //时间复杂度：O(n^2) //3n + 3n^2 + 1，可能是n的2次方次。  直接看for循环 n * n  n的2次方

        //O(2^n)是每一层都是n的两倍，指数级增长。上面并不会 （例如fib数，每一次调用都会分叉调用2次）
    }


    public static void test4(int n) {

        // 1 + 2n + n * (1 + 45)
        // 1 + 48n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }

        //时间复杂度：O(n)，可能是n次
        //直接看for循环 n * 15，15个N，忽略15系数
    }

    public static void test5(int n) {
        //log2(n) n的2次幂  n能被2整除几次
        while ((n = n / 2) > 0) {
            System.out.println("test");
        }
        //时间复杂度：O(logn)，可能是n的次方幂次数
    }

    public static void test6(int n) {
        // 1 + 2* log2(n) + log2(n) * (1 + 3n)
        for (int i = 0; i < n; i += i) {
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }

        //时间复杂度：O(nlogn) 可能是n（内层循环）*n的次方幂次数（外层循环）
    }

    public static void test08(int n) {
        int a = 10;
        int b = 20;
        int c = a + b;
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + c);
        }

        //空间复杂度：O(n) 可能是n个int变量
    }

    public static void test09(int n,int k){
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
        for (int i = 0; i < k; i++) {
            System.out.println("test");
        }
        //时间复杂度：O(n+k)
    }
}
