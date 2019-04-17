package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我可以飞！！！");
    }
}
