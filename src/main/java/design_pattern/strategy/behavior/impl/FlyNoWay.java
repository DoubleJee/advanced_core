package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我不可以飞");
    }
}
