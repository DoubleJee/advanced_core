package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Light;

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        light.off();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        light.on();
    }
}
