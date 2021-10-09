package algorithms.greedy;

import java.util.Arrays;

// 贪心策略练习
public class Practice {

    public static void main(String[] args) {
//        optimalLoading();
        coinChange();
    }


    // 最优装载问题   贪心策略 每一次都优先选择重量最小的宝物
    static void optimalLoading() {
        // 总重量
        int weight = 30;
        // 每个宝物重量
        int[] weights = new int[]{3, 5, 4, 10, 7, 14, 2, 11};
        int count = 0;
        // 每次选最小的重量，以确保放入更多数量的宝物
        Arrays.sort(weights);

        for (int i = 0; i < weights.length; i++) {
            if (weight < weights[i]) continue;
            // 放入宝物
            weight -= weights[i];
            count++;
            System.out.println(weights[i]);
        }

        System.out.println("一共选了" + count + "件宝物");
    }

    // 零钱兑换问题  贪心策略 每一次都优先选择面值最大的硬币 （不是全局最优解）
    static void coinChange() {
        // 硬币面值
        int[] coins = new int[]{25, 5, 20, 1};
        // 要找零的钱
        int money = 41;
        int count = 0;

        // 每次选择最大面值的硬币，以确保给最少的硬币数量
        Arrays.sort(coins);

        for (int i = coins.length - 1; i >= 0;) {
            if (money < coins[i]) {
                // 这个硬币面值大于要找零的钱
                i--;
                continue;
            }
            // 找零硬币
            money -= coins[i];
            count++;
            System.out.println(coins[i]);
        }

        System.out.println("一共找了" + count + "枚硬币");
    }
}
