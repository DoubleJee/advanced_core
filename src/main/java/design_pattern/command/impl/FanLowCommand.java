package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Fan;

public class FanLowCommand implements Command {

    Fan fan;

    private int lastSpeed = -1;


    public FanLowCommand(Fan fan){
        this.fan = fan;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        lastSpeed = fan.getSpeed();
        fan.low();
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
