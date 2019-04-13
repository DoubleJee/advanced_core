package design_pattern.strategy.duck;

import design_pattern.strategy.duck.behavior.FlyBehavior;
import design_pattern.strategy.duck.behavior.QuackBehavior;

//鸭子类
public abstract class Duck {
    FlyBehavior flyBehavior; //具有飞行为
    QuackBehavior quackBehavior;//具有叫行为

    public Duck(){

    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    //执行飞行为
    public void performFly(){
        flyBehavior.fly();
    }

    //执行叫行为
    public void performQuack(){
        quackBehavior.quack();
    }

    //展示自己
    public abstract void display();

    public void swim(){
        System.out.println("所有的鸭子都会浮起来，玩具鸭也会");
    }
}
