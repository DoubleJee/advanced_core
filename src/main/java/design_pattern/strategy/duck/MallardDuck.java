package design_pattern.strategy.duck;

import design_pattern.strategy.duck.behavior.impl.FlyWithWings;
import design_pattern.strategy.duck.behavior.impl.Quack;

public class MallardDuck extends Duck{

    public MallardDuck(){
        //带着翅膀飞
        flyBehavior = new FlyWithWings();
        //嘎嘎叫
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("我是一只野鸭子！！！");
    }
}
