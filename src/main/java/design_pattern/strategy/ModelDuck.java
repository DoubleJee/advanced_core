package design_pattern.strategy;

import design_pattern.strategy.behavior.impl.FlyNoWay;
import design_pattern.strategy.behavior.impl.Quack;

public class ModelDuck extends Duck {

    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("我是一只模型鸭..！");
    }
}
