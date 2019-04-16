package design_pattern.adapter.impl;

import design_pattern.adapter.Turkey;

public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("我是野火鸡，咯咯叫！");
    }

    @Override
    public void fly() {
        System.out.println("我是野火鸡，我只能飞一小下！");
    }
}
