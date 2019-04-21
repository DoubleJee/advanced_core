package design_pattern.state.impl;

import design_pattern.state.GumballMachine;
import design_pattern.state.State;

public class WinnerState implements State {

    private GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("请稍等一下再投币，我们将把糖果售出给你");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("不好意思，糖果即将出机，不可以退币了");
    }

    @Override
    public void turnCrank() {
        System.out.println("转两圈也不会多得到糖果！");
    }

    @Override
    public void dispense() {
        System.out.println("你是赢家，你会得到2颗糖果");
        gumballMachine.releaseBall();
        int count = gumballMachine.getCount();
        if(count == 0){
            gumballMachine.setState(gumballMachine.getSoldOutState());
            System.out.println("糖果已经售罄了！");
        }else {
            gumballMachine.releaseBall();
            if(count == 0){
                gumballMachine.setState(gumballMachine.getSoldOutState());
                System.out.println("糖果已经售罄了！");
            }else{
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }
        }
    }
}
