package design_pattern.adapter;

import design_pattern.adapter.impl.MallardDuck;
import design_pattern.adapter.impl.WildTurkey;

public class DuckTurkeyTest {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.quack();
        duck.fly();

        Turkey turkey = new WildTurkey();
        turkey.gobble();
        turkey.fly();

        //目标对象是鸭子 -> 适配器 -> 被适配者是火鸡，客户看着是在对鸭子访问，但是请求给火鸡给操作了
        //客户 -> 鸭子 -> 适配器 -> 火鸡
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();

        //目标对象是火鸡 -> 适配器 -> 被适配者是鸭子，客户看着是在对火鸡访问，但是请求给了鸭子给操作了
        //客户 -> 火鸡 -> 鸭子
        Turkey duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();

    }
}
