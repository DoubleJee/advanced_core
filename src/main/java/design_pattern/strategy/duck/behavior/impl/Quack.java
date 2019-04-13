package design_pattern.strategy.duck.behavior.impl;

import design_pattern.strategy.duck.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫..！");
    }
}
