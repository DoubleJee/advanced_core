package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Light;

public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        light.on();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        light.off();
    }
}
