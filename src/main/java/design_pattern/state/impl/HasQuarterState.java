package design_pattern.state.impl;

import design_pattern.state.GumballMachine;
import design_pattern.state.State;

import java.util.Random;

//有硬币状态
public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经有硬币了，你不能再投入硬币了！");
    }

    @Override
    public void ejectQuarter() {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
        System.out.println("退回成功！");
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了扳机");
        int winner = new Random(System.currentTimeMillis()).nextInt(10);
        if(winner == 0 && gumballMachine.getCount() > 1){
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("没有糖果！");
    }
}
