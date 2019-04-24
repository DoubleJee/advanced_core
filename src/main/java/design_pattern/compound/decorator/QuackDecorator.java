package design_pattern.compound.decorator;

import design_pattern.compound.Quackable;
import design_pattern.compound.observer.Observer;

public class QuackDecorator implements Quackable {

    private Quackable quackable;
    private static int numberOfQuacks;

    public QuackDecorator(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        numberOfQuacks++;
    }
    public static int getQuacks(){
        return numberOfQuacks;
    }

    @Override
    public void registerObserver(Observer observer) {
        quackable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        quackable.notifyObservers();
    }
}
