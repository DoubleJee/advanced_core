package design_pattern.compound.observer;

public class Quackologist implements Observer {
    @Override
    public void update(QuackObservable quackObservable) {
        System.out.println("鸭子：" + quackObservable + "，呱呱叫了");
    }
}
