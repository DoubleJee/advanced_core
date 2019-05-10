package datastructure.complexity;

import datastructure.TimeTool;

public class FibNumber {

    /*
     * 0 1 1 2 3 5 8 13 21 ....
     */

    //时间复杂度：O(2^n)，每一层是n的两倍
    public static int fib1(int n) {
        if (n <= 1) return n;
        int fibOne = fib1(n - 1);
        int fibTwo = fib1(n - 2);
        return fibOne + fibTwo;
    }


    //时间复杂度：O(n)
    public static int fib2(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {

        TimeTool.checkTime("fb1", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(fib1(45));
            }
        });
        TimeTool.checkTime("fb2", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(45));;
            }
        });

    }
}
