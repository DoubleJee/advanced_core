package algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 0-1背包问题
public class Knapsack {


    public static void main(String[] args) {
        // 不同的贪心策略
        // 价值主导 优先选择价值最高的放入背包
        selected("价值主导", (a1, a2) -> a2.value - a1.value);

        // 重量主导 优先选择最轻的放入背包
        selected("重量主导", (a1, a2) -> a1.weight - a2.weight);

        // 价值密度主导 优先选择平均1重量价值最高的放入背包
        selected("价值密度主导", (a1, a2) -> Double.compare(a2.valueDensity, a1.valueDensity));


    }


    // 根据贪心策略，选物品放入背包，以确保价值最大
    static void selected(String mode, Comparator<Article> comparator) {
        // 物品
        Article[] articles = new Article[]{
                new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)
        };
        // 背包重量，存放价值
        int weight = 150, value = 0;
        // 选择放入的物品
        List<Article> selectedArticles = new ArrayList<>();

        // 根据贪心策略，每次选最优物品，以确保价值最大
        Arrays.sort(articles, comparator);

        for (int i = 0; i < articles.length; i++) {
            if (weight < articles[i].weight) continue;

            // 放入背包
            selectedArticles.add(articles[i]);
            weight -= articles[i].weight;
            value += articles[i].value;
        }

        System.out.println("【" + mode + "】");
        System.out.println("总价值：" + value);
        for (Article selectedArticle : selectedArticles) {
            System.out.println(selectedArticle);
        }
        System.out.println("------------------------------------------");
    }


    // 物品
    private static class Article {
        // 重量
        private int weight;
        // 价值
        private int value;
        // 价值密度 价值 / 重量
        private double valueDensity;

        public Article(int weight, int value) {
            this.weight = weight;
            this.value = value;
            valueDensity = value * 1.0 / weight;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "weight=" + weight +
                    ", value=" + value +
                    ", valueDensity=" + valueDensity +
                    '}';
        }
    }
}
