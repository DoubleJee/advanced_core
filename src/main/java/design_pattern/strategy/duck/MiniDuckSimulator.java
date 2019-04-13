package design_pattern.strategy.duck;

import design_pattern.strategy.duck.behavior.impl.FlyRocketPowered;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();
        mallardDuck.display();
        System.out.println();
        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        //动态改变对象的行为
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.performFly();

        //飞行和叫，有很多种策略。

    }
}
