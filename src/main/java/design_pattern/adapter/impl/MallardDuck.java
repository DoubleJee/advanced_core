package design_pattern.adapter.impl;

import design_pattern.adapter.Duck;

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
