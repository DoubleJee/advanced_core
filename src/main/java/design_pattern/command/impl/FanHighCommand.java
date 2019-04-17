package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Fan;

public class FanHighCommand implements Command {

    Fan fan;

    private int lastSpeed = -1;


    public FanHighCommand(Fan fan){
        this.fan = fan;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        lastSpeed = fan.getSpeed();
        fan.high();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        if(lastSpeed == 0){
            fan.low();
        }else if (lastSpeed == 1){
            fan.high();
        }else{
            fan.off();
        }
    }
}
