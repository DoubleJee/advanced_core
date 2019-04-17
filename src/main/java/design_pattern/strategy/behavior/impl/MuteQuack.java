package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("沉默...（不会叫）");
    }
}
