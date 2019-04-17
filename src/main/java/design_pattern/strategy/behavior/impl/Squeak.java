package design_pattern.strategy.behavior.impl;

import design_pattern.strategy.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫..！");
    }
}
