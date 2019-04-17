package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我被火箭带着飞..！");
    }
}
