package design_pattern.state.impl;

import design_pattern.state.GumballMachine;
import design_pattern.state.State;

public class SoldOutState implements State {

    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("不要再投硬币了，糖果已经卖完了");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("无法退出，你没有投入硬币！");
    }

    @Override
    public void turnCrank() {
        System.out.println("已经没有糖果了。");
    }

    @Override
    public void dispense() {
        System.out.println("没有糖果！");
    }
}
