package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫..！");
    }
}
