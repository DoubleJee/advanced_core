package design_pattern.state.impl;

import design_pattern.state.GumballMachine;
import design_pattern.state.State;

//没有硬币状态
public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("投入硬币，现在有硬币了");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你没有投入硬币！");
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了扳机，但是你没有投入硬币！");
    }

    @Override
    public void dispense() {
        System.out.println("你需要先付钱！");
    }
}
