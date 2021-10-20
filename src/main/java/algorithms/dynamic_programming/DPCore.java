package algorithms.dynamic_programming;

public class DPCore {

    /**
     * 动态规划：
     * 是求解最优化问题的一种常用策略  （简称DP算法，最优解）
     *
     * 基本思路：将复杂的原问题拆解成若干个简单的子问题，每个子问题仅仅解决一次，并保存它们的解，最后用子问题的解，推导出原问题的解
     *
     *
     * 新手通常的使用套路 （一步一步优化）
     * 1.暴力递归 （自顶向下，出现了重叠子问题）
     * 2.记忆化搜索 （自顶向下）
     * 3.递推 （自底向上）
     *
     * 递推 + 记忆化搜索 = 动态规划
     * 个人理解动态规划算是一种求最优解的过程
     */


    /**
     * 动态规划的常规步骤：
     *
     * 1.定义状态   （状态是原问题、子问题的解）
     *  比如定义 dp(i) 的含义                 （例如：fib(n) = 第n项斐波那契数、 coins(n) = 凑够n分钱所需要的最小硬币数， 就是原问题、子问题的解）
     *
     * 2.设置初始状态 （边界）
     *  比如设置 dp(0)、dp(1) 的值            （例如：fib(1)、fib(2) = 1、 coins(25)、coins(5) = 1， 就是设置初始解，可以轻易推出来的子问题状态解）
     *
     * 3.确定状态转移方程
     *  比如确定 dp(i) 和 dp(i - 1)、dp(i - x) 的关系    （例如：fib(n) = fib(n - 1) + fib(n - 2)、 dp(n) = min{dp(n - 25), dp(n - 5)} + 1， 就是由旧状态得到新状态的方程，由之前的解求出新解，最后子问题的解得出原问题的解，如可以从fib(1) 推到 fib(n)的fib方程）
     *
     *
     * 可以用动态规划解决的问题，通常具备两个特点：
     * 1.最优子结构（最优化原理）：通过求解子问题的最优解，可以获得原问题的最优解
     * 2.无后效性：
     *  某阶段的状态一旦确定，则此后过程的演变不再受此前各个状态及决策的影响       （每个状态只看自己的前一个状态，不去看再前面的那些状态，此后关注此，不关注此前，未来与过去无关）
     *  在推导后面状态的阶段，只关心前面阶段的具体状态值，不关心这个状态是怎么一步步推导出来的
     *
     */
}
