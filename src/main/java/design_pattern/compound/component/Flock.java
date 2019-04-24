package design_pattern.compound.component;

import design_pattern.compound.Quackable;
import design_pattern.compound.observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;

public class Flock implements Quackable {

    ArrayList<Quackable> quackables = new ArrayList<>();

    public void add(Quackable quackable){
        quackables.add(quackable);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackables.iterator();
        while (iterator.hasNext()){
            Quackable quackable = iterator.next();
            quackable.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = quackables.iterator();
        while (iterator.hasNext()){
            Quackable quackable = iterator.next();
            quackable.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
        //不需要实现
//        Iterator<Quackable> iterator = quackables.iterator();
//        while (iterator.hasNext()){
//            Quackable quackable = iterator.next();
//            quackable.notifyObservers();
//        }
    }
}
