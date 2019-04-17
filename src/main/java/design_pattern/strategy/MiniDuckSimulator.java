package design_pattern.strategy;

import design_pattern.strategy.behavior.impl.FlyRocketPowered;

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

        //飞行和叫，有很多种策略。 算法族，可以相互替换，动态改变对象的行为

        /**
         * 策略模式：定义算法族（一群策略），分别封装起来，让它们之间可以互相替换，此模式让算法的变化，独立于使用算法的客户
         */

    }
}
