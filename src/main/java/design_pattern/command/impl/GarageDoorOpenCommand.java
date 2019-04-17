package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.GarageDoor;

public class GarageDoorOpenCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        garageDoor.up();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        garageDoor.down();
    }
}
