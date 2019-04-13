package design_pattern.strategy.duck.behavior.impl;

import design_pattern.strategy.duck.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我可以飞！！！");
    }
}
