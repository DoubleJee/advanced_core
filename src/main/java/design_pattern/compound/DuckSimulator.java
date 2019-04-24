package design_pattern.compound;

import design_pattern.compound.adapter.GooseAdapter;
import design_pattern.compound.component.Flock;
import design_pattern.compound.decorator.QuackDecorator;
import design_pattern.compound.factory.AbstractDuckFactory;
import design_pattern.compound.factory.DecoratorDuckFactory;
import design_pattern.compound.observer.Observer;
import design_pattern.compound.observer.Quackologist;

public class DuckSimulator {

    public void simulate(Quackable quackable){
        quackable.quack();
    }

    public void simulate(AbstractDuckFactory duckFactory){
        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        System.out.println("鸭子模拟器");
        Flock duckFlock = new Flock();
        duckFlock.add(mallardDuck);
        duckFlock.add(redheadDuck);
        duckFlock.add(duckCall);
        duckFlock.add(rubberDuck);
        duckFlock.add(gooseDuck);
        Observer observer = new Quackologist();
        duckFlock.registerObserver(observer);

        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseDuck);
        simulate(duckFlock);

        System.out.println("总共有" + QuackDecorator.getQuacks() + "声鸭叫");

    }


    public static void main(String[] args) {
        DuckSimulator duckSimulator = new DuckSimulator();
        duckSimulator.simulate(new DecoratorDuckFactory());

        //复合模式：结合两个或以上的模式，组成一个解决方案，解决一再发生的一般性问题。
    }
}
