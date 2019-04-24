package design_pattern.compound.impl;

import design_pattern.compound.Quackable;
import design_pattern.compound.observer.Observable;
import design_pattern.compound.observer.Observer;
import design_pattern.compound.observer.QuackObservable;

public class RedheadDuck implements Quackable {

    private QuackObservable quackObservable;

    public RedheadDuck() {
        quackObservable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("嘎嘎");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        quackObservable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        quackObservable.notifyObservers();
    }
}
