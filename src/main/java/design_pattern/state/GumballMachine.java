package design_pattern.state;

import design_pattern.state.impl.*;

public class GumballMachine {
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State winnerState;
    private State soldState;

    private State state;
    private int count;

    public GumballMachine(int count){
        soldOutState = new SoldOutState(this);
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        winnerState = new WinnerState(this);
        soldState = new SoldState(this);
        this.count = count;
        if(count > 0){
            state = noQuarterState;
        }else {
            state = soldOutState;
        }
    }


    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public void setNoQuarterState(State noQuarterState) {
        this.noQuarterState = noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public void setHasQuarterState(State hasQuarterState) {
        this.hasQuarterState = hasQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(State winnerState) {
        this.winnerState = winnerState;
    }

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    /**
     * 投入硬币
     */
    public void insertQuarter(){
        state.insertQuarter();
    }

    /**
     * 退回硬币
     */
    public void ejectQuarter(){
        state.ejectQuarter();
    }

    /**
     * 转动扳机
     */
    public void turnCrank(){
        state.turnCrank();
        state.dispense();
    }

    public void releaseBall(){
        System.out.println("糖果已经出来了！");
        if(count > 0){
            count --;
        }
    }
}
