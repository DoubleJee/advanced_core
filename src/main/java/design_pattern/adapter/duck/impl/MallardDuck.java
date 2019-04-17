package design_pattern.adapter.duck.impl;

import design_pattern.adapter.duck.Duck;

public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("我是绿头鸭，嘎嘎叫！");
    }

    @Override
    public void fly() {
        System.out.println("我是绿头鸭，我可以自由飞翔！");
    }
}
